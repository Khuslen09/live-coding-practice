# Day 1 · 2026-11-02 · Java 기본 문법·Scanner

단계: 1 기초 다지기 · 총 35분 · 50점 (C+Java 합쳐 100점)
규칙: AI·자동완성·검색 없이, 타이머 켜고. 막히면 5분 고민 후 힌트 1개만.
출력 형식은 공백·콜론·대소문자까지 정확히 맞아야 해요.

## Java P1. 수강 학점 체크 (20점 · 10분) — `P1CreditCheck.java`
이름, 과목 수 `k`, 학점 `k`개 입력 → `이름: 총학점 credits`, 다음 줄에 21 초과 `Over limit` / 12 미만 `Under minimum` / 그 외 `OK`.

```
[입력]            [출력]
Khuslen 6         Khuslen: 18 credits
3 3 3 3 3 3       OK
```

## Java P2. 간단 ATM (30점 · 25분) — `P2SimpleAtm.java`
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


채점용 테스트: `tests/` · 모범답안: `../solutions/2026-11-02/`
