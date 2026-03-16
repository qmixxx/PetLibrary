#!/bin/bash

SEARCH_PATH=${1:-.}

echo "Starting the scanner..."

# Ищем ключевые слова в файлах конфигурации (.env, .conf, .yaml, .yml, .json)
# -r: рекурсивно
# -n: показать номер строки
# -E: использовать расширенные регулярные выражения
# -i: игнорировать регистр
grep -rniE "(password|api[_-]?key|secret|token|credential)" "$SEARCH_PATH" \
  --include=\*.{env,conf,yaml,yml,json} \
  --exclude-dir={.git,node_modules,.github,target} |
grep -v '\${{' |
grep -v '\${[A-Z_]*}' |
grep -v 'example'

echo "Scanning is completed..."

