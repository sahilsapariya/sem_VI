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