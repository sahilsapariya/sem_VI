#include<stdio.h>
#include<stdlib.h>
#include<fcntl.h>
#include<unistd.h>
#include<sys/shm.h>
#include<sys/stat.h>
#include<sys/mman.h>

int main() {

    const int SIZE = 4096;
    void *ptr;
    int shm_fd;

    shm_fd = shm_open("shared", O_CREAT | O_RDWR, 0777);
    ftruncate(shm_fd, SIZE);
    ptr = mmap(NULL, SIZE, PROT_WRITE, MAP_SHARED, shm_fd, 0);
    write(shm_fd, "Hello from the source", SIZE);

    printf("inserted\n");

    return 0;
}