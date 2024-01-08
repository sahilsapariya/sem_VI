# Understand the makefile

Create two files named `square.c` and `circle.c`.

**square.c**

```c
#include<stdio.h>

int main() {
    double side, result;

    // Taking the side from user
    printf("Enter the side of square: ");
    scanf("%lf", &side);

    // calculating the result
    result = side * side;

    // printing the result
    printf("Area of square is: %.2lf\n", result);

    return 0;
}
```

**circle.c**

```c
#include<stdio.h>

int main() {
    double radius, result;

    // Taking the side from user
    printf("Enter the radius of circle: ");
    scanf("%lf", &radius);

    // calculating the result
    result = radius * radius * 3.14;

    // printing the result
    printf("Area of circle is: %.2lf\n", result);

    return 0;
}
```

Now create a makefile which will include the commands for running and compiling circle and square individually and combinied both

**makefile**

```makefile
.PHONY : circle square

circle: ccompile crun
	@echo "execution completed for circle"

ccompile:
	@gcc circle.c -o circle
crun:
	@./circle


square: scompile srun
	@echo "execution completed for square"
	
scompile:
	@gcc square.c -o square

srun:
	@./square

clean:
	@rm -f circle.c circle square square.c

all:
	@gcc circle.c -o circle
	@gcc square.c -o square
	@./circle
	@./square

.DEFAULT_GOAL := all
```

Run command `make circle` to compile and run the circle.c fiel and same for square, run `make square` command.

To compile and run both the files, run `make all` command. But here we have declared the all target as default target. So `make all` is same as `make` command only.

`clean` target is used for deleting all the program and executable files.

---

> `make circle`

```
Enter the radius of circle: 5
Area of circle is: 78.50
execution completed for circle
```

> `make square`

```
Enter the side of square: 5
Area of square is: 25.00
execution completed for square
```

> `make all` or `make`

```
Enter the radius of circle: 3
Area of circle is: 28.26
Enter the side of square: 5
Area of square is: 25.00
```