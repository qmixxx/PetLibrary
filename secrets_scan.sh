#!/bin/bash

SEARCH_PATH=${1:-.}

echo "🕵️ Starting secret scanner..."

# Ищем ключевые слова в файлах конфигурации (.env, .conf, .yaml, .yml, .json)
# -r: рекурсивно
# -n: показать номер строки
# -E: использовать расширенные регулярные выражения
# -i: игнорировать регистр
RESULT=$(grep -rniE "(password|api[_-]?key|secret|token|credential)" "$SEARCH_PATH" \
  --include=\*.{env,conf,yaml,yml,json} \
  --exclude-dir={.git,node_modules,.github,target} |
grep -v '\${{' |
grep -v '\${[A-Z_]*}' |
grep -v 'example'
)

if [ -z "$RESULT" ]; then
    echo "✅ No secrets found."
else
    echo "❌ Potential secrets detected:"
    echo "$RESULT"

    # GitHub Actions annotations
    while IFS= read -r line; do
        file=$(echo "$line" | cut -d: -f1)
        lineno=$(echo "$line" | cut -d: -f2)
        message=$(echo "$line" | cut -d: -f3-)
        echo "::error file=$file,line=$lineno::$message"
    done <<< "$RESULT"

    exit 1
fi

echo "🛡️ Scan completed."

