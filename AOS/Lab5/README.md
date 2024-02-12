# Experiment 5

```c
#include<stdio.h>
#include<sys/types.h>
#include<pthread.h>
#include<unistd.h>

int count = 0;

void *routine() {
    printf("Thread created\n");

    int i;
    for(i=0; i<1000; i++) {
        count++;
    }

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
```

__Output: 1__
```
Thread created
Process id: 1803
Thread created
Process id: 1803
Count value: 2000
Thread end
Count value: 2000
Thread end
```

__Output: 2__
```
Thread created
Thread created
Process id: 1850
Process id: 1850
Count value: 1974
Thread end
Count value: 1974
Thread end
```

Here we have declared a shared variable named "count" and created two different threads and when we run the program we will get different results. Sometimes it is 2000 of count variable and sometimes it is less than 2000 of count variable

So we have to achive the process synchronization. Here we will be using mutex to achieve it

```c
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
```

__Output:__
```
Thread created
Thread created
Process id: 2092
Process id: 2092
Count value: 2000
Thread end
Count value: 2000
Thread end
```


## Producer Consumer Problem implementation in C

```c
#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

#define BUFFER_SIZE 5
#define MAX_ITEMS 5

int buffer[BUFFER_SIZE];
int in = 0;
int out = 0;
int produced_count = 0;
int consumed_count = 0;

pthread_mutex_t mutex;
pthread_cond_t full;
pthread_cond_t empty;

void* producer(void* arg) {
   int item = 1;

   while (produced_count < MAX_ITEMS) {
      pthread_mutex_lock(&mutex);

      while (((in + 1) % BUFFER_SIZE) == out) {
         pthread_cond_wait(&empty, &mutex);
      }

      buffer[in] = item;
      printf("Produced: %d\n", item);
      item++;
      in = (in + 1) % BUFFER_SIZE;

      produced_count++;

      pthread_cond_signal(&full);
      pthread_mutex_unlock(&mutex);
   }

   pthread_exit(NULL);
}

void* consumer(void* arg) {
   while (consumed_count < MAX_ITEMS) {
      pthread_mutex_lock(&mutex);

      while (in == out) {
         pthread_cond_wait(&full, &mutex);
      }

      int item = buffer[out];
      printf("Consumed: %d\n", item);
      out = (out + 1) % BUFFER_SIZE;

      consumed_count++;

      pthread_cond_signal(&empty);
      pthread_mutex_unlock(&mutex);
   }

   pthread_exit(NULL);
}

int main() {
   pthread_t producerThread, consumerThread;

   pthread_mutex_init(&mutex, NULL);
   pthread_cond_init(&full, NULL);
   pthread_cond_init(&empty, NULL);

   pthread_create(&producerThread, NULL, producer, NULL);
   pthread_create(&consumerThread, NULL, consumer, NULL);

   pthread_join(producerThread, NULL);
   pthread_join(consumerThread, NULL);

   pthread_mutex_destroy(&mutex);
   pthread_cond_destroy(&full);
   pthread_cond_destroy(&empty);

   return 0;
}
```

__Output:__

```
Produced: 1
Produced: 2
Produced: 3
Produced: 4
Consumed: 1
Consumed: 2
Consumed: 3
Consumed: 4
Produced: 5
Consumed: 5
```