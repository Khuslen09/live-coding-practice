#include <stdio.h>

int main()
{
    int n, i, age, fee;
    int visitors = 0, free = 0, total = 0;

    scanf("%d", &n);
    for (i = 1; i <= n; i++) {
        scanf("%d", &age);
        if (age < 0 || age > 120) {
            printf("#%d invalid\n", i);
            continue;
        }

        if (age <= 5) fee = 0;
        else if (age <= 12) fee = 1000;
        else if (age <= 18) fee = 1500;
        else if (age <= 64) fee = 3000;
        else fee = 0;

        printf("#%d %d won\n", i, fee);
        visitors++;
        if (fee == 0) free++;
        total += fee;
    }

    printf("Visitors: %d\n", visitors);
    printf("Free: %d\n", free);
    printf("Total: %d won\n", total);
    return 0;
}
