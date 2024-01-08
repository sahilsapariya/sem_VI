# Lab3 | Study of fork(), execv() command

# fork() command



## execv() command

First make two files named `lab3.c` and `program1.c`

**lab3.c**

```c
#include<stdio.h>
#include<unistd.h>

int main() {
    printf("The parent process id: %d\n", getpid());
    char *args[] = {"./program1", NULL};
    execv(args[0], args);
    printf("Back to main program\n");

    return 0;
}
```

**program1.c**

```c
#include<stdio.h>
#include<unistd.h>

int main() {
    printf("Program 1 process id: %d\n", getpid());

    return 0;
}
```

> Now compile and run the programs

```
gcc program1.c -o program1
gcc lab3.c -o lab3
./lab3
```

**Output:**

```
The parent process id: 1644
Program 1 process id: 1644
```

> Here you can notice that after printing the program1 process id program is not returned to main program


## Now let's solve the above problem

Here we will use *fork()* to separat the execution of the processes. Modify the program accordingly

**lab3.c**

```c
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
            printf("Inside the parent process\n");
            printf("2 + 3 = %d\n", 2 + 3);
        }
    }

    printf("Main program executed\n");
    return 0;
}
```

**Output:**

```
The parent process id: 1917
Inside the parent process
2 + 3 = 5
Main program executed
Program 1 process id: 1918
```

> Execution of the parent process is done before the child process so to make parent process wait for child process we can use `wait(NULL)` in the parent process

So to make parent process wait for child process we can use `wait(NULL)` in parent process.


Update the `lab3.c` code like this:

```c
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
```

**Output:**

```
The parent process id: 1968
Program 1 process id: 1969
Inside the parent process
2 + 3 = 5
Main program executed
```

