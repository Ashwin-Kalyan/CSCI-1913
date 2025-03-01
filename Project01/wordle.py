def check_word(secret, guess):
    output = [''] * len(guess) # Establish an output list of same length as guess
    secret_list = list(secret)
    guess_list = list(guess)

    # First round: mark all the green letters
    for i in range(len(guess)):
        if guess_list[i] == secret_list[i]:
            output[i] = 'green'
            secret_list[i] = None  # If marked as green, marks letter as used

    # Second round: mark the yellow letters
    for i in range(len(guess)):
        if output[i] == '':  # Only consider letters not already marked as green
            if guess_list[i] in secret_list:
                output[i] = 'yellow'
                # Mark the first occurrence of this letter in the secret as used
                secret_list[secret_list.index(guess_list[i])] = None
            else:
                output[i] = 'grey'

    return output
 
def known_word(clues):
    known = list("_____")  

    for guess, clue in clues:  # Iterate over each (guess, clue) pair
        for i in range(len(clue)):  # Iterate over each position in the clue
            if clue[i] == 'green':  
                known[i] = guess[i]

    return ''.join(known)  # Convert the list back to a string
        
def no_letters(clues):
    no_letters_set = set()  # Use a set to avoid duplicates
    yes_letters_set = set()  # Track letters that are confirmed to be in the word

    for guess, clue in clues:  # Iterate over each (guess, clue) pair
        for i in range(len(clue)):  # Iterate over each position in the clue
            if clue[i] == 'grey':
                # Only add the letter if it's not in the yes_letters_set
                if guess[i] not in yes_letters_set:
                    no_letters_set.add(guess[i])
            elif clue[i] == 'green' or clue[i] == 'yellow':
                # If the letter is green or yellow, it's in the word
                yes_letters_set.add(guess[i])

    # Remove any letters from no_letters_set that are in yes_letters_set
    no_letters_set -= yes_letters_set

    # Sort alphabetically, convert the set to a sorted list, then to a string, then uppercase all letters
    no_letters_list = sorted(no_letters_set)
    return ''.join(no_letters_list).upper()