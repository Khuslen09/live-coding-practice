# Day 3 · 2026-11-04 · Java 배열

단계: 1 기초 다지기 · 총 35분 · 50점 (C+Java 합쳐 100점)
규칙: AI·자동완성·검색 없이, 타이머 켜고. 막히면 5분 고민 후 힌트 1개만.

## Java P1. 평균 넘은 점수 (20점 · 10분) — `P1AboveAverage.java`
- 입력: `n` (1 ≤ n ≤ 100), 정수 `n`개
- 1줄: `Average: 평균` (소수 둘째 자리)
- 2줄: `Above:` 뒤에 평균보다 **큰** 값을 입력 순서대로 (앞에 공백 1칸). 없으면 `Above: none`
- 3줄: `Count: 개수`

```
[입력 1]        [출력 1]
4               Average: 62.50
70 85 40 55     Above: 70 85
                Count: 2

[입력 2]        [출력 2]
3               Average: 50.00
50 50 50        Above: none
                Count: 0
```

## Java P2. 도서관 대출 관리 (30점 · 25분) — `P2LibraryBooks.java`
`String[] titles` 와 `boolean[] borrowed` 두 배열로 관리. 반드시 구현해서 사용:
- `static int findBook(String[] titles, String t)` — 제목 위치, 없으면 `-1` (대소문자 구분)
- `static void printAvailable(String[] titles, boolean[] borrowed)` — `Available:` 줄 출력

입력: `m`, 제목 `m`개(공백 없는 한 단어), `q`, 이후 명령 `q`줄
| 명령 | 출력 |
|---|---|
| `BORROW 제목` | `Borrowed: 제목` / 이미 대출 중 `Already borrowed: 제목` / 없는 책 `Not found: 제목` |
| `RETURN 제목` | `Returned: 제목` / 대출 중 아님 `Not borrowed: 제목` / 없는 책 `Not found: 제목` |
| `LIST` | `Available:` 뒤에 대출 가능한 책을 입력 순서대로, 없으면 `Available: none` |

마지막 줄: `Borrowed count: 현재 대출 중인 권수`

```
[입력 1]          [출력 1]
3                 Borrowed: Java
Java C Python     Already borrowed: Java
6                 Not found: Rust
BORROW Java       Not borrowed: C
BORROW Java       Available: C Python
BORROW Rust       Returned: Java
RETURN C          Borrowed count: 0
LIST
RETURN Java

[입력 2]          [출력 2]
2                 Borrowed: Algo
Algo DB           Borrowed: DB
5                 Available: none
BORROW Algo       Not found: algo
BORROW DB         Returned: DB
LIST              Borrowed count: 1
RETURN algo
RETURN DB
```

## 채점
`tests/` 의 입력으로 실행해 `.out` 과 정확히 같으면 통과 (숨김 경계값 포함). 메소드 미사용은 감점.
