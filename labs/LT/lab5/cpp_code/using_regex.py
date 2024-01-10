import re

def tokenize_c_code(code):
    # Use a regular expression to tokenize C code
    tokens = re.findall(r'\b\w+\b|[^\w\s]', code)
    return tokens

def is_keyword(word):
    keywords = ["int", "float", "char", "if", "else", "while", "for", "return"]
    return word in keywords

def is_identifier(word):
    return re.match(r'^[a-zA-Z_][a-zA-Z0-9_]*$', word) is not None

def is_operator(char):
    operators = ['+', '-', '*', '/', '=', '<', '>', '!', '&', '|']
    return char in operators

def is_separator(char):
    separators = [';', ',', '(', ')', '{', '}']
    return char in separators

def is_constant(word):
    try:
        int(word)
        return True
    except ValueError:
        try:
            float(word)
            return True
        except ValueError:
            return False

def main():

    with open("labs/LT/lab5/cpp_code/example.c") as f:
        user_code = f.read()


    tokens = tokenize_c_code(user_code)

    for token in tokens:
        if is_keyword(token):
            print(f"Keytoken: {token}")
        elif is_identifier(token):
            print(f"Identifier: {token}")
        elif len(token) == 1 and is_operator(token):
            print(f"Operator: {token}")
        elif len(token) == 1 and is_separator(token):
            print(f"Separator: {token}")
        elif is_constant(token):
            print(f"Constant: {token}")

if __name__ == "__main__":
    main()
