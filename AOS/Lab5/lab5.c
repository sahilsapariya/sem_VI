#include<stdio.h>
#include<sys/types.h>
#include<pthread.h>
#include<unistd.h>

pthread_mutex_t mutex;
int count = 0;

void *routine() {
    printf("Thread created\n");
    
    pthread_mutex_lock(&mutex);
    int i;
    for(i=0; i<1000; i++) {
        count++;
    }
    pthread_mutex_unlock(&mutex);


    printf("Process id: %d\n", getpid());
    sleep(3);

    printf("Count value: %d\n", count);
    printf("Thread end\n");
}

int main() {
    pthread_t t1, t2;

    pthread_create(&t1, NULL, &routine, NULL);
    pthread_create(&t2, NULL, &routine, NULL);

    pthread_join(t1, NULL);
    pthread_join(t2, NULL);

    return 0;
}