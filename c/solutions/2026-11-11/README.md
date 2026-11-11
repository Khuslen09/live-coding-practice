# C 모범답안 — Day 10 (2026-11-11) 구조체 + 문자열

문제: [`c/2026-11-11/README.md`](../../2026-11-11/README.md) · 채점: `c/2026-11-11` 폴더에 이 파일들을 복사하고 `sh run_tests.sh` → 8/8

## P1. p1_find.c — 이름으로 학생 찾기

### 왜 이렇게 썼나
| 코드 | 왜? |
|---|---|
| `#include <string.h>` | `strcmp` 를 쓰려면 필요 |
| `strcmp(s[i].name, name) == 0` | `s[i].name == name` 은 **주소** 비교라 항상 다름. strcmp 는 글자를 하나씩 비교해 같으면 0 |
| 찾으면 바로 `return i;` | 더 볼 필요 없음. 첫 번째로 같은 학생 |
| 반복이 끝난 뒤 `return -1;` | `else return -1;` 을 반복 **안**에 쓰면 첫 학생만 보고 끝나 버림 |
| `const char *name` | 문자열(char 배열)은 함수에 주소로 넘어감. 바꾸지 않으니 const |
| `scanf("%s", query)` | char 배열이라 `&` 없음 |
| `printf("Found: "); print_student(...); printf("\n");` | 어제 만든 print_student 를 그대로 재사용 — 형식 `Found: Lee (92)` |

### 짜는 순서
1. 입력만 받고 `printf("%s\n", query)` 로 마지막 이름이 잘 들어오는지 **실행해서 확인**
2. `find_by_name` 작성 → main 에서 `printf("%d\n", find_by_name(s, n, query))` 로 인덱스 찍어 보기 (Lee → 1, Choi → -1)
3. 출력 두 갈래(`Found:` / `Not found:`) 작성 → 입력 1, 2 **확인**
4. `Ah` vs `Ahn` 테스트(c_p1_4) 확인 → `sh run_tests.sh` P1 4개 OK

## P2. p2_update.c — 점수 고치기

### 왜 이렇게 썼나
| 코드 | 왜? |
|---|---|
| print_student, find_by_name 복사 | P1 도구 그대로 — P2 는 add_score 하나만 새로 |
| `void add_score(struct Student *s, ...)` | 값으로 받으면 복사본만 바뀜. 포인터로 받아야 원본 변경 |
| `s->score += delta;` 다음 `if` 두 개 | 먼저 더하고 나서 100 / 0 으로 자르기 |
| `old = s[idx].score;` 를 add_score **전에** | 바꾼 뒤엔 이전 값이 사라짐 → `Kim: 85 -> 90` 에 필요 |
| `continue;` | 못 찾은 이름은 출력만 하고 다음 명령으로 |
| `"%s: %d -> %d\n"` | 콜론 뒤 공백 1칸, 화살표 앞뒤 공백 1칸 (출력 형식 주의!) |

### 짜는 순서
1. P1 코드 통째로 복사 → 학생 입력 + `Final:` 목록만 먼저 출력 → **실행**
2. m 명령 반복: 이름·delta 읽고 find_by_name 결과만 찍어 보기
3. `add_score` 작성 → `old` 저장 → `Kim: 85 -> 90` 출력 → 입력 1 **확인**
4. 입력 2(97+10 → 100, 4-10 → 0)로 자르기 **확인** → `sh run_tests.sh` 8/8

## 자주 하는 실수
- `if (s[i].name == name)` — 컴파일은 되지만 항상 거짓
- `find_by_name` 안에서 `else return -1;` — 첫 칸만 확인
- add_score 를 먼저 부르고 old 를 저장 → `85 -> 90` 이 아니라 `90 -> 90`
- `Kim:85 -> 90` (콜론 뒤 공백 빠짐) → 테스트 FAIL
