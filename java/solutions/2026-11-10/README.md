# Java 모범답안 — Day 9 (2026-11-10) 추상 클래스로 중복 제거

문제: [`java/2026-11-10/README.md`](../../2026-11-10/README.md) · 원본: `Hyunha/2023CSE2016` → `MidTermCheck/src/marblegame/`
확인: P1Marble `MarbleTester` **20/20**, P2Dice `DiceTester` **12/12**

```bash
cd java/solutions/2026-11-10/P1Marble/src
javac -encoding UTF-8 -d ../bin model/*.java controller/*.java tester/*.java
java -Djava.awt.headless=true -cp ../bin tester.MarbleTester
```

## ① P1Marble — 홀짝 구슬 게임

### 왜 이렇게 썼나
| 코드 | 왜? |
|---|---|
| `public abstract class Gamer` + `public abstract String name();` | Player·Computer 의 공통 코드(구슬 수, 더하기/빼기, lose)는 부모에 한 번만. **자식마다 다른 것(이름)만** 추상 메소드로 남김 |
| `private int beads;` | 캡슐화. 자식도 직접 못 건드림 → `getBeads()` 로만 읽기 |
| `beads += n;` / `beads -= n;` | addBeads/removeBeads — 가장 쉬운 2개부터 |
| `return beads <= 0;` | `== 0` 이 아니라 `<= 0` (음수 테스트 2-3) |
| `return name() + "의 구슬 : " + beads;` | 부모가 자식의 name() 을 부름 = 다형성. 콜론 **앞뒤 공백** 정확히 |
| `int n = 5; if (getBeads() < 5) n = getBeads();` | min(5, 내 구슬). `Math.min(5, getBeads())` 도 OK |
| `(int)(Math.random() * n) + 1` | 0.0~0.999 × n → 0 ~ n-1 → +1 해서 **1 ~ n** |
| `(int)(Math.random() * (n + 1))` | **0 ~ n** (0개를 쥐는 것도 가능) |
| `if (playerEven == isEven(computerGuard))` | 내가 외친 홀짝과 실제 홀짝이 같으면 맞힌 것. boolean 끼리 `==` 비교 |
| 이긴 쪽 `add`, 진 쪽 `remove` 둘 다 | 구슬 이동은 **양쪽 모두** 바꿔야 함 (6-2, 7-1 이 둘 다 검사) |
| `computerTurn` 은 조건이 반대 | 컴퓨터가 맞히면(홀짝 같음) **당신이 진 것** |

### 짜는 순서 (30분)
1. (3분) `Gamer` 의 addBeads / removeBeads / lose → 컴파일 + tester → 1-1 ~ 2-3 OK 확인
2. (4분) status → 3-1 ~ 3-3 OK. 문자열은 테스트 이름에서 **복사**해서 비교
3. (8분) Computer 의 pickForAttack / pickForGuard → 4-x, 5-x OK. 범위가 틀리면 "양 끝값" 항목이 FAIL
4. (10분) playerTurn → 6-x OK 후, 같은 모양으로 computerTurn (조건만 반대) → 7-x
5. (나머지) 전체 20/20 확인. FAIL 항목은 이름에 적힌 기대값과 내 리턴값을 `System.out.println` 으로 비교

## ② P2Dice — 주사위 칩 게임

| 코드 | 왜? |
|---|---|
| `return chips <= 0;` | P1 lose 와 같은 모양 |
| `return name() + "의 칩 : " + chips;` | P1 status 와 같은 모양, 단어만 바꿈 |
| `(int)(Math.random() * 6) + 1` | 1 ~ 6 |
| `if (>) … else if (<) … else "무승부"` | 세 갈래. 무승부일 때는 칩 변화 없음 |
| `"딜러 승! -" + bet` | 마이너스 기호는 문자열에 직접 |
| `int max = 10;` 에서 두 번 줄이기 | 셋 중 최솟값 = 하나씩 비교하며 더 작으면 교체 |

### 짜는 순서 (20분)
1. P1 답을 옆에 띄우고 isBroke, status 를 옮겨 쓰기 → 1-x, 2-x OK
2. rollDice → 3-x OK
3. round → 4-x OK (메시지 3개 정확히)
4. maxBet → 12/12

## 자주 하는 실수
- Computer 안에서 `beads` 를 직접 씀 → **컴파일 에러로 tester 전체 0점**
- `Math.random() * 5 + 1` 을 `(int)` 없이 리턴 → double 이라 컴파일 에러
- 이긴 쪽만 add 하고 진 쪽 remove 를 빼먹음 → 6-2 / 7-1 FAIL
