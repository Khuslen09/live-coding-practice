#include <stdio.h>

int main(void)
{
    // TODO: n 입력
    int i, n , score;
    int sum = 0, max = 0, min = 100;

    scanf("%d", &n);
    // TODO: n개의 점수를 읽으며 합계·최고·최저 갱신
    if(n == 0) {
        printf("No Score");
        return 0;
    }

    for(i = 1; i <= n; i++) {
        scanf("%d", &score);
        sum += score;
        if(max < score) max = score;
        if(min > score) min = score;
    }
    // TODO: 결과 출력
    printf("Average: %.2f\n", (double)sum / n);
    printf("Max:%d\n", max);
    printf("Min:%d\n", min);

    return 0;
}
