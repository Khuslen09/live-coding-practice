# Day 11 (2026-11-12 목) · C — 동적할당 입문 (malloc / free)

> ⏱ AI·자동완성·검색 없이, 타이머 켜고. **P1 10분만이라도!** 채점: 이 폴더에서 `sh run_tests.sh` (P1 4개 + P2 4개)

지금까지는 `struct Student s[MAX];` 처럼 크기를 미리 정했어요. 오늘은 **입력받은 n 만큼만** `malloc` 으로 공간을 만들고, 다 쓰면 `free` 해요.
(`#include <stdlib.h>` 필요 · malloc 이 `NULL` 이면 `Memory error` 출력 후 `return 1;` — 채점은 안 하지만 꼭 쓰기)

## 🔥 P1. 거꾸로 출력 + 합계 — `p1_reverse.c` (40점 · 10분)

입력: `n`(1~1000) → 정수 n개 (한 줄, 공백으로 구분).
출력: `Reversed:` 뒤에 거꾸로 (숫자 사이 공백 1칸, **끝에 공백 없음**), 다음 줄 `Sum: 합`.

| 함수 | 규칙 |
|---|---|
| `int sum_array(const int *a, int n)` | a 가 가리키는 정수 n개의 합 |

```
[입력 1]          [출력 1]
5                 Reversed: 5 4 3 2 1
1 2 3 4 5         Sum: 15

[입력 2]          [출력 2]
4                 Reversed: 7 0 10 -3
-3 10 0 7         Sum: 14
```

## 🧩 P2. 평균보다 높은 학생 — `p2_above.c` (60점 · 25분)

**P1 과 같은 도구(malloc → 입력 → 합계 반복 → free)** 를 int 대신 `struct Student` 로. print_student 는 어제 것을 그대로 복사.

```c
struct Student {
    char name[20];   /* 공백 없는 이름, 19자 이하 */
    int score;
};
```

입력: `n`(1~1000) → n줄 `이름 점수`.
출력: `Average: 85.00` (소수 둘째 자리) → `Above average:` → 평균보다 **높은**(같으면 제외) 학생만 입력 순서대로 `Lee (92)`. 한 명도 없으면 `(none)`.

| 함수 | 규칙 |
|---|---|
| `void print_student(const struct Student *s)` | `Kim (85)` — 줄바꿈 없음 (Day 9~10 과 같음) |
| `double average(const struct Student *s, int n)` | 평균 점수 (정수 나눗셈 주의!) |

```
[입력 1]          [출력 1]
3                 Average: 85.00
Kim 85            Above average:
Lee 92            Lee (92)
Park 78

[입력 2]          [출력 2]
3                 Average: 60.00
Min 60            Above average:
Ji 60             (none)
Su 60
```

## 채점
```bash
cd c/2026-11-12
sh run_tests.sh      # 시작 코드는 0 / 8
```
모범답안 + 풀이 과정은 내일 `c/solutions/2026-11-12/` 에 공개돼요.
