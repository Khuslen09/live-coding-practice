#include <stdio.h>
#include <string.h>
#include <ctype.h>

/* 0: OK, 1: length, 2: start, 3: char, 4: digit */
int check_id(char s[])
{
    int len = strlen(s);
    int i, has_digit = 0;

    if (len < 4 || len > 12)
        return 1;
    if (!isalpha(s[0]))
        return 2;
    for (i = 0; i < len; i++) {
        if (!isalnum(s[i]))
            return 3;
        if (isdigit(s[i]))
            has_digit = 1;
    }
    if (!has_digit)
        return 4;
    return 0;
}

int main()
{
    char *reason[] = { "", "length", "start", "char", "digit" };
    char id[101], longest[101];
    int n, i, r, valid = 0;

    longest[0] = '\0';
    scanf("%d", &n);
    for (i = 1; i <= n; i++) {
        scanf("%100s", id);
        r = check_id(id);
        if (r == 0) {
            printf("#%d OK\n", i);
            valid++;
            if (strlen(id) > strlen(longest))   /* > : 같으면 먼저 나온 것 유지 */
                strcpy(longest, id);
        } else {
            printf("#%d INVALID %s\n", i, reason[r]);
        }
    }
    printf("Valid: %d\n", valid);
    if (valid == 0)
        printf("Longest: none\n");
    else
        printf("Longest: %s\n", longest);
    return 0;
}
