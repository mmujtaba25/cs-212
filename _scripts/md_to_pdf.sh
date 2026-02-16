#!/bin/bash

# Script: md_to_pdf.sh
# Purpose: Converts markdown to PDF using Typst instead of LaTeX
# Requires: pandoc, typst
# Outputs PDFs to _compiled_labs

# Check if correct number of arguments provided
if [ $# -ne 1 ]; then
  echo "Usage: $0 <markdown_file>"
  echo "Example: $0 Lab5.md"
  exit 1
fi

INPUT_MD="$1"

# Check if input file exists
if [ ! -f "$INPUT_MD" ]; then
  echo "Error: File '$INPUT_MD' not found!"
  exit 1
fi

# Extract filename without extension
BASENAME=$(basename "$INPUT_MD" .md)

# Output PDF path
OUTPUT_PDF="${BASENAME}.pdf"

# Path to Typst template (relative to project root)
PROJECT_ROOT="$(cd "$(dirname "$0")/.." && pwd)"
TEMPLATE_PATH="${PROJECT_ROOT}/_scripts/style.typ"

if [ ! -f "$TEMPLATE_PATH" ]; then
  echo "Error: Template '$TEMPLATE_PATH' not found!"
  exit 1
fi

echo "Converting markdown to PDF using Typst..."
echo "Input:    $INPUT_MD"
echo "Output:   $OUTPUT_PDF"
echo "Template: $TEMPLATE_PATH"
echo ""

# Convert using Typst engine with the template and syntax highlighting
pandoc "$INPUT_MD" \
  -o "$OUTPUT_PDF" \
  --pdf-engine=typst \
  --template="$TEMPLATE_PATH"

# Check result
if [ $? -eq 0 ]; then
  echo ""
  echo "✓ Success!"
  echo "PDF created: $OUTPUT_PDF"
else
  echo ""
  echo "✗ Error: PDF conversion failed!"
  echo ""
  echo "Make sure pandoc and typst are installed:"
  echo "  macOS:  brew install typst pandoc"
  echo "  Linux:  Use your package manager or download from:"
  echo "          https://github.com/typst/typst/releases"
  echo "          https://pandoc.org/installing.html"
  exit 1
fi