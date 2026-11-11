#include <stdio.h>
#include <string.h>

#define MAX 10

struct Student {
    char name[20];
    int score;
};

/* 어제와 같음: "Kim (85)" — 줄바꿈은 부르는 쪽에서 */
void print_student(const struct Student *s)
{
    printf("%s (%d)", s->name, s->score);
}

/* 이름이 "정확히" 같은 학생의 인덱스, 없으면 -1 */
int find_by_name(const struct Student s[], int n, const char *name)
{
    int i;
    for (i = 0; i < n; i++)
        if (strcmp(s[i].name, name) == 0)   /* == 가 아니라 strcmp, 0 이면 같음 */
            return i;                       /* 찾자마자 바로 리턴 */
    return -1;                              /* 반복이 다 끝나야 "없음" */
}

int main()
{
    struct Student s[MAX];
    char query[20];
    int n, i, idx;

    scanf("%d", &n);
    for (i = 0; i < n; i++)
        scanf("%s %d", s[i].name, &s[i].score);
    scanf("%s", query);

    idx = find_by_name(s, n, query);
    if (idx == -1)
        printf("Not found: %s\n", query);
    else {
        printf("Found: ");
        print_student(&s[idx]);
        printf("\n");
    }
    return 0;
}
