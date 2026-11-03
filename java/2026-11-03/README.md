# Day 2 · 2026-11-03 · Java 메소드(static)

단계: 1 기초 다지기 · 총 35분 · 50점 (C+Java 합쳐 100점)
규칙: AI·자동완성·검색 없이, 타이머 켜고. 막히면 5분 고민 후 힌트 1개만.
"이 함수를 구현해서 사용하라"는 조건을 지키지 않으면 답이 맞아도 감점이에요.

## Java P1. 온도 변환 메소드 (20점 · 10분) — `P1TempConvert.java`
`static double cToF(double c)`, `static double fToC(double f)` 를 만들어 사용 (F = C × 9 / 5 + 32). 입력 `n`, 이후 `단위 값`. 소수 첫째 자리 출력, C/F가 아니면 `Unknown unit`.

```
[입력]     [출력]
3          36.5C = 97.7F
C 36.5     212.0F = 100.0C
F 212      Unknown unit
K 10
```

## Java P2. 학식 주문 정산 (30점 · 25분) — `P2CafeteriaOrder.java`
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


채점용 테스트: `tests/` · 모범답안은 내일 `../solutions/2026-11-03/` 에 공개
