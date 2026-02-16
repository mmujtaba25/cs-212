#!/bin/bash

# Script: lab_to_pdf.sh
# Purpose: Compile a specific lab (e.g., Lab5) into a single PDF
# Usage: ./lab_to_pdf.sh Lab5

# Check if correct number of arguments provided
if [ $# -ne 1 ]; then
  echo "Usage: $0 <LabDirectory>"
  echo "Example: $0 Lab5"
  exit 1
fi

LAB_NAME="$1"
PROJECT_ROOT="$(pwd)"
LAB_DIR="${PROJECT_ROOT}/${LAB_NAME}"
SCRIPTS_DIR="${PROJECT_ROOT}/_scripts"
COMPILED_DIR="${PROJECT_ROOT}/_compiled_labs"

JAVA_TO_MD="${SCRIPTS_DIR}/java_to_md.sh"
MD_TO_PDF="${SCRIPTS_DIR}/md_to_pdf.sh"

# Verify lab directory exists
if [ ! -d "$LAB_DIR" ]; then
  echo "Error: Lab directory '$LAB_NAME' not found."
  exit 1
fi

# Verify scripts exist
if [ ! -f "$JAVA_TO_MD" ]; then
  echo "Error: java_to_md.sh not found in _scripts directory."
  exit 1
fi

if [ ! -f "$MD_TO_PDF" ]; then
  echo "Error: md_to_pdf.sh not found in _scripts directory."
  exit 1
fi

# Create compiled directory if it doesn't exist
mkdir -p "$COMPILED_DIR"

echo "=========================================="
echo "Compiling Lab: $LAB_NAME"
echo "=========================================="
echo ""

# Generate markdown from Java sources
echo "Step 1: Generating markdown from Java sources..."
echo ""

bash "$JAVA_TO_MD" "$LAB_DIR"

# The script creates a file named ${LAB_NAME}.md in the current directory
GENERATED_MD="${LAB_NAME}.md"

if [ ! -f "$GENERATED_MD" ]; then
  echo ""
  echo "Error: Markdown generation failed."
  echo "Expected file: $GENERATED_MD"
  exit 1
fi

# Move the generated markdown to compiled directory with .temp.md extension
TEMP_MD="${COMPILED_DIR}/${LAB_NAME}.temp.md"
mv "$GENERATED_MD" "$TEMP_MD"

echo ""
echo "Step 2: Converting markdown to PDF..."
echo ""

# Change to compiled directory and convert
cd "$COMPILED_DIR" || exit 1
bash "$MD_TO_PDF" "$(basename "$TEMP_MD")"

# Rename the output PDF (remove .temp from name)
TEMP_PDF="${LAB_NAME}.temp.pdf"
FINAL_PDF="${LAB_NAME}.pdf"

if [ -f "$TEMP_PDF" ]; then
  mv "$TEMP_PDF" "$FINAL_PDF"
fi

# Return to project root
cd "$PROJECT_ROOT" || exit 1

echo ""
echo "=========================================="
echo "✓ Compilation Complete!"
echo "=========================================="
echo ""
echo "Output files:"
echo "  Markdown: _compiled_labs/${LAB_NAME}.temp.md"
echo "  PDF:      _compiled_labs/${LAB_NAME}.pdf"
echo ""
