# Day 5 (2026-11-06 금) · C — 문자 다루기 (ctype.h · fgets)

⏱ 총 35분 · 50점 · AI·자동완성·검색 없이, 타이머 켜고!
채점: 이 폴더에서 `sh run_tests.sh` → 마지막 줄 `Score: x / 8`

오늘은 P1에서 쓰는 도구(`isalpha`, `isdigit`, `toupper/tolower`, 문자 하나씩 도는 반복)를
P2에서 **한 줄 전체 + 단어 단위**로 키워서 다시 써요.

---

## 🔥 P1. 모음·자음·숫자 세기 (20점 · 10분) — `p1_vowel_count.c`

공백 없는 단어 하나(최대 99글자)를 입력받아 세 줄을 출력하세요.

- `int is_vowel(char c)` 를 구현해서 사용 (a e i o u, **대문자도 모음**)
- 영문자 중 모음이 아니면 자음, `'0'`~`'9'` 는 숫자, 그 밖의 기호는 세지 않음

```
[입력 1]
Programming
[출력 1]
Vowels: 3
Consonants: 8
Digits: 0

[입력 2]
ERICA2026
[출력 2]
Vowels: 3
Consonants: 2
Digits: 4
```

## 🧩 P2. 문장 정리기 (30점 · 25분) — `p2_sentence.c`

한 줄(공백 포함, 최대 200글자)을 **`fgets`** 로 읽어서:

1. `Words: 단어 수` — 단어는 공백(`' '`)으로 구분. 공백이 앞·뒤에 있거나 여러 개 연속일 수 있음
2. `Longest: 가장 긴 단어` — **입력에 쓰인 그대로**, 길이가 같으면 **먼저 나온 것**
3. `Title: ...` — 단어마다 첫 글자는 대문자, 나머지는 소문자로 바꾸고, 단어 사이를 **공백 하나**로 이어 붙인 문장
   (숫자·기호는 그대로. 단어가 1개 이상인 입력만 나와요)

```
[입력 1]
i love c programming
[출력 1]
Words: 4
Longest: programming
Title: I Love C Programming

[입력 2]
  hello   wORLD from c  
[출력 2]
Words: 4
Longest: hello
Title: Hello World From C
```
(입력 2는 앞에 공백 2개, 단어 사이 공백 3개, 끝에 공백 2개)

⚠️ 함정: `fgets` 는 끝의 `'\n'` 까지 같이 읽어요.

### 배점
| 문제 | 테스트 | 점수 |
|---|---|---|
| P1 | c_p1_1 ~ 4 | 각 5점 |
| P2 | c_p2_1 ~ 4 | 각 6점 + 코드 리뷰 6점 |
