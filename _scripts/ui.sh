#!/bin/bash
# ==============================================================================
# UI / Box System (Shared)
# ==============================================================================
# Prevent double sourcing
if [[ -n "${UI_SH_LOADED:-}" ]]; then
  return
fi
readonly UI_SH_LOADED=1
# ------------------------------------------------------------------------------
# Colors
# ------------------------------------------------------------------------------
GREEN='\033[0;32m'
RED='\033[0;31m'
YELLOW='\033[1;33m'
CYAN='\033[0;36m'
PURPLE='\033[0;35m'
BLUE='\033[0;34m'
BOLD='\033[1m'
RESET='\033[0m'
readonly GREEN RED YELLOW CYAN PURPLE BLUE BOLD RESET
# ------------------------------------------------------------------------------
# Internal Box Printer
# ------------------------------------------------------------------------------
_print_box() {
  local text="$1"
  local color="$2"
  local bold="$3"
  local padding=1
  local min_width=60

  # Strip ANSI codes to measure visible length
  local clean_text
  clean_text=$(echo -e "$text" | sed 's/\x1b\[[0-9;]*m//g')
  local len=${#clean_text}

  local inner_width=$((len + padding * 2))
  if [ "$inner_width" -lt "$min_width" ]; then
    inner_width=$min_width
  fi

  local total_padding=$((inner_width - len))
  local left_padding=$((total_padding / 2))
  local right_padding=$((total_padding - left_padding))

  # Top
  echo -e "${color}${bold}┌$(printf '─%.0s' $(seq 1 "$inner_width"))┐${RESET}"

  # Middle
  printf "${color}${bold}│${RESET}"
  printf "%*s" "$left_padding" ""
  echo -ne "${color}${bold}${text}${RESET}"
  printf "%*s" "$right_padding" ""
  echo -e "${color}${bold}│${RESET}"

  # Bottom
  echo -e "${color}${bold}└$(printf '─%.0s' $(seq 1 "$inner_width"))┘${RESET}"
}

# ------------------------------------------------------------------------------
# Public UI Functions
# ------------------------------------------------------------------------------
print_header() {
  _print_box "$1" "$CYAN" "$BOLD"
}
print_step() {
  local step="$1"
  local total="$2"
  local title="$3"
  _print_box "Step $step/$total — $title" "$BLUE" "$BOLD"
}
print_section() {
  _print_box "$1" "$PURPLE" ""
}
print_success() {
  echo -e "${GREEN}✓ $1${RESET}"
}

print_error() {
  echo -e "${RED}✗ $1${RESET}"
}

print_warning() {
  echo -e "${YELLOW}⚠ $1${RESET}"
}

print_info() {
  echo -e "${CYAN}→ $1${RESET}"
}

print_separator() {
  echo -e "${PURPLE}$(printf '─%.0s' {1..60})${RESET}"
}
