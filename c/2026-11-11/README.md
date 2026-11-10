# Day 10 (2026-11-11 수) · C — 구조체 + 문자열: 이름으로 찾기 (strcmp)

> ⏱ AI·자동완성·검색 없이, 타이머 켜고. **P1 10분만이라도!** 채점: 이 폴더에서 `sh run_tests.sh` (P1 4개 + P2 4개)

```c
struct Student {
    char name[20];   /* 공백 없는 이름, 19자 이하 */
    int score;
};
```

## 🔥 P1. 이름으로 학생 찾기 — `p1_find.c` (40점 · 10분)

입력: `n`(1~10) → n줄 `이름 점수` → 마지막 줄에 찾을 이름 하나.
찾으면 `Found: Lee (92)`, 없으면 `Not found: Choi`. 이름은 **대소문자까지 정확히 같아야** 같은 이름이에요 (`Ah` 와 `Ahn` 은 다름).

| 함수 | 규칙 |
|---|---|
| `void print_student(const struct Student *s)` | `Kim (85)` — 어제와 같음, 줄바꿈 없음 |
| `int find_by_name(const struct Student s[], int n, const char *name)` | 이름이 같은 학생의 인덱스, 없으면 `-1` |

```
[입력 1]          [출력 1]
3                 Found: Lee (92)
Kim 85
Lee 92
Park 78
Lee

[입력 2]          [출력 2]
3                 Not found: Choi
Kim 85
Lee 92
Park 78
Choi
```

## 🧩 P2. 점수 고치기 — `p2_update.c` (60점 · 25분)

**P1 의 print_student, find_by_name 을 그대로 복사**하고 `add_score` 하나만 새로.
입력: `n` → n줄 학생 → `m` → m줄 `이름 변화량`(음수 가능).
각 명령마다: 학생이 있으면 점수를 바꾸고 `이름: 이전 -> 이후`, 없으면 `Not found: 이름`.
점수는 **0 ~ 100 을 넘지 않게** (100 넘으면 100, 0 밑이면 0). 마지막에 `Final:` 다음 줄부터 전체 학생(입력 순서).

| 함수 | 규칙 |
|---|---|
| `void add_score(struct Student *s, int delta)` | 포인터로 원본 점수 변경, 0~100 으로 자르기 |

```
[입력 1]          [출력 1]
3                 Kim: 85 -> 90
Kim 85            Not found: Choi
Lee 92            Park: 78 -> 70
Park 78           Final:
3                 Kim (90)
Kim 5             Lee (92)
Choi 10           Park (70)
Park -8

[입력 2]          [출력 2]
2                 Ahn: 97 -> 100
Ahn 97            Bae: 4 -> 0
Bae 4             Final:
2                 Ahn (100)
Ahn 10            Bae (0)
Bae -10
```

출력 형식 주의: 콜론 뒤 공백 1칸, `->` 앞뒤 공백 1칸.
