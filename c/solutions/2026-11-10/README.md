# C 모범답안 — Day 9 (2026-11-10) 구조체 배열 + 포인터

문제: [`c/2026-11-10/README.md`](../../2026-11-10/README.md) · 채점: `c/2026-11-10` 폴더에 이 파일들을 복사하고 `sh run_tests.sh` → 8/8

## P1. p1_students.c — 학생 목록 + 1등

### 왜 이렇게 썼나
| 코드 | 왜? |
|---|---|
| `scanf("%s %d", s[i].name, &s[i].score)` | `name` 은 char 배열 → 이름 자체가 주소라 `&` 없음. `score` 는 int 라 `&` 필요 |
| `void print_student(const struct Student *s)` | 구조체를 통째로 복사하지 않고 주소만 넘김. 바꾸지 않으니 `const` |
| `s->name` | 포인터로 멤버 접근 = `(*s).name` 의 줄임 |
| `printf("%s (%d)", ...)` 에 `\n` 없음 | `Top: ` 뒤에도 같은 함수를 쓰려고 줄바꿈은 부르는 쪽(main)에서 |
| `best = 0` 에서 시작, `i = 1` 부터 | 첫 학생을 일단 1등으로 놓고 나머지와 비교 |
| `if (s[i].score > s[best].score)` | `>=` 가 아니라 `>` → 동점이면 앞 학생이 남음 |
| `&s[top_index(s, n)]` | 인덱스를 받아 그 학생의 주소를 print_student 에 넘김 |

### 짜는 순서
1. 구조체 + main 에서 입력만 받고, `printf("%s %d\n", ...)` 로 그대로 찍어 보기 → **실행해서 입력이 잘 들어오는지 확인**
2. `print_student` 작성 → main 의 출력 반복문을 print_student + `printf("\n")` 로 교체 → **실행**
3. `top_index` 작성 → `Top: ` 출력 → **입력 2(동점 95, 95)로 Bae 가 나오는지 확인**
4. `sh run_tests.sh` 로 P1 4개 OK

## P2. p2_ranking.c — 점수 순위표

### 왜 이렇게 썼나
| 코드 | 왜? |
|---|---|
| print_student 복사 | P1 도구 재사용 — 시험에서도 이미 짠 함수는 그대로 가져다 쓰기 |
| `struct Student temp = *a;` | 구조체는 `=` 로 이름(char 배열)까지 통째 복사됨 (strcpy 필요 없음) |
| `*a = *b; *b = temp;` | 포인터가 가리키는 **원본**을 바꿔야 main 의 배열이 바뀜 |
| 바깥 `i < n - 1`, 안쪽 `j < n - 1 - i` | 버블 정렬: 한 바퀴 돌 때마다 맨 뒤 1개가 자리 확정 → 범위 줄이기. `j + 1` 이 n 을 넘지 않음 |
| `if (s[j].score < s[j + 1].score)` | 내림차순이니 앞이 **작을 때만** 교체. `<=` 로 쓰면 동점끼리 자리가 바뀌어 입력 순서가 깨짐 |
| `printf("%d. ", i + 1)` | 순위는 1부터, 점 뒤 공백 1칸 |

### 짜는 순서
1. P1 에서 구조체·입력·print_student 복사 → 정렬 없이 `Ranking:` + 목록 출력 → **실행**
2. `swap_student` 작성 → main 에서 `swap_student(&s[0], &s[1])` 한 번 불러 보고 순서 바뀌는지 **확인 후 지우기**
3. `sort_by_score` 작성 → 입력 1로 Lee, Kim, Park 순서 **확인**
4. 입력 2(동점 95·95, 70·70)로 Bae→Eun, Ahn→Cho 순서인지 **확인** → `sh run_tests.sh` 8/8

## 자주 하는 실수
- `scanf("%s", &s[i].name)` — 경고 나옴 (`&` 빼기)
- `void swap_student(struct Student a, struct Student b)` — 값 복사본만 바뀌고 원본은 그대로
- 안쪽 반복을 `j < n` 으로 → `s[j + 1]` 이 배열 밖 (쓰레기값/크래시)
