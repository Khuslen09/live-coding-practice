#!/bin/sh
# 사용법: c/2026-11-11 폴더에서  sh run_tests.sh
for p in p1_find p2_update; do
  gcc -Wall -o $p $p.c || exit 1
done
pass=0; total=0
for f in tests/*.in; do
  b=$(basename $f .in); total=$((total+1))
  case $b in c_p1_*) prog=./p1_find;; *) prog=./p2_update;; esac
  if $prog < $f | diff - tests/$b.out > /dev/null; then echo "[OK]   $b"; pass=$((pass+1));
  else echo "[FAIL] $b  (< 내 출력 / > 정답)"; $prog < $f | diff - tests/$b.out | head -6; fi
done
echo "Score: $pass / $total"
rm -f p1_find p2_update
