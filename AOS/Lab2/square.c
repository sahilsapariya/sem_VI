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