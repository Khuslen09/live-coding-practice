#include <stdio.h>

char to_grade(int score)
{
    if (score < 0 || score > 100) return '?';
    if (score >= 90) return 'A';
    if (score >= 80) return 'B';
    if (score >= 70) return 'C';
    if (score >= 60) return 'D';
    return 'F';
}

int main()
{
    int n, i, score, pass = 0;
    char g;

    scanf("%d", &n);
    for (i = 0; i < n; i++) {
        scanf("%d", &score);
        g = to_grade(score);
        if (g == '?') {
            printf("%d -> invalid\n", score);
        } else {
            printf("%d -> %c\n", score, g);
            if (g != 'F') pass++;
        }
    }
    printf("Pass: %d\n", pass);
    return 0;
}
