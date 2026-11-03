#include <stdio.h>

int to_minutes(int hhmm)
{
    int h = hhmm / 100;
    int m = hhmm % 100;
    if (hhmm < 0 || h > 23 || m > 59) return -1;
    return h * 60 + m;
}

int calc_fee(int minutes)
{
    int over, units, fee;
    if (minutes <= 30) return 0;
    over = minutes - 30;
    units = (over + 9) / 10;   /* 10분 미만도 올림 */
    fee = units * 500;
    if (fee > 20000) fee = 20000;
    return fee;
}

int main()
{
    int n, i, in, out, t_in, t_out, minutes, fee;
    int total = 0;

    scanf("%d", &n);
    for (i = 1; i <= n; i++) {
        scanf("%d %d", &in, &out);
        t_in = to_minutes(in);
        t_out = to_minutes(out);
        if (t_in == -1 || t_out == -1 || t_out < t_in) {
            printf("#%d invalid\n", i);
            continue;
        }
        minutes = t_out - t_in;
        fee = calc_fee(minutes);
        total += fee;
        printf("#%d %d min %d won\n", i, minutes, fee);
    }
    printf("Total: %d won\n", total);
    return 0;
}
