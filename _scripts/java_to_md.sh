#!/bin/bash

# Script: java_to_md.sh
# Purpose: Converts Java source files to a single markdown file, organized by package
# and includes program execution output for each file

# Check if correct number of arguments provided
if [ $# -ne 1 ]; then
  echo "Usage: $0 <directory>"
  echo "Example: $0 ./my-java-project"
  exit 1
fi

SOURCE_DIR="$1"

# Extract the directory name to use as output filename
DIR_NAME=$(basename "$SOURCE_DIR")
OUTPUT_MD="${DIR_NAME}.md"

# Clear any previous markdown file with the same name
>"$OUTPUT_MD"

# Create temporary file for sorting packages
TMP_FILE=$(mktemp)

echo "Collecting Java files from $SOURCE_DIR..."

# Find all Java files and extract package information
find "$SOURCE_DIR" -type f -name "*.java" | while read -r file; do
  # Extract package name from the file
  PACKAGE=$(grep -m1 "^package " "$file" | sed 's/package \(.*\);/\1/')
  # Use default package if no package declaration found
  PACKAGE=${PACKAGE:-"(default package)"}
  # Store package and file path for sorting
  echo "$PACKAGE|$file" >>"$TMP_FILE"
done

echo "Processing and organizing files by package..."

# Sort files alphabetically by package name, then process each file
sort "$TMP_FILE" | while IFS="|" read -r PACKAGE file; do

  # Re-extract package name from file (redundant but keeps consistency)
  PACKAGE=$(grep -m1 "^package " "$file" | sed 's/package \(.*\);/\1/')

  # Extract class/interface/enum name from the file
  CLASS=$(grep -m1 -E "class|interface|enum" "$file" |
    sed -E 's/.*(class|interface|enum)[[:space:]]+([A-Za-z0-9_]+).*/\2/')

  # Format lab part: converts "lab1" to "Lab 1"
  LAB_PART=$(echo "$PACKAGE" | cut -d'.' -f1 | sed 's/\([A-Za-z]\)\([0-9]\)/\1 \2/')

  # Format task part: converts "Task1" to "Task 1"
  TASK_PART=$(echo "$CLASS" | sed 's/\([A-Za-z]\)\([0-9]\)/\1 \2/')

  # Create the title for this section
  TITLE="$LAB_PART - $TASK_PART"

  echo "  Processing: $TITLE"

  # Write the title and source code to markdown
  {
    echo "# $TITLE"
    echo
    echo '```java'
    cat "$file"
    echo '```'
    echo
  } >>"$OUTPUT_MD"

  # Attempt to compile and run the Java file
  echo "  Attempting to compile and run..."

  # Get the directory of the Java file
  FILE_DIR=$(dirname "$file")

  # Compile the Java file with proper classpath
  # We need to compile from the source root, not from the file location
  cd "$SOURCE_DIR" 2>/dev/null
  COMPILE_OUTPUT=$(javac "$file" 2>&1)
  COMPILE_STATUS=$?
  cd - >/dev/null 2>&1

  # Check if compilation was successful
  if [ $COMPILE_STATUS -eq 0 ]; then
    # Extract fully qualified class name (package.ClassName)
    if [ -n "$PACKAGE" ] && [ "$PACKAGE" != "(default package)" ]; then
      FULL_CLASS_NAME="${PACKAGE}.${CLASS}"
    else
      FULL_CLASS_NAME="$CLASS"
    fi

    # Change to source directory to handle relative paths correctly
    cd "$SOURCE_DIR" 2>/dev/null

    # First attempt: Try to run with a 2-second timeout to detect if input is needed
    # Using 'timeout' command (or 'gtimeout' on macOS with coreutils)
    if command -v timeout &>/dev/null; then
      TIMEOUT_CMD="timeout"
    elif command -v gtimeout &>/dev/null; then
      TIMEOUT_CMD="gtimeout"
    else
      TIMEOUT_CMD=""
    fi

    if [ -n "$TIMEOUT_CMD" ]; then
      # Try running with timeout first to detect if program needs input
      PROGRAM_OUTPUT=$($TIMEOUT_CMD 2s java "$FULL_CLASS_NAME" 2>&1)
      RUN_STATUS=$?

      # Exit code 124 means timeout - program likely waiting for input
      if [ $RUN_STATUS -eq 124 ]; then
        echo "  ⚠️  Program requires user input!"
        echo "  Running interactively..."
        echo ""
        echo "  ┌─────────────────────────────────────────────┐"
        echo "  │  Running: $FULL_CLASS_NAME"
        echo "  └─────────────────────────────────────────────┘"
        echo ""

        # Run the program interactively so user can see and provide input
        java "$FULL_CLASS_NAME"
        INTERACTIVE_STATUS=$?

        echo ""
        echo "  ┌─────────────────────────────────────────────┐"
        echo "  │  Program completed. Now capturing output..."
        echo "  └─────────────────────────────────────────────┘"
        echo ""
        echo "  Please provide the same input again for documentation:"
        echo "  (Press Ctrl+D or Ctrl+Z when finished)"
        echo ""

        # Capture the output with user's input
        PROGRAM_OUTPUT=$(java "$FULL_CLASS_NAME" 2>&1)
        RUN_STATUS=$?
      fi
    else
      # No timeout command available, just run the program
      echo "  Running program..."
      PROGRAM_OUTPUT=$(java "$FULL_CLASS_NAME" 2>&1)
      RUN_STATUS=$?
    fi

    cd - >/dev/null 2>&1

    # Add program output section to markdown
    {
      echo "## Program Output"
      echo
      if [ $RUN_STATUS -eq 0 ] || [ $RUN_STATUS -eq 124 ]; then
        echo '```'
        echo "$PROGRAM_OUTPUT"
        echo '```'
      else
        echo '```'
        echo "Program execution failed:"
        echo "$PROGRAM_OUTPUT"
        echo '```'
      fi
      echo
    } >>"$OUTPUT_MD"

    # Clean up compiled .class files
    find "$SOURCE_DIR" -name "*.class" -delete 2>/dev/null
  else
    # If compilation failed, note it in the markdown
    {
      echo "## Program Output"
      echo
      echo '```'
      echo "Compilation failed:"
      echo "$COMPILE_OUTPUT"
      echo '```'
      echo
    } >>"$OUTPUT_MD"
  fi

  # Add spacing between entries
  echo "" >>"$OUTPUT_MD"

done

# Clean up temporary file
rm "$TMP_FILE"

echo ""
echo "Done!"
echo "Markdown file created: $OUTPUT_MD"
echo ""
echo "To convert to PDF, run: ./md_to_pdf.sh $OUTPUT_MD"
