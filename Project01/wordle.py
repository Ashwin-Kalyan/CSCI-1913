# @author Ashwin Kalyan

from words import words
import random
import display_utility

def check_word(secret, guess):
    """
    Checks each letter of the guessed word and compares it to the secret word. It then appends the results/clues to a list according to the rules of Wordle.

    Args:
        secret (str): The secret word that the player must guess. 
        guess (str): The word that the user guesses.
    Return:
        list: A list containing the accuracy result of each letter guessed when compared to the secret word. 
    """
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
    """
    Checks which guess letters are "green", or match the secret word, then appends that to a 5 letter string in their correct position.

    Args:
        clues (list): A list of tuples, with each tuple being a guess (string) and the clues returned.
    Return:
        str: A string containing correctly guessed and positioned letters in their correct positions, and "_" for incorrectly positioned/guessed letters. 
    """
    known = list("_____")  

    for guess, clue in clues:  # Iterate over each (guess, clue) pair
        for i in range(len(clue)):  # Iterate over each position in the clue
            if clue[i] == 'green':  
                known[i] = guess[i]

    return ''.join(known)  # Convert the list back to a string
        
def no_letters(clues):
    """
    Creates a string of letters guessed by the player that are not in the secret word.

    Args:
        clues (list): A list of tuples, with each tuple being a guess (string) and the clues returned.
    Return:
        str: A string containing which guessed letters are not in the secret word.
    """
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

def yes_letters(clues):
    """
    Creates a string of letters guessed by the player that are in the secret word, regardless of position.

    Args:
        clues (list): A list of tuples, with each tuple being a guess (string) and the clues returned.
    Return:
        str: A string containing which guessed letters are in the secret word.
    """
    yes_letters_set = set()  # Use a set to avoid duplicates

    for guess, clue in clues:  # Iterate over each (guess, clue) pair
        for i in range(len(clue)):  # Iterate over each position in the clue
            if clue[i] == 'green' or clue[i] == 'yellow':
                # Add the letter to the set if it's green or yellow
                yes_letters_set.add(guess[i])

    # Sort alphabetically, convert the set to a sorted list, then to a string, then uppercase all letters
    yes_letters_list = sorted(yes_letters_set)
    return ''.join(yes_letters_list).upper()

if __name__ == "__main__":
    """
    Wordle clone main game loop. Generates clues, displays the UI, and accounts for the attempt limit.
    """
    secret_word = random.choice(words).upper()

    guesses = []
    clues = []

    for attempt in range(6):  # Allows 6 guesses max
        print(f"Known: {known_word(clues)}")
        print(f"Green/Yellow Letters: {yes_letters(clues)}")
        print(f"Grey Letters: {no_letters(clues)}") 

        while True:
            guess = input("> ").strip().upper()  # Get user input and format it
            if len(guess) != 5:
                print("Word must be 5 letters long")
            elif guess.lower() not in words:  
                print("Invalid word")
            else:
                break  # Valid guess, exit the loop
        
        clue = check_word(secret_word, guess)
        clues.append((guess, clue))
        
        for guess, clue in clues: # Makes sure guess/clue history is shown with each guess
            for i in range(len(guess)):
                if clue[i] == "green":
                    display_utility.green(guess[i])
                elif clue[i] == "yellow":
                    display_utility.yellow(guess[i])
                else:
                    display_utility.grey(guess[i])
            print()  

        if guess == secret_word:
            print(f"Answer: {secret_word}")
            break
    else:  # If the loop completes without breaking, the user ran out of guesses
        print(f"Answer: {secret_word}")
