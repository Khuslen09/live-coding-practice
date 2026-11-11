#include <stdio.h>
#include <string.h>

#define MAX 10

struct Student {
    char name[20];
    int score;
};

/* P1 그대로 */
void print_student(const struct Student *s)
{
    printf("%s (%d)", s->name, s->score);
}

/* P1 그대로 */
int find_by_name(const struct Student s[], int n, const char *name)
{
    int i;
    for (i = 0; i < n; i++)
        if (strcmp(s[i].name, name) == 0)
            return i;
    return -1;
}

/* 포인터로 받아야 main 의 원본 점수가 바뀐다 */
void add_score(struct Student *s, int delta)
{
    s->score += delta;
    if (s->score > 100)
        s->score = 100;
    if (s->score < 0)
        s->score = 0;
}

int main()
{
    struct Student s[MAX];
    char name[20];
    int n, m, i, delta, idx, old;

    scanf("%d", &n);
    for (i = 0; i < n; i++)
        scanf("%s %d", s[i].name, &s[i].score);

    scanf("%d", &m);
    for (i = 0; i < m; i++) {
        scanf("%s %d", name, &delta);
        idx = find_by_name(s, n, name);
        if (idx == -1) {
            printf("Not found: %s\n", name);
            continue;                       /* 다음 명령으로 */
        }
        old = s[idx].score;                 /* 바꾸기 "전에" 저장 */
        add_score(&s[idx], delta);
        printf("%s: %d -> %d\n", s[idx].name, old, s[idx].score);
    }

    printf("Final:\n");
    for (i = 0; i < n; i++) {
        print_student(&s[i]);
        printf("\n");
    }
    return 0;
}
