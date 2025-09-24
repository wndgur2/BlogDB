#!/bin/bash
set -euo pipefail
IFS=$'\n\t'

# Where we'll collect finished posts
mkdir -p ./posts/algorithm

# Map file extension -> fenced code language
lang_from_ext() {
  case "$1" in
    java) echo "java" ;;
    cpp|cc|cxx|hpp|hh|hxx) echo "cpp" ;;
    c|h) echo "c" ;;
    py) echo "python" ;;
    js|mjs|cjs) echo "javascript" ;;
    ts|tsx) echo "typescript" ;;
    jsx) echo "jsx" ;;
    kt|kts) echo "kotlin" ;;
    go) echo "go" ;;
    rs) echo "rust" ;;
    swift) echo "swift" ;;
    rb) echo "ruby" ;;
    php) echo "php" ;;
    cs) echo "csharp" ;;
    scala) echo "scala" ;;
    r) echo "r" ;;
    lua) echo "lua" ;;
    sh|bash|zsh) echo "bash" ;;
    sql) echo "sql" ;;
    *) echo "" ;; # fallback: no language tag
  esac
}

# Make a safe filename from a title
safe_slug() {
  # Trim, replace spaces with _, and replace disallowed path chars with _
  # Keep unicode (e.g., Korean) intact.
  sed -e 's/^[[:space:]]*//' -e 's/[[:space:]]*$//' \
  | tr ' ' '_' \
  | sed -E 's#[/\\:*?"<>|]#_#g'
}

# Extract YAML frontmatter title
extract_title() {
  awk '
    /^---[[:space:]]*$/ { hdr=!hdr; next }
    hdr && $1=="title:" {
      sub(/^title:[[:space:]]*/, "", $0)
      print
      exit
    }
  ' "$1"
}

# Append code block if not already appended
append_code_block() {
  local mdfile="$1"
  local codefile="$2"
  local lang="$3"
  local basename_code
  basename_code="$(basename "$codefile")"

  # Avoid double-appending if rerun: look for a sentinel that includes the code filename
  if grep -q "<!-- CODE-APPENDED:$basename_code -->" "$mdfile"; then
    echo "  - Code already appended for $basename_code, skipping append."
    return
  fi

  echo "" >> "$mdfile"
  echo "---" >> "$mdfile"
  echo "" >> "$mdfile"
  echo "### Code" >> "$mdfile"
  echo "" >> "$mdfile"
  echo "<!-- CODE-APPENDED:$basename_code -->" >> "$mdfile"
  if [ -n "$lang" ]; then
    echo "\`\`\`$lang" >> "$mdfile"
  else
    echo "\`\`\`" >> "$mdfile"
  fi
  cat "$codefile" >> "$mdfile"
  echo "" >> "$mdfile"
  echo "\`\`\`" >> "$mdfile"
}

# Find all markdown files under ./algorithm
find ./algorithm -type f -iname "*.md" | while read -r md; do
  dir="$(dirname "$md")"
  md_base="$(basename "$md")"

  echo "Processing: $md"

  # Find the code file in the same directory (assume exactly one code file)
  # Extend this list as needed.
  codefile="$(find "$dir" -maxdepth 1 -type f \
    ! -iname "$md_base" \
    \( -iname "*.java" -o -iname "*.cpp" -o -iname "*.cc" -o -iname "*.cxx" \
       -o -iname "*.c" -o -iname "*.h" \
       -o -iname "*.py" -o -iname "*.js" -o -iname "*.mjs" -o -iname "*.cjs" \
       -o -iname "*.ts" -o -iname "*.tsx" -o -iname "*.jsx" \
       -o -iname "*.kt" -o -iname "*.kts" \
       -o -iname "*.go" -o -iname "*.rs" -o -iname "*.swift" \
       -o -iname "*.rb" -o -iname "*.php" -o -iname "*.cs" \
       -o -iname "*.scala" -o -iname "*.r" -o -iname "*.lua" \
       -o -iname "*.sh" -o -iname "*.bash" -o -iname "*.zsh" \
       -o -iname "*.sql" \) \
    -print -quit || true)"

  if [ -z "${codefile:-}" ]; then
    echo "  - No code file found next to $md_base; continuing."
  else
    ext="${codefile##*.}"; ext="$(echo "$ext" | tr '[:upper:]' '[:lower:]')"
    lang="$(lang_from_ext "$ext")"
    echo "  - Found code: $codefile (lang: ${lang:-none})"
    append_code_block "$md" "$codefile" "$lang"
  fi

  # Pull title from YAML frontmatter
  title="$(extract_title "$md" || true)"
  if [ -z "${title:-}" ]; then
    echo "  - No 'title:' found in YAML frontmatter; leaving filename as-is."
    final_name="$md_base"
  else
    final_name="$(printf "%s" "$title" | safe_slug).md"
  fi

  # Compute destination path; avoid collisions by adding numeric suffix
  dest="./posts/algorithm/$final_name"
  if [ -e "$dest" ]; then
    i=1
    while [ -e "./posts/algorithm/${final_name%.md}_$i.md" ]; do
      i=$((i+1))
    done
    dest="./posts/algorithm/${final_name%.md}_$i.md"
    echo "  - Destination exists; using: $(basename "$dest")"
  fi

  # Move to posts directory (rename in the process)
  mv "$md" "$dest"
  echo "  - Moved to: $dest"
done

echo "All done."
