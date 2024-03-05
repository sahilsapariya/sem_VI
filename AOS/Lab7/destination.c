#include<stdio.h>
#include<stdlib.h>
#include<fcntl.h>
#include<unistd.h>
#include<sys/shm.h>
#include<sys/stat.h>
#include<sys/mman.h>

int main() {

    
    const int SIZE = 4096;
    const char *name = "shared";
    void *ptr;
    int shm_fd;

    shm_fd = shm_open(name, O_RDONLY, 0666);
    ptr = mmap(0, SIZE, PROT_READ, MAP_SHARED, shm_fd, 0);

    printf("Received message from shared memory: %s\n", (char *)ptr);

    return 0;
}