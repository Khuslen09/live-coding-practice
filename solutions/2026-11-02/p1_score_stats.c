#include <stdio.h>

int main()
{
    int n, i, score;
    int sum = 0, max = 0, min = 100;

    scanf("%d", &n);
    if (n <= 0) {
        printf("No scores\n");
        return 0;
    }

    for (i = 0; i < n; i++) {
        scanf("%d", &score);
        sum += score;
        if (score > max) max = score;
        if (score < min) min = score;
    }

    printf("Average: %.2f\n", (double)sum / n);
    printf("Max: %d\n", max);
    printf("Min: %d\n", min);
    return 0;
}
