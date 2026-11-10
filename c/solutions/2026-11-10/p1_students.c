#include <stdio.h>

#define MAX 10

struct Student {
    char name[20];
    int score;
};

/* 출력 형식 "Kim (85)" — 줄바꿈은 부르는 쪽에서 */
void print_student(const struct Student *s)
{
    printf("%s (%d)", s->name, s->score);
}

/* 최고 점수 인덱스. 동점이면 앞 학생 → "더 클 때만(>)" 교체 */
int top_index(const struct Student s[], int n)
{
    int best = 0, i;
    for (i = 1; i < n; i++)
        if (s[i].score > s[best].score)
            best = i;
    return best;
}

int main()
{
    struct Student s[MAX];
    int n, i;

    scanf("%d", &n);
    for (i = 0; i < n; i++)
        scanf("%s %d", s[i].name, &s[i].score);   /* name 은 배열 = 주소라 & 없음 */

    for (i = 0; i < n; i++) {
        print_student(&s[i]);
        printf("\n");
    }
    printf("Top: ");
    print_student(&s[top_index(s, n)]);
    printf("\n");
    return 0;
}
