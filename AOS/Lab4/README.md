# Lab4 | Study of open(), write(), read(), close() and lseek() system calls

**myfile.txt**

```
0123456789abcdefghijklmnopqrstuvwxyz
```

**lab4.c**

```c
#include<stdio.h>
#include<unistd.h>
#include<sys/wait.h>
#include<sys/types.h>
#include<fcntl.h>

int main() {
    char buf[36];
    int fd; // file descriptor

    fd = open("./myfile.txt", O_RDWR);
    read(fd, buf, sizeof(buf));
    write(1, buf, sizeof(buf));

    printf("\n");

    read(fd, buf, sizeof(buf));
    write(1, buf, sizeof(buf));

    return 0;
}
```

**Output:**

```
0123456789abcdefghijklmnopqrstuvwxyz
0123456789abcdefghijklmnopqrstuvwxyz
```

Now let's modify the read and write functions

**lab4.c**

```c
#include<stdio.h>
#include<unistd.h>
#include<sys/wait.h>
#include<sys/types.h>
#include<fcntl.h>

int main() {
    char buf[36];
    int fd; // file descriptor

    fd = open("./myfile.txt", O_RDWR);
    read(fd, buf, 10);
    write(1, buf, 10);

    printf("\n");

    read(fd, buf, 10);
    write(1, buf, 10);

    return 0;
}
```

**Output:**

```
0123456789
abcdefghij
```

```c
#include<stdio.h>
#include<unistd.h>
#include<sys/wait.h>
#include<sys/types.h>
#include<fcntl.h>

int main() {
    char buf[36];
    int fd; // file descriptor

    fd = open("./myfile.txt", O_RDWR | O_CREAT);
    read(fd, buf, 10);
    write(1, buf, 10);

    printf("\n");

    // lseek() will shift the pointer to the right side by 5
    lseek(fd, 5, SEEK_CUR);
    read(fd, buf, 10);
    write(1, buf, 10);

    printf("\nFile Descriptor: %d\n", fd);
    return 0;
}
```

**Output:**

```
0123456789
fghijklmno
File Descriptor: 3
```

> Here value of fd is 3 because fd 0, 1 and 2 are reserved for system functions like 0 for read(), 1 for write() and 2 for other functions. So everytime our value of file descriptor will be equal or greater than 3