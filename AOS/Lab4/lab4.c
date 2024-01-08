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