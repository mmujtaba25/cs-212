#!/bin/bash

# Predefined values
CMS_ID="540040"
NAME="Muhammad_Mujtaba"

# Check arguments
if [ "$#" -ne 2 ]; then
    echo "Usage: $0 <N> <directory_path>"
    exit 1
fi

N="$1"
DIR_PATH="$2"

# Validate directory
if [ ! -d "$DIR_PATH" ]; then
    echo "Error: Directory does not exist."
    exit 1
fi

ZIP_NAME="_zips/Lab_N_${CMS_ID}.${NAME}.zip"
ZIP_NAME="${ZIP_NAME/Lab_N/Lab_${N}}"

# Create zip excluding .docx files
zip -r "$ZIP_NAME" "$DIR_PATH" -x "*.docx"

echo "Created archive: $ZIP_NAME"
