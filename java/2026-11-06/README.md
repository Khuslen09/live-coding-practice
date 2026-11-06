# Day 5 (2026-11-06 금) · Java — 추상 클래스 · 인터페이스 · 다형성 (블랙잭)

⏱ 총 50분 · 50점 · AI·자동완성·검색 없이, 타이머 켜고!

어제(주차장)는 "키로 찾기"였다면, 오늘은 **인터페이스(행동 약속) → 추상 클래스(공통 코드) → 자식 클래스(다른 부분만)** 3층 구조예요.
기말에서 Dealer/Controller 가 `CardPlayerBehavior` 타입 하나로 사람·컴퓨터를 똑같이 다루는 게 핵심이에요.

---

## ① P1Blackjack — 원본 재현 (30점 · 30분)

**원본:** `Hyunha/2025COM2018` → `COM2018/src/labs/lab08/cardgame/` (Card, CardDeck, CardPlayerBehavior, Dealer — 올해 lab10 에 해당)
+ 완성형 참고: `Doggzone/cse2016` → `blackjack.zip` (CardPlayer, ComputerPlayer, HumanPlayer, Dealer, BlackjackController)

바꾼 점: 패키지 `model` / `controller` / `tester` 로 나눔, 테스트를 위해 `Dealer(CardDeck d)` 생성자 추가,
`playRound()` 가 결과 문자열을 리턴, 원본의 "처음 21이면 이기고도 계속 진행" 버그를 → 바로 BLACKJACK 으로 끝내도록 고침.

```
model/CardPlayerBehavior (interface: wantsACard, receiveCard)
        ▲
model/CardPlayer (abstract: Card[] hand, receiveCard, showCards, totalScore)
   ├── model/ComputerPlayer  (16 이하면 더 받기)
   └── model/HumanPlayer     (콘솔 Y/N, 칩 계산 — 이미 완성)
controller/Dealer               (CardDeck 으로 카드 나눠주기)
controller/BlackjackController  (한 판 진행)
```

| 파일 | 구현할 메소드 | 규칙 (파일 주석에도 있음) |
|---|---|---|
| CardPlayer | `receiveCard(Card c)` | 자리 있으면 넣고 true, 꽉 차면 false |
| CardPlayer | `totalScore()` | 2~10 그대로, J·Q·K 10, A 11 → 21 넘으면 A 를 1로(-10), A 남아 있는 동안 반복 |
| ComputerPlayer | `wantsACard()` | 점수 ≤ 16 이면 true |
| Dealer | `dealOneTo(p)` / `dealTo(p)` | 1장 주기 / 원하는 동안 계속 주기, 못 받으면 멈춤 |
| BlackjackController | `playRound()` | 사람·컴·사람·컴 → 21이면 BLACKJACK → 사람 dealTo → 21초과 LOSE → 컴 dealTo → WIN/DRAW/LOSE |

채점: `tester/BlackjackTester` **20항목**

```bash
cd java/2026-11-06/P1Blackjack/src
javac -encoding UTF-8 -d ../bin model/*.java controller/*.java tester/*.java && java -cp ../bin tester.BlackjackTester
# 직접 한 판 해보기:
java -cp ../bin controller.BlackjackController
```

## ② P2Delivery — 변형: 택배 상차 (20점 · 20분)

똑같은 3층 구조를 **택배**로 바꿨어요. 대응표:

| 블랙잭 | 택배 |
|---|---|
| CardPlayerBehavior | LoadBehavior (`wantsMore`, `load`) |
| CardPlayer (abstract) | Vehicle (abstract: `Parcel[] cargo`, `maxWeight`) |
| ComputerPlayer / HumanPlayer | Bike (3개·10kg, 8kg 미만이면 더 받음) / Van (10개·100kg, 80kg 이하면 더 받음) |
| CardDeck | ParcelQueue (들어온 순서대로) |
| Dealer.dealTo | Dispatcher.dispatchTo |

| 파일 | 구현할 메소드 | 규칙 |
|---|---|---|
| Vehicle | `totalWeight()` | 실린 상자 무게 합 |
| Vehicle | `load(Parcel p)` | 칸이 남았고 실어도 maxWeight **이하**면 싣고 true, 아니면 false |
| Dispatcher | `dispatchTo(LoadBehavior v)` | 큐에 남아 있고 v 가 원하는 동안 1개씩 싣기. 못 실으면 rejected+1 하고 멈춤. 실은 개수 리턴 |
| Dispatcher | `countVans(Vehicle[])` | instanceof 로 Van 개수 (null 칸 있음) |
| Dispatcher | `totalFee(Vehicle[])` | 모든 차 `fee()` 합 — Bike/Van 구분 없이 (다형성), null 건너뛰기 |

채점: `tester/DeliveryTester` **12항목**

```bash
cd java/2026-11-06/P2Delivery/src
javac -encoding UTF-8 -d ../bin model/*.java controller/*.java tester/*.java && java -cp ../bin tester.DeliveryTester
```

### 배점
P1: 20항목 × 1.25점 + 코드 리뷰 5점 / P2: 12항목 × 1.25점 + 코드 리뷰 5점
