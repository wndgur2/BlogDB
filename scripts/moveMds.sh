#!/bin/bash

# Ensure the target directory exists
mkdir -p ./posts/algorithm

# Find all .md files and rename them based on their path
find ./algorithm -type f -name "*.md" | while read file; do
  # Remove the leading ./algorithm/
  rel_path="${file#./algorithm/}"

  # Replace slashes with underscores
  new_name=$(echo "$rel_path" | tr '/' '_')

  # Move the file with the new name
  mv "$file" "./posts/algorithm/$new_name"
done
