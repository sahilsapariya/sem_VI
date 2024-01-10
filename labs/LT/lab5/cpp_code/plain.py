letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
digits = "0123456789"
special_letters = "!@#$%^&*"
relational_operators = ['>', '<', '<=', '>=', '<>', '!=', '=', '==']
seperators = "(){}[];,"

keywords = [
    "auto", "break", "case", "char", "const", "continue", "default", "do", 
    "else", "enum", "extern", "for", "goto", "if", "inline", "int", "long", 
    "register", "return", "short", "signed", "sizeof", "static", "struct", 
    "switch", "typedef", "union", "unsigned", "void", "volatile", "while"
]

def is_identifier(word) -> bool:  
    if word[0] in letters or word[0] == '_':
        if word in keywords: return False
        return True
    else: return False
    
def is_number(token) -> bool: 

    for char in token:
        if char in digits: continue
        else: return False
    
    return True



def tokenize_code(code) -> list:
    tokens = []
    current_token = ""
    
    for char in code:
        if char in {' ', '\n', '\t'}:
            if current_token:
                tokens.append(current_token)
                current_token = ""
        else:
            current_token += char

    if current_token: tokens.append(current_token)
    return tokens


def identify_code(code):
    tokens = tokenize_code(code)

    for token in tokens:
        if token in keywords: print("Keyword: ", token)
        elif token in seperators: print("Separator: ", token)
        elif token in relational_operators: print("Relational operator: ", token)
        elif is_identifier(token): print("Identifier: ", token)
        elif is_number(token): print("Number: ", token)
        else: print("Unknown token: ", token)


def main():
    # int a = 5;
    user_input = input("Enter a C language line: ")
    identify_code(user_input)


if __name__ == "__main__":
    main()

