# Day 2 · 2026-11-03 · C 함수 / Java 메소드(static)

단계: 1 기초 다지기 · 총 70분 · 100점
규칙: AI·자동완성·검색 없이, 타이머 켜고. 막히면 5분 고민 후 힌트 1개만.
"이 함수를 구현해서 사용하라"는 조건을 지키지 않으면 답이 맞아도 감점이에요.

## C P1. 학점 변환 함수 (20점 · 10분) — `c/p1_grade_func.c`
`char to_grade(int score)` 를 만들어 사용. 90+ A, 80+ B, 70+ C, 60+ D, 0~59 F, 범위 밖이면 `'?'` 반환.
- 입력: `n`, 점수 `n`개
- 점수마다 `점수 -> 학점`, 범위 밖이면 `점수 -> invalid`
- 마지막 줄 `Pass: X` (D 이상 인원)
- 출력은 main에서만

```
[입력]                  [출력]
6                       95 -> A
95 85 70 59 101 60      85 -> B
                        70 -> C
                        59 -> F
                        101 -> invalid
                        60 -> D
                        Pass: 4
```

## C P2. 주차 요금 정산 (30점 · 25분) — `c/p2_parking_fee.c`
반드시 구현해서 사용:
- `int to_minutes(int hhmm)` — 분 단위로 변환, 시 > 23 또는 분 > 59 이면 `-1`
- `int calc_fee(int minutes)` — 30분 이하 0원, 초과분 10분당 500원(올림), 최대 20000원

입력 `n`, 이후 `n`줄 `입차 출차`(HHMM). 차량마다 `#번호 분 min 요금 won`, 잘못된 시각이거나 출차 < 입차면 `#번호 invalid`(합계 제외). 마지막 `Total: 합계 won`.

```
[입력]          [출력]
3               #1 135 min 5500 won
0930 1145       #2 30 min 0 won
1000 1030       #3 invalid
2300 2200       Total: 5500 won
```

## Java P1. 온도 변환 메소드 (20점 · 10분) — `java/P1TempConvert.java`
`static double cToF(double c)`, `static double fToC(double f)` 를 만들어 사용 (F = C × 9 / 5 + 32). 입력 `n`, 이후 `단위 값`. 소수 첫째 자리 출력, C/F가 아니면 `Unknown unit`.

```
[입력]     [출력]
3          36.5C = 97.7F
C 36.5     212.0F = 100.0C
F 212      Unknown unit
K 10
```

## Java P2. 학식 주문 정산 (30점 · 25분) — `java/P2CafeteriaOrder.java`
반드시 구현해서 사용: `static int priceOf(String menu)` (없으면 -1), `static int calcDiscount(int subtotal, boolean isStudent)`.
메뉴: ramen 3500 / bibimbap 5000 / donkatsu 6000 / coffee 2000 (대소문자 구분)
1. 첫 줄 `STUDENT` 또는 `GUEST` 와 `n`, 이후 `n`줄 `메뉴 수량`
2. 정상: `메뉴 x수량 = 금액`
3. 없는 메뉴 `Unknown menu: 메뉴`, 수량 0 이하 `Invalid qty: 메뉴` (메뉴 검사 먼저, 둘 다 합계 제외)
4. STUDENT 이고 소계 >= 10000 이면 10% 할인 (정수 나눗셈)
5. 마지막 `Subtotal: X` / `Discount: Y` / `Total: Z`

```
[입력]          [출력]
GUEST 4         donkatsu x2 = 12000
donkatsu 2      Unknown menu: pizza
pizza 1         Invalid qty: ramen
ramen 0         coffee x3 = 6000
coffee 3        Subtotal: 18000
                Discount: 0
                Total: 18000
```

채점용 테스트: `tests/` · 모범답안은 내일 `solutions/2026-11-03/` 에 공개
