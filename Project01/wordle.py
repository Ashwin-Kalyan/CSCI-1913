import display_utility

def check_word(secret, guess):
    output = ""

    for i in range(0, len(guess)):
        if guess[i] == secret[i]:
            output += display_utility.green(guess[i])
        elif guess[i] in secret:
            output += display_utility.yellow(guess[i])
        elif guess[i] not in secret:
            output += display_utility.grey(guess[i])

    print(output)

        