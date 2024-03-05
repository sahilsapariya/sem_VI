#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <semaphore.h>

#define BUFFER_SIZE 5
#define MAX_ITEMS 20

int buffer[BUFFER_SIZE];

int in = 0;
int out = 0;
int produced_count = 0;
int consumed_count = 0;

sem_t mutex;
sem_t full;
sem_t empty;

void* producer (void* arg) {

    int item = 1;

    while (produced_count < MAX_ITEMS) {
        sem_wait(&empty); 
        sem_wait(&mutex);
        
        int *f= (int *) (arg);
        buffer[in] = item;

        printf("Produced: %d \n", *(int *)arg, item);
        item++;
        in = (in + 1) % BUFFER_SIZE;

        produced_count++;

        sem_post(&mutex);
        sem_post(&full);
    }

    pthread_exit(NULL);
}

void* consumer(void * arg) {
    while (consumed_count < MAX_ITEMS) {
        sem_wait(&full);
        sem_wait(&mutex);

        int *f = (int *) (arg);
        int item = buffer[out];
        printf("Consumed %d: %d \n", *f, item);

        out = (out + 1) % BUFFER_SIZE;

        consumed_count++;

        sem_post(&mutex);
        sem_post(&empty);
    }

    pthread_exit(NULL);
}

int main() {
    pthread_t producer_thread[10], consumer_thread[10];

    sem_init(&mutex, 0, 1);
    sem_init(&full, 0, BUFFER_SIZE);
    sem_init(&empty, 0, BUFFER_SIZE);

    for (int i = 0; i < 10; i++) {
        pthread_create(&producer_thread[i], NULL, producer, &i);
        pthread_create(&consumer_thread[i], NULL, consumer, (void *) &i);

        pthread_join(producer_thread[i], NULL);
        pthread_join(consumer_thread[i], NULL);
    }

    sem_destroy(&mutex);
    sem_destroy(&full);
    sem_destroy(&empty);

    return 0;
}