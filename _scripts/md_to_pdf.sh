#!/bin/bash

# Script: md_to_pdf.sh
# Purpose: Converts markdown to PDF using Typst instead of LaTeX
# Requires: pandoc, typst
# Outputs PDFs to _compiled_labs

# Source UI utilities
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
source "$SCRIPT_DIR/ui.sh"

# * validation
validate_args() {
  if [ $# -lt 1 ] || [ $# -gt 3 ]; then
    echo -e "${RED}Usage: $0 <markdown_file> [start_page] [end_page]${RESET}"
    echo -e "${YELLOW}Example: $0 Lab5.md 3 10${RESET}"
    exit 1
  fi
}

validate_file_exists() {
  local file="$1"
  if [ ! -f "$file" ]; then
    echo -e "${RED}Error: File '$file' not found!${RESET}"
    exit 1
  fi
}

validate_template_exists() {
  local template="$1"
  if [ ! -f "$template" ]; then
    echo -e "${RED}Error: Template '$template' not found!${RESET}"
    exit 1
  fi
}

check_dependencies() {
  local missing=()

  if ! command -v pandoc &>/dev/null; then
    missing+=("pandoc")
  fi

  if ! command -v typst &>/dev/null; then
    missing+=("typst")
  fi

  if [ ${#missing[@]} -gt 0 ]; then
    print_error "Missing dependencies: ${missing[*]}"
    echo ""
    echo "Make sure pandoc and typst are installed:"
    echo -e "  ${CYAN}macOS:${RESET}  brew install typst pandoc"
    echo -e "  ${CYAN}Linux:${RESET}  Use your package manager or download from:"
    echo "          https://github.com/typst/typst/releases"
    echo "          https://pandoc.org/installing.html"
    exit 1
  fi
}
# * validation end

# ==============================================================================
# Main Script
# ==============================================================================

validate_args "$@"

INPUT_MD="$1"
START_PAGE="${2:-}" # Optional, default empty
END_PAGE="${3:-}"   # Optional, default empty

# Check if input file exists
validate_file_exists "$INPUT_MD"

# Extract filename without extension
BASENAME=$(basename "$INPUT_MD" .md)

# Output PDF path
OUTPUT_PDF="${BASENAME}.pdf"

# Path to Typst template (relative to project root)
PROJECT_ROOT="$(cd "$(dirname "$0")/.." && pwd)"
TEMPLATE_PATH="${PROJECT_ROOT}/_scripts/style.typ"

validate_template_exists "$TEMPLATE_PATH"

print_info "${YELLOW}Converting $INPUT_MD to PDF${RESET}"

# Build pandoc options for page ranges
# default to 1 if not provided
: "${START_PAGE:=1}"
: "${END_PAGE:=0}" # 0 -> no end page limit

# if END_PAGE = 0  -> Page 1, Page 2
# if END_PAGE != 0 -> Page 1 of 2, Page 2 of 2 (END_PAGE = 2 here)

PAGE_VARS=()
PAGE_VARS+=("-V" "start-page=$START_PAGE")
PAGE_VARS+=("-V" "end-page=$END_PAGE")

pandoc "$INPUT_MD" \
  -o "$OUTPUT_PDF" \
  --pdf-engine=typst \
  --template="$TEMPLATE_PATH" \
  "${PAGE_VARS[@]}"

# Check result
if [ $? -eq 0 ]; then
  echo ""
  print_header "${GREEN}Created: ${PURPLE}$OUTPUT_PDF${RESET}"
else
  print_header "${RED}PDF conversion failed!${RESET}" "${RED}"
  check_dependencies
  exit 1
fi
