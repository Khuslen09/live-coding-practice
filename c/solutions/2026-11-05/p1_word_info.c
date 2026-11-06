#include <stdio.h>

int my_strlen(char s[])
{
    int len = 0;
    while (s[len] != '\0')
        len++;
    return len;
}

int main()
{
    char word[100];
    int len, i;

    scanf("%99s", word);
    len = my_strlen(word);

    printf("Length: %d\n", len);

    printf("Reverse: ");
    for (i = len - 1; i >= 0; i--)
        printf("%c", word[i]);
    printf("\n");

    for (i = 0; i < len; i++)
        if (word[i] >= 'a' && word[i] <= 'z')
            word[i] = word[i] - 'a' + 'A';
    printf("Upper: %s\n", word);

    return 0;
}
