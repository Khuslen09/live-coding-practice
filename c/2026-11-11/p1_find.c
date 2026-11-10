#include <stdio.h>
#include <string.h>

#define MAX 10

struct Student {
    char name[20];
    int score;
};

/* "Kim (85)" 형식으로 출력 (줄바꿈 없음) */
void print_student(const struct Student *s)
{
    /* TODO */
}

/* 이름이 name 인 학생의 인덱스, 없으면 -1 */
int find_by_name(const struct Student s[], int n, const char *name)
{
    /* TODO */
    return -1;
}

int main()
{
    struct Student s[MAX];
    char query[20];
    int n;

    /* TODO: n, 학생 n명, 찾을 이름 query 를 입력받기 */

    /* TODO: 찾으면 "Found: Lee (92)", 없으면 "Not found: Choi" 출력 */

    return 0;
}
