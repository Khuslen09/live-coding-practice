# Day 1 · 2026-11-02 · C 입출력·조건·반복 / Java 기본 문법·Scanner

단계: 1 기초 다지기 · 총 70분 · 100점
규칙: AI·자동완성·검색 없이, 타이머 켜고. 막히면 5분 고민 후 힌트 1개만.
출력 형식은 공백·콜론·대소문자까지 정확히 맞아야 해요.

## C P1. 성적 통계 (20점 · 10분) — `c/p1_score_stats.c`
학생 수 `n`과 정수 점수 `n`개(0~100)를 입력받아 평균(`%.2f`), 최고점, 최저점을 출력. 배열 없이 반복문 하나로. `n`이 0이면 `No scores`.

```
[입력]            [출력]
5                 Average: 79.00
80 95 72 60 88    Max: 95
                  Min: 60
```
```
[입력]            [출력]
3                 Average: 0.33
0 0 1             Max: 1
                  Min: 0
```

## C P2. 놀이공원 요금 정산 (30점 · 25분) — `c/p2_ticket_fee.c`
| 나이 | 0~5 | 6~12 | 13~18 | 19~64 | 65~120 |
|---|---|---|---|---|---|
| 요금 | 0 | 1000 | 1500 | 3000 | 0 |

1. 방문객마다 `#번호 요금 won` (번호 1부터)
2. 나이 < 0 또는 > 120 이면 `#번호 invalid`, 집계 제외
3. 마지막에 `Visitors: X` / `Free: Y` / `Total: Z won`

```
[입력]          [출력]
4               #1 0 won
3 30 -2 70      #2 3000 won
                #3 invalid
                #4 0 won
                Visitors: 3
                Free: 2
                Total: 3000 won
```

## Java P1. 수강 학점 체크 (20점 · 10분) — `java/P1CreditCheck.java`
이름, 과목 수 `k`, 학점 `k`개 입력 → `이름: 총학점 credits`, 다음 줄에 21 초과 `Over limit` / 12 미만 `Under minimum` / 그 외 `OK`.

```
[입력]            [출력]
Khuslen 6         Khuslen: 18 credits
3 3 3 3 3 3       OK
```

## Java P2. 간단 ATM (30점 · 25분) — `java/P2SimpleAtm.java`
첫 줄 초기 잔액, 이후 명령:
- `D 금액` → `Deposited 금액`
- `W 금액` → `Withdrew 금액`, 잔액 부족 시 `Insufficient funds`
- `B` → `Balance: 잔액`
- `Q` → `Bye. Final balance: 잔액` 후 즉시 종료
- 그 외 → `Unknown command`
- D/W 금액이 0 이하면 `Invalid amount` (금액 검사가 잔액 검사보다 먼저)

```
[입력]     [출력]
1000       Insufficient funds
W 2000     Invalid amount
D -5       Invalid amount
W 0        Unknown command
X          Balance: 1000
B          Bye. Final balance: 1000
Q
```

채점용 테스트: `tests/` · 모범답안: `solutions/2026-11-02/`
