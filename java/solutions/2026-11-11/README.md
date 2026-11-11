# Java 모범답안 — Day 10 (2026-11-11) 객체 배열 + 콘솔 MVC

문제: [`java/2026-11-11/README.md`](../../2026-11-11/README.md) · 원본: `Hyunha/2025COM2018` → `COM2018/src/chapters/ch07/VoteCount.java`
확인: P1Vote `VoteTester` **20/20**, P2Kiosk `KioskTester` **12/12**

```bash
cd java/solutions/2026-11-11/P1Vote/src
javac -encoding UTF-8 -d ../bin $(find . -name '*.java')
java -Djava.awt.headless=true -cp ../bin tester.VoteTester
```

## ① P1Vote — 투표 집계 (Candidate[] 객체 배열)

### 왜 이렇게 썼나
| 코드 | 왜? |
|---|---|
| `votes = votes + 1;` | addVote — 가장 쉬운 것부터 (1-1, 1-2 OK) |
| `candidates = new Candidate[names.length];` | **new ①**: 배열 칸만 만듦. 이때 칸은 전부 `null` |
| `candidates[i] = new Candidate(names[i]);` | **new ②**: 칸마다 객체를 넣어야 `get(0).getName()` 이 됨. 빠뜨리면 NullPointerException |
| `if (v >= 0 && v < candidates.length)` | 범위 검사는 항상 **두 개 다**. `v < length` 만 쓰면 -1 에서 예외 |
| `candidates[v].addVote();` | 표 세기는 Candidate 에게 메시지로 맡김 (Ballot 이 votes 필드를 직접 못 만짐 = 캡슐화) |
| `for (Candidate c : candidates) sum += c.getVotes();` | 향상된 for — 객체 배열 합계의 기본형 |
| `if (total() == 0) return -1;` | 0표면 "당선자 없음". 이미 만든 total() 재사용 |
| `best = 0` + `>` 비교 | 어제 C 의 top_index 와 같은 모양. `>=` 로 쓰면 동점일 때 뒤 번호가 됨 |
| `"후보 " + i + "번(" + ... + ")이 " + ... + "표를 득표하였습니다."` | 띄어쓰기 위치 정확히: `후보␣0번(Kim)이␣3표를␣득표하였습니다.` |
| `if (!ballot.vote(inputs[i])) break;` + `count++` | 원본 while 반복의 "범위 밖이면 끝" 규칙. vote 의 boolean 리턴을 그대로 활용 |

### 짜는 순서 (30분)
1. (2분) `addVote` → 컴파일 + tester → 1-1, 1-2 OK
2. (5분) `Ballot` 생성자 → 2-1 ~ 2-3 OK (여기서 new 두 번 확인)
3. (5분) `vote` → 3-1 ~ 3-4 OK
4. (5분) `total`, `winner` → 4-x, 5-x OK. 동점 테스트 5-3 꼭 확인
5. (5분) `report` → 실패하면 tester 의 기대 문자열과 한 글자씩 비교
6. (5분) `VoteController.run` → 7-1 ~ 7-4 OK → **20/20**
7. 남는 시간에 `view.VoteApp` 실행해서 콘솔로 0 1 1 2 9 입력해 보기

## ② P2Kiosk — 분식집 키오스크

### 왜 이렇게 썼나
| 코드 | 왜? |
|---|---|
| 생성자 = P1 Ballot 생성자와 같은 모양 | `new MenuItem[n]` + 칸마다 `new MenuItem(names[i], prices[i])` |
| `idx >= 0 && idx < items.length && qty >= 1` | 조건 3개를 `&&` 로. 하나라도 틀리면 false + 아무것도 안 바꿈 |
| `sum += m.getPrice() * m.getCount();` | 메뉴별 매출 합 |
| bestSeller: 1등을 먼저 찾고, 그 1등이 0개면 -1 | "다 0개" 를 따로 반복하지 않아도 됨 (P1 은 total()==0 으로, P2 는 이 방법으로 — 둘 다 OK) |
| `" x" + count + " = " + (price * count) + "원"` | 곱셈을 **괄호**로 묶어야 숫자 계산. 안 묶으면 `"... = 3000" + 3` 처럼 문자열이 이어붙음 |

### 짜는 순서 (20분)
1. P1 Ballot 을 옆에 두고 생성자·order 옮기기 → 1-x, 2-x OK
2. totalSales, bestSeller → 3-x, 4-x OK
3. line → 5-1, 5-2 OK → **12/12**

## 자주 하는 실수
- 생성자에서 `Candidate[] candidates = new Candidate[...]` 처럼 **타입을 다시 쓰기** → 필드가 아니라 지역 변수가 생겨서 필드는 null 그대로 (2-x 전부 FAIL + NPE)
- `report` 에서 `"번("` 를 `"번 ("` 로 → 6-x FAIL
- run 에서 범위 밖을 `continue` 로 건너뜀 → 7-2 FAIL (멈춰야 함)
