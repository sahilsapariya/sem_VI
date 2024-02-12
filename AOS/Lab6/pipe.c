#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <fcntl.h>

#define MAX_BUF 1024

int main() {
    int fd[2];
    pid_t pid;
    char buf[MAX_BUF];

    /* create the pipe */
    if (pipe(fd) == -1) {
        perror("pipe");
        exit(EXIT_FAILURE);
    }

    /* fork a child process */
    pid = fork();
    if (pid == -1) {
        perror("fork");
        exit(EXIT_FAILURE);
    }

    if (pid == 0) { /* child process */
        /* close the read end of the pipe */
        close(fd[0]);

        /* write to the pipe */
        printf("Enter a file name: ");
        fgets(buf, MAX_BUF, stdin);
        buf[strcspn(buf, "\n")] = 0; // remove newline character
        write(fd[1], buf, strlen(buf));

        /* close the write end of the pipe */
        close(fd[1]);

        exit(EXIT_SUCCESS);
    } else { /* parent process */
        /* close the write end of the pipe */
        close(fd[1]);

        /* read from the pipe */
        read(fd[0], buf, MAX_BUF);
        printf("Received file name: %s\n", buf);

        /* open the file */
        FILE *file = fopen(buf, "r");
        if (file == NULL) {
            perror("fopen");
            exit(EXIT_FAILURE);
        }

        /* read the file content and display it */
        while (fgets(buf, MAX_BUF, file) != NULL) {
            printf("%s", buf);
        }

        /* close the file */
        fclose(file);

        /* close the read end of the pipe */
        close(fd[0]);
    }

    return 0;
}