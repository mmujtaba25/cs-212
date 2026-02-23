#!/bin/bash

# Script: lab_to_pdf.sh
# Purpose: Compile a specific lab (e.g., Lab5) into a single PDF
# Usage: ./lab_to_pdf.sh Lab5

# Setup paths first
PROJECT_ROOT="$(pwd)"
source "${PROJECT_ROOT}/_scripts/ui.sh"

# * validation
validate_args() {
  if [ $# -ne 1 ]; then
    echo -e "${RED}Usage: $0 <LabDirectory>${RESET}"
    echo -e "${YELLOW}Example: $0 Lab5${RESET}"
    exit 1
  fi
}

validate_directory() {
  local dir="$1"
  if [ ! -d "$dir" ]; then
    echo -e "${RED}Error: Lab directory '$(basename "$dir")' not found.${RESET}"
    exit 1
  fi
}

validate_script() {
  local script="$1"
  local name="$2"
  if [ ! -f "$script" ]; then
    echo -e "${RED}Error: $name not found in _scripts directory.${RESET}"
    exit 1
  fi
}
# * validation end

# ==============================================================================
# Main Script
# ==============================================================================

clear
clear
clear

validate_args "$@"

LAB_NAME="$1"
LAB_DIR="${PROJECT_ROOT}/${LAB_NAME}"
SCRIPTS_DIR="${PROJECT_ROOT}/_scripts"
COMPILED_DIR="${PROJECT_ROOT}/_compiled_labs"

JAVA_TO_MD_NAME="java_to_md.sh"
MD_TO_PDF_NAME="md_to_pdf.sh"
JAVA_TO_MD_DIR="${SCRIPTS_DIR}/${JAVA_TO_MD_NAME}"
MD_TO_PDF_DIR="${SCRIPTS_DIR}/${MD_TO_PDF_NAME}"

# Verify lab directory exists
validate_directory "$LAB_DIR"

# Verify scripts exist
validate_script "$JAVA_TO_MD_DIR" "$JAVA_TO_MD_NAME"
validate_script "$MD_TO_PDF_DIR" "$MD_TO_PDF_NAME"

# Create compiled directory if it doesn't exist
mkdir -p "$COMPILED_DIR"

print_header "Compiling Lab: $LAB_NAME"

# Step 1: Generate markdown from Java sources
print_step "1" "2" "Running ${JAVA_TO_MD_NAME}"
echo ""

bash "$JAVA_TO_MD_DIR" "$LAB_DIR"

# The script creates a file named ${LAB_NAME}.md in the current directory
GENERATED_MD="${LAB_NAME}.md"

if [ ! -f "$GENERATED_MD" ]; then
  echo ""
  print_error "Markdown generation failed"
  echo "Expected file: $GENERATED_MD"
  exit 1
fi

# Move the generated markdown to compiled directory with .temp.md extension
TEMP_MD_NAME="${LAB_NAME}.temp.md"
TEMP_MD_DIR="${COMPILED_DIR}/${TEMP_MD_NAME}"
mv "$GENERATED_MD" "$TEMP_MD_DIR"

print_info "Markdown moved to _compiled_labs/$TEMP_MD_NAME"

echo ""
# Step 2: Convert markdown to PDF
print_step "2" "2" "Running ${MD_TO_PDF_NAME}"

# Change to compiled directory and convert
cd "$COMPILED_DIR" || exit 1
bash "$MD_TO_PDF_DIR" "$(basename "$TEMP_MD_DIR")"

# Rename the output PDF (remove .temp from name)
TEMP_PDF="${LAB_NAME}.temp.pdf"
FINAL_PDF="${LAB_NAME}.code.pdf"

if [ -f "$TEMP_PDF" ]; then
  mv "$TEMP_PDF" "$FINAL_PDF"
  print_info "$TEMP_PDF renamed to $FINAL_PDF"
fi

# Return to project root
cd "$PROJECT_ROOT" || exit 1

echo ""
print_header "Compilation Complete!" "$BLUE"
print_info "Markdown: ${PURPLE}_compiled_labs/${TEMP_MD_NAME}${RESET}"
print_info "PDF:      ${PURPLE}_compiled_labs/${FINAL_PDF}${RESET}"
echo ""
