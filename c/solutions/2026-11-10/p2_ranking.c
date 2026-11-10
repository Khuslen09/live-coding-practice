#include <stdio.h>

#define MAX 10

struct Student {
    char name[20];
    int score;
};

/* P1 에서 그대로 복사 */
void print_student(const struct Student *s)
{
    printf("%s (%d)", s->name, s->score);
}

/* 구조체는 = 로 통째로 복사된다 → temp 하나로 맞바꾸기 */
void swap_student(struct Student *a, struct Student *b)
{
    struct Student temp = *a;
    *a = *b;
    *b = temp;
}

/* 버블 정렬 내림차순. "앞이 뒤보다 작을 때만(<)" 바꾸면 동점 순서 유지 */
void sort_by_score(struct Student s[], int n)
{
    int i, j;
    for (i = 0; i < n - 1; i++)
        for (j = 0; j < n - 1 - i; j++)
            if (s[j].score < s[j + 1].score)
                swap_student(&s[j], &s[j + 1]);
}

int main()
{
    struct Student s[MAX];
    int n, i;

    scanf("%d", &n);
    for (i = 0; i < n; i++)
        scanf("%s %d", s[i].name, &s[i].score);

    sort_by_score(s, n);

    printf("Ranking:\n");
    for (i = 0; i < n; i++) {
        printf("%d. ", i + 1);
        print_student(&s[i]);
        printf("\n");
    }
    return 0;
}
