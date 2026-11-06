# Day 4 (2026-11-05) Java 모범답안 + 풀이 과정

⚠️ 어제 시작 코드·tester 는 어제 받은 zip 안에 있어요. 이 폴더의 파일은 **메소드 본문을 비교·복사하는 용도**예요
(생성자 모양이 시작 코드와 조금 다를 수 있으니, 시작 코드에 **메소드 본문만** 옮겨서 tester 를 돌려 보세요).
자체 점검 13/13 통과.

---

## ① P1BankDB (원본: `COM2018/src/chapters/ch08/bankdb+/`)

### 왜 원본이 터지나?
원본 `IntegerKey.equals(Key k)` 는 `((IntegerKey)k)` 로 **무조건 캐스트**해요.
Database 에 StringKey 레코드가 섞이면 `findLocation` 이 모든 칸의 키와 비교하다가 StringKey 를 IntegerKey 로 바꾸려다 **ClassCastException**.

### 무엇을 왜 썼나
| 코드 | 왜? |
|---|---|
| `if (k instanceof IntegerKey)` 먼저 | "이 타입이 맞을 때만" 캐스트. `null instanceof X` 는 false 라서 null 검사도 공짜 |
| StringKey 는 `key.equals(...)` | String 내용 비교. `==` 는 같은 객체인지(주소) 비교라 `new String("kim")` 에서 틀림 |
| `base[i] != null &&` 를 **앞에** | 빈 칸(null)에서 `.getKey()` 하면 NullPointerException. `&&` 는 앞이 false 면 뒤를 안 봄(단락 평가) |
| `insert` 에서 `findLocation` → `findEmpty` 순서 | ① 중복이면 거절 ② 빈 칸 있으면 거기 ③ 없으면 늘리기. helper 를 먼저 만들어 두니 insert 는 조립만 |
| `new Record[base.length * 2]` 후 복사, `temp[base.length] = r` | 옛 배열 마지막 다음 칸 = 옛 길이. 마지막에 `base = temp` 로 바꿔 끼우기 |

### 짜는 순서
1. `IntegerKey.equals`, `StringKey.equals` (가장 작고 독립적) → tester 의 equals 항목 OK 확인
2. `findLocation`, `findEmpty` (helper) → 3. `find`, `delete` (helper 한 줄 사용) → 4. `insert` (조립)
→ **작은 부품 → 그걸 쓰는 메소드** 순서. 기말 tester 도 대개 이 순서로 항목이 나와요.

---

## ② P2Parking (변형)

| 코드 | 왜? |
|---|---|
| `abstract class Vehicle` + `abstract int fee(int hours)` | 차종마다 요금 공식이 달라서 부모는 "fee 가 있다"는 약속만 |
| Truck 생성자 첫 줄 `super(plate)` | 부모 필드(key)는 부모 생성자가 만들어요. 반드시 첫 줄 |
| Car `fee`: `f > MAX_FEE` 면 `MAX_FEE` | "최대 15000" = 계산 후 상한으로 자르기 |
| `leave` 에서 `slots[i].fee(hours)` | Car 인지 Truck 인지 몰라도 **실제 객체의 fee 가 불림(다형성)** → instanceof 필요 없음 |
| `leave` 순서: 검사 → 요금 계산 → 칸 비우기 | 칸을 먼저 null 로 만들면 fee 를 부를 객체가 사라져요 |
| `countTrucks`, `heaviestTruck` 에서만 instanceof | "Truck 만" 골라야 할 때, 그리고 `getTons()` 처럼 **자식에만 있는 메소드**를 부를 때 캐스트가 필요 |
| `best == null \|\| t.getTons() > best.getTons()` | 첫 Truck 은 무조건 best. `>` 라서 같으면 앞 칸 유지 |

### 짜는 순서
PlateKey.equals → Car.fee / Truck → findSlot → find → park → leave → countTrucks → heaviestTruck
