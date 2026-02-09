#!/bin/bash

# Usage check
if [ $# -ne 1 ]; then
  echo "Usage: $0 <directory>"
  exit 1
fi

SOURCE_DIR="$1"
OUTPUT_MD="java_sources.temp.md"
OUTPUT_PDF="java_sources.pdf"

# Clear previous markdown file
>"$OUTPUT_MD"

# Temp file for sorting
TMP_FILE=$(mktemp)

# Collect package + file path
find "$SOURCE_DIR" -type f -name "*.java" | while read -r file; do
  PACKAGE=$(grep -m1 "^package " "$file" | sed 's/package \(.*\);/\1/')
  PACKAGE=${PACKAGE:-"(default package)"}
  echo "$PACKAGE|$file" >>"$TMP_FILE"
done

# Sort alphabetically by package
sort "$TMP_FILE" | while IFS="|" read -r PACKAGE file; do

  # Extract package
  PACKAGE=$(grep -m1 "^package " "$file" | sed 's/package \(.*\);/\1/')

  # Extract class name
  CLASS=$(grep -m1 -E "class|interface|enum" "$file" |
    sed -E 's/.*(class|interface|enum)[[:space:]]+([A-Za-z0-9_]+).*/\2/')

  LAB_PART=$(echo "$PACKAGE" | cut -d'.' -f1 | sed 's/\([A-Za-z]\)\([0-9]\)/\1 \2/')

  # Tasks -> Task
  TASK_PART=$(echo "$CLASS" | sed 's/\([A-Za-z]\)\([0-9]\)/\1 \2/')

  TITLE="$LAB_PART - $TASK_PART"

  {
    echo "# $TITLE"
    echo
    echo '```java'
    cat "$file"
    echo '```'
    echo
    echo
  } >>"$OUTPUT_MD"

done

# Cleanup
rm "$TMP_FILE"

# Convert markdown to PDF
pandoc "$OUTPUT_MD" -o "$OUTPUT_PDF" \
  --pdf-engine=xelatex \
  --syntax-highlighting=idiomatic \
  --include-in-header=_prereqs/style.tex

echo "Done!"
echo "Markdown: $OUTPUT_MD"
echo "PDF: $OUTPUT_PDF"
