#!/bin/bash

# Script: java_to_md.sh
# Purpose: Converts Java source files to a single markdown file, organized by package
# and includes program execution output for each file

# Source UI utilities
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
source "$SCRIPT_DIR/ui.sh"

# * validation
validate_args() {
  if [ $# -ne 1 ]; then
    echo -e "${RED}Usage: $0 <directory>${RESET}"
    echo -e "${YELLOW}Example: $0 ./my-java-project${RESET}"
    exit 1
  fi
}
# * validation end

# ==============================================================================
# Main Script
# ==============================================================================

validate_args "$@"

SOURCE_DIR="$1"

# Extract the directory name to use as output filename
DIR_NAME=$(basename "$SOURCE_DIR")
OUTPUT_MD="${DIR_NAME}.md"

# Clear any previous markdown file with the same name
>"$OUTPUT_MD"

# Create temporary file for sorting packages
TMP_FILE=$(mktemp)

print_info "Collecting Java files from $SOURCE_DIR..."

# Find all Java files and extract package information
find "$SOURCE_DIR" -type f -name "*.java" | while read -r file; do
  # Extract package name from the file
  PACKAGE=$(grep -m1 "^package " "$file" | sed 's/package \(.*\);/\1/')
  # Use default package if no package declaration found
  PACKAGE=${PACKAGE:-"(default package)"}
  # Store package and file path for sorting
  echo "$PACKAGE|$file" >>"$TMP_FILE"
done

print_info "Processing and organizing files by package..."

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

  echo ""
  print_section "$TITLE"

  # Write the title and source code to markdown
  {
    echo "# $TITLE"
    echo
    echo '```java'
    cat "$file"
    echo '```'
    echo
  } >>"$OUTPUT_MD"

  # Determine the source root for compilation
  if [ -n "$PACKAGE" ] && [ "$PACKAGE" != "(default package)" ]; then
    # Get the first part of the package (e.g., "Lab5" from "Lab5.Tasks")
    FIRST_PACKAGE=$(echo "$PACKAGE" | cut -d'.' -f1)

    # Get the directory containing the file
    FILE_DIR=$(dirname "$file")

    # Find the source root by looking for the directory that contains FIRST_PACKAGE
    CURRENT_DIR="$FILE_DIR"
    SOURCE_ROOT=""

    while [ "$CURRENT_DIR" != "/" ] && [ -n "$CURRENT_DIR" ]; do
      PARENT=$(dirname "$CURRENT_DIR")
      BASENAME=$(basename "$CURRENT_DIR")

      # If current directory name matches first package, parent is the source root
      if [ "$BASENAME" = "$FIRST_PACKAGE" ]; then
        SOURCE_ROOT="$PARENT"
        break
      fi

      CURRENT_DIR="$PARENT"
    done

    # If we couldn't determine source root, use SOURCE_DIR
    if [ -z "$SOURCE_ROOT" ] || [ "$SOURCE_ROOT" = "/" ]; then
      SOURCE_ROOT="$SOURCE_DIR"
    fi
  else
    SOURCE_ROOT="$SOURCE_DIR"
  fi

  # Compile from the source root
  print_info "[1/2] Compiling..."
  cd "$SOURCE_ROOT" 2>/dev/null
  COMPILE_OUTPUT=$(javac "$file" 2>&1)
  COMPILE_STATUS=$?

  # Check if compilation was successful
  if [ $COMPILE_STATUS -eq 0 ]; then
    print_success "Compilation successful"

    # Extract fully qualified class name (package.ClassName)
    if [ -n "$PACKAGE" ] && [ "$PACKAGE" != "(default package)" ]; then
      FULL_CLASS_NAME="${PACKAGE}.${CLASS}"
    else
      FULL_CLASS_NAME="$CLASS"
    fi

    print_info "[2/2] Running program..."
    echo ""

    # Check if timeout/gtimeout is available
    if command -v timeout &>/dev/null; then
      TIMEOUT_CMD="timeout"
    elif command -v gtimeout &>/dev/null; then
      TIMEOUT_CMD="gtimeout"
    else
      TIMEOUT_CMD=""
    fi

    # Create a temporary file for capturing output
    TEMP_OUTPUT=$(mktemp)

    # First, try with a short timeout to see if program needs input
    NEEDS_INPUT=true

    if [ "$NEEDS_INPUT" = true ]; then
      # Program needs input - run it interactively
      print_warning "This program requires user input"
      print_info "The session will be captured for the PDF"
      echo ""

      # Run with script to capture everything on macOS
      if [[ "$OSTYPE" == "darwin"* ]]; then
        # macOS version - use script with typescript file and wait for output
        TYPESCRIPT_FILE=$(mktemp)

        # Run script connected to the terminal; run it in background and wait for it explicitly
        script -q "$TYPESCRIPT_FILE" bash -lc "java $FULL_CLASS_NAME" </dev/tty &
        SCRIPT_PID=$!
        wait "$SCRIPT_PID"
        SCRIPT_EXIT=$?

        # Give the file a moment to be written (in case of buffering); poll a few times
        RETRIES=0
        while [ ! -s "$TYPESCRIPT_FILE" ] && [ $RETRIES -lt 10 ]; do
          sleep 0.1
          RETRIES=$((RETRIES + 1))
        done

        # Read and clean the typescript file if it exists and is non-empty
        if [ -f "$TYPESCRIPT_FILE" ] && [ -s "$TYPESCRIPT_FILE" ]; then
          PROGRAM_OUTPUT=$(sed 's/\r$//g' "$TYPESCRIPT_FILE" | sed 's/\x1b\[[0-9;]*m//g' | grep -v '^Script started' | grep -v '^Script done')
          RUN_STATUS=$SCRIPT_EXIT
        else
          PROGRAM_OUTPUT="[No output captured - check if program ran correctly]"
          RUN_STATUS=1
        fi

        rm -f "$TYPESCRIPT_FILE"
      else
        # Linux version
        script -q -c "java $FULL_CLASS_NAME" "$TEMP_OUTPUT" </dev/tty
        PROGRAM_OUTPUT=$(cat "$TEMP_OUTPUT" | col -b)
        RUN_STATUS=$?
      fi

      echo ""
      print_success "Session captured successfully"
    else
      # Program doesn't need input - use captured output
      PROGRAM_OUTPUT=$(cat "$TEMP_OUTPUT")
      RUN_STATUS=$?
      print_success "Program executed"
    fi

    rm -f "$TEMP_OUTPUT"
    cd - >/dev/null 2>&1

    # Add program output section to markdown
    {
      echo "## Program Output"
      echo
      echo '```'
      if [ -n "$PROGRAM_OUTPUT" ]; then
        echo "$PROGRAM_OUTPUT"
      else
        echo "[No output]"
      fi
      echo '```'
      echo
    } >>"$OUTPUT_MD"

    # Clean up compiled .class files from source root
    find "$SOURCE_ROOT" -name "*.class" -delete 2>/dev/null
  else
    # If compilation failed, note it in the markdown
    print_error "Compilation failed"
    cd - >/dev/null 2>&1
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
print_header "${GREEN}Markdown Generation Complete!${GREEN}"
print_info "Markdown file created: ${PURPLE}$OUTPUT_MD${RESET}"
echo -e "To convert to PDF, run: ${CYAN}./md_to_pdf.sh $OUTPUT_MD${RESET}"