# Lab 6

**Program 1**

Single Pipe program

```c
#include<unistd.h>
#include<stdio.h>

int main() {
    int p[2];
    int x = pipe(p);

    if(x == 0) {
        char buff[20];
        char *str = "hello from sahil";
        write(p[1], str, 20);
        sleep(2);
        read(p[0], buff, 20);
        printf("Buff: %s\n", buff);
    }
    else {
        printf("Error");
    }

    return 0;
}
```

__Output:__

```
Buff: Hello from sahil
```

**Program 2**

Double Pipe program


```c
#include<unistd.h>
#include<stdio.h>
#include<sys/wait.h>

int main() {
    int p1[2], p2[2];
    int x;
    int y = 5;
    pipe(p1);
    pipe(p2);

    int pid = fork();

    if(pid == 0) {
       close(p1[0]);
       close(p2[1]);
       read(p2[0], &x, 20);
       printf("X: %d\n", x);
       x = x + 4;
       write(p1[1], &x, 20);
       printf("X: %d\n", x);
       close(p2[0]);
       close(p1[1]);
    }
    else {
        close(p1[1]);
        close(p2[0]);
        write(p2[1], &y, 20);
        printf("Y: %d\n", y);
        wait(NULL);
        read(p1[0], &y, 20);
        close(p2[1]);
        close(p1[0]);
    }

    return 0;
}
```
__Output:__
```
Y: 5
X: 5
X: 9
```