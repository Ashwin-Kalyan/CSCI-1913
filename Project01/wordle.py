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
        