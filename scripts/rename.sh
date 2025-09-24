#!/bin/bash

# Loop through all .md files in ./algorithm
for file in ./algorithm/*.md; do
  # Extract the 'title' field from the file
  title=$(grep '^title:' "$file" | head -n 1 | cut -d':' -f2- | xargs)

  # Replace spaces with underscores
  safe_title=$(echo "$title" | tr ' ' '_')

  # Define new filename (in same directory)
  new_file="./algorithm/${safe_title}.md"

  # Rename the file
  mv "$file" "$new_file"

  echo "Renamed: $file -> $new_file"
done
