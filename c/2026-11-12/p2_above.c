#include <stdio.h>
#include <stdlib.h>

struct Student {
    char name[20];   /* 공백 없는 이름, 19자 이하 */
    int score;
};

/* Day 9~10 과 같음: "Kim (85)" — 줄바꿈 없음 */
void print_student(const struct Student *s)
{
    /* TODO */
}

/* 학생 n 명의 평균 점수 (소수점까지) */
double average(const struct Student *s, int n)
{
    /* TODO */
    return 0.0;
}

int main()
{
    int n;
    struct Student *s;

    /* TODO: n 입력 → malloc 으로 struct Student n 개 공간 (실패하면 "Memory error" 후 return 1) */

    /* TODO: 학생 n 명 입력 */

    /* TODO: "Average: 85.00" (소수 둘째 자리) */

    /* TODO: "Above average:" 다음 줄부터 평균보다 "높은" 학생만 입력 순서대로
             한 명도 없으면 "(none)" */

    /* TODO: free */

    return 0;
}
