# Day 11 (2026-11-12 목) · Java — 2차원 객체 배열 + MVC: 짝맞추기 게임

> ⏱ AI·자동완성·검색 없이, 타이머 켜고. 막히면 5분 고민 후 힌트 1개만. **① 의 1~3번(10분)만이라도!**

이번 주 주제 "객체 배열 + 콘솔 MVC" 2일째. 어제는 1차원 `Candidate[]`, 오늘은 **2차원 `Cell[][]`** 이에요.

## ① P1Matching — 원본 재현: 짝맞추기 게임 (30분 · tester 21항목)

**원본:** `Doggzone/cse2016` → `exam1-2022.md` (도경구 교수님 2022 코딩시험 1 — Cell, GameBoard, GameController 미완성 → 완성)
원본 빈칸 4개 중 `isATwinWith`, `makeSquare`, `createTwinCells` 는 그대로 내고, `play()` 는 채점을 위해 한 턴을 `turn()` 으로 뺐어요 (play 는 완성).
`createTwinCells`·`makeSquare` 는 채점을 위해 private → **public** 으로 바꿨고, 섞지 않는 생성자 `GameBoard(Cell[] cells)` 를 추가했어요(완성).

```
src/model/Cell.java              색깔 + 뒤집힘 (1번)
src/model/GameBoard.java         4x4 판 (2~6번)
src/controller/GameController.java  한 턴 turn (7번), play 는 완성
src/view/BoardWriter.java, Reader.java, MatchingGame.java  화면·입력·실행 (완성, 원본 그대로)
src/tester/MatchingTester.java   채점기
```

| # | 메소드 | 규칙 |
|---|---|---|
| 1 | `Cell.isATwinWith(Cell c)` | 색깔이 같으면 true |
| 2 | `GameBoard.makeSquare(Cell[] cells)` | 1차원 16장을 한 줄에 4장씩 `square[0][0], [0][1], … [3][3]` 에 |
| 3 | `GameBoard.createTwinCells(Color[] cs)` | 색마다 Cell 을 **2개 따로 new**, 순서 `cs[0], cs[0], cs[1], cs[1], …`, 길이 `cs.length*2` |
| 4 | `GameBoard.cellAt(int number)` | 화면 번호 1~16 → 칸. `1→[0][0]`, `4→[0][3]`, `5→[1][0]`, `16→[3][3]`, 범위 밖 `null` |
| 5 | `GameBoard.revealedCount()` | 뒤집힌 칸 수 |
| 6 | `GameBoard.status()` | 정확히 `"점수: 4 / 16 (남은 짝 6쌍)"` (남은 짝 = (16 − 점수) / 2) |
| 7 | `GameController.turn(int fst, int snd)` | 같은 번호·범위 밖·이미 뒤집힌 칸 → 아무것도 안 바꾸고 false. 둘 다 reveal → 짝이면 `board.upgrade()` + true(뒤집힌 채로), 아니면 둘 다 hide + false |

```bash
cd java/2026-11-12/P1Matching/src
javac -encoding UTF-8 -d ../bin $(find . -name '*.java')
java -Djava.awt.headless=true -cp ../bin tester.MatchingTester     # 시작 상태 1 / 21
java -cp ../bin view.MatchingGame                                  # (창 있는 컴퓨터에서) 실제 게임
```

## ② P2WordPair — 변형: 단어 카드 짝맞추기 (20분 · tester 12항목)

P1 과 같은 구조를 2줄 x 3칸(6장, 3쌍) 단어 카드로. Cell → `WordCard`, GameBoard + turn → `WordBoard` 하나로.

```
화면 번호:  1 2 3
           4 5 6
```

| # | 메소드 | 규칙 |
|---|---|---|
| 1 | `WordCard.isPairWith(WordCard c)` | 단어가 같으면 true (**대소문자 구분**) |
| 2 | `static WordBoard.createPairs(String[] words)` | 단어마다 2장씩 따로 new, 순서 `a, a, b, b, …` |
| 3 | `WordBoard.cardAt(int number)` | 1~6 → `grid[?][?]` (한 줄에 **3장**), 범위 밖 `null` |
| 4 | `WordBoard.pick(int a, int b)` | P1 turn 과 같은 규칙, 짝이면 둘 다 `show()` + `matched` 1 증가 + true |
| 5 | `WordBoard.status()` | 정확히 `"맞춘 짝 1 / 3"` |

```bash
cd java/2026-11-12/P2WordPair/src
javac -encoding UTF-8 -d ../bin $(find . -name '*.java')
java -Djava.awt.headless=true -cp ../bin tester.WordPairTester     # 시작 상태 1 / 12
java -cp ../bin view.WordPairApp                                   # 콘솔로 플레이
```

모범답안 + 풀이 과정은 내일 `java/solutions/2026-11-12/` 에 공개돼요.
