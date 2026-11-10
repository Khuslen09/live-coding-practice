# Day 10 (2026-11-11 수) · Java — 객체 배열 + 콘솔 MVC (투표 집계)

> ⏱ AI·자동완성·검색 없이, 타이머 켜고. 이번 주 주제: **객체 배열 + 콘솔 MVC** (11/11–11/17)

공통 실행법 (프로젝트 폴더의 `src` 에서):
```bash
javac -encoding UTF-8 -d ../bin $(find . -name '*.java')
java -Djava.awt.headless=true -cp ../bin tester.VoteTester     # P2 는 tester.KioskTester
java -cp ../bin view.VoteApp                                   # 콘솔로 직접 실행해 보기 (선택)
```

## ① P1Vote — 원본 재현: 투표 집계 (30분 · tester 20항목)

**원본:** `Hyunha/2025COM2018` → `COM2018/src/chapters/ch07/VoteCount.java` (같은 코드: `Hyunha/2023CSE2016/chapter07/src/VoteCount.java`).
원본은 `int[] votes` 하나와 JOptionPane 입력 while 반복 → 오늘은 **후보 객체 배열(Candidate[])** + model/view/controller 로 나눈 버전.

| # | 클래스.메소드 | 규칙 |
|---|---|---|
| 1 | `Candidate.addVote()` | 한 표 더하기 |
| 2 | `Ballot(String[] names)` 생성자 | names 길이의 `Candidate[]` 를 만들고 **각 칸에 new Candidate(...)** |
| 3 | `Ballot.vote(int v)` | 0 ≤ v < 후보 수면 한 표 + `true`, 아니면 `false` (예외 X) |
| 4 | `Ballot.total()` | 전체 표 수 |
| 5 | `Ballot.winner()` | 최다 득표 번호, 동점이면 작은 번호, 0표면 `-1` |
| 6 | `Ballot.report(int i)` | 정확히 `"후보 0번(Kim)이 3표를 득표하였습니다."` |
| 7 | `VoteController.run(int[] inputs)` | 앞에서부터 투표, **범위 밖 번호가 나오면 즉시 멈춤**(원본 while 규칙), 반영된 표 수 리턴 |

시작 상태: 3/20 · 완성 파일: `size`, `get`, `results`, `view/VoteApp`

## ② P2Kiosk — 변형: 분식집 키오스크 주문 (20분 · tester 12항목)

P1 과 같은 구조: Candidate → `MenuItem`(완성: 이름·가격·팔린 개수, `addOrder(qty)`), Ballot → `OrderBoard`.

| # | 메소드 | 규칙 |
|---|---|---|
| 1 | `OrderBoard(String[] names, int[] prices)` | `MenuItem[]` 만들고 i 번 칸에 `new MenuItem(names[i], prices[i])` |
| 2 | `order(int idx, int qty)` | idx 범위 안 **그리고** qty ≥ 1 이면 반영 + `true`, 아니면 `false` |
| 3 | `totalSales()` | (가격 × 개수) 합 |
| 4 | `bestSeller()` | 가장 많이 팔린 번호, 동점이면 작은 번호, 하나도 안 팔렸으면 `-1` |
| 5 | `line(int i)` | 정확히 `"김밥 x3 = 9000원"` |

시작 상태: 3/12
