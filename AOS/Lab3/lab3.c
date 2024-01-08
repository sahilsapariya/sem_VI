#include<stdio.h>
#include<unistd.h>
#include<sys/wait.h>


int main() {
    printf("The parent process id: %d\n", getpid());

    {
        int a = fork();
        if (a == -1) {
            printf("Failed to fork/n");
        } else if (a == 0) {
            char *args[] = {"./program1", NULL};
            execv(args[0], args);
        } else {
            wait(NULL);
            printf("Inside the parent process\n");
            printf("2 + 3 = %d\n", 2 + 3);
        }
    }

    printf("Main program executed\n");

    return 0;
}