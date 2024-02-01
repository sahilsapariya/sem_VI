#include <iostream>
#include <cstdlib>
#include <string>

// Forward declaration
int expr();

// Function to handle the 'if' statement
void ifStatement() {
    std::string token;
    std::cin >> token;

    if (token != "if") {
        std::cerr << "Syntax error: 'if' expected." << std::endl;
        exit(EXIT_FAILURE);
    }

    int condition = expr();

    std::cin >> token;
    if (token != "then") {
        std::cerr << "Syntax error: 'then' expected." << std::endl;
        exit(EXIT_FAILURE);
    }

    if (condition) {
        std::cout << "Then branch executed." << std::endl;
    } else {
        std::cout << "Else branch executed." << std::endl;
    }
}

// Function to parse arithmetic expressions
int expr() {
    int result = 0;
    std::string token;
    std::cin >> token;

    if (token == "(") {
        result = expr();
        std::cin >> token;
        if (token != ")") {
            std::cerr << "Syntax error: ')' expected." << std::endl;
            exit(EXIT_FAILURE);
        }
    } else if (std::isdigit(token[0])) {
        result = std::stoi(token);
    } else {
        std::cerr << "Syntax error: Number or '(' expected." << std::endl;
        exit(EXIT_FAILURE);
    }

    return result;
}

int main() {
    std::cout << "Enter an expression: ";
    ifStatement();
    return 0;
}
