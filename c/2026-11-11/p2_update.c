#include <stdio.h>
#include <string.h>

#define MAX 10

struct Student {
    char name[20];
    int score;
};

/* P1 에서 그대로 복사해 오세요 */
void print_student(const struct Student *s)
{
    /* TODO */
}

/* P1 에서 그대로 복사해 오세요 */
int find_by_name(const struct Student s[], int n, const char *name)
{
    /* TODO */
    return -1;
}

/* s 가 가리키는 학생의 점수에 delta 를 더한다. 결과가 100 보다 크면 100, 0 보다 작으면 0 */
void add_score(struct Student *s, int delta)
{
    /* TODO */
}

int main()
{
    struct Student s[MAX];
    int n, m;

    /* TODO: n, 학생 n명 입력 */

    /* TODO: m, 그다음 m줄 "이름 변화량" 을 하나씩 처리
             있으면 "Kim: 85 -> 90", 없으면 "Not found: Choi" */

    /* TODO: "Final:" 다음 줄부터 모든 학생을 입력 순서대로 출력 */

    return 0;
}
