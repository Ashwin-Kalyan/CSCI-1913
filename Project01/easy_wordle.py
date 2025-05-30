# @author Ashwin Kalyan

from wordle import check_word
import random
import display_utility
from words import words

def filter_word_list(words, clues):
    """
    Creates a new list of potential secret words from the guess clues.

    Args:
        words (list): A list of words.
        clues (list): A list of tuples, with each tuple being a guess (string) and the clues returned.
    Return:
        list: A new list containing only which words could be the secret word based off earlier clues. 
    """
    filtered_words = []
    
    for word in words:
        # This will be our match checker
        is_match = True

        for guess, clue in clues:
            # Use the check_word function to see if the word generates the same clue, uppercase to make it compatible with check_word requirements
            generated_clue = check_word(word.upper(), guess.upper())
            
            # If the generated clue doesn't match the given clue, the word is not a match
            if generated_clue != clue:
                is_match = False
                break
        
        # If the word matches all clues, add it to the filtered list
        if is_match:
            filtered_words.append(word)
    
    return filtered_words

if __name__ == "__main__":
    """
    Easy Wordle clone main game loop. Generates clues, displays the UI, and accounts for the attempt limit.
    """
    secret_word = random.choice(words).upper()
    clues = []  

    for attempt in range(6):  # Allows 6 guesses max
        while True:
            guess = input("> ").strip().lower()  # Get user input and format it
            if len(guess) != 5:
                print("Word must be 5 letters long")
            elif guess not in words:
                print("Invalid word")
            else:
                break  # Valid guess, exit the loop

        guess = guess.upper()

        # Generate the clue for the guess
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

        # Filter the word list based on the clues
        possible_words = filter_word_list(words, clues)
        num_possible_words = len(possible_words)

        print(f"Possible words remaining: {num_possible_words}")

        if num_possible_words > 0:
            sample_size = min(5, num_possible_words)
            sample_words = random.sample(possible_words, sample_size)
            print(f"{num_possible_words} words possible:")

            for word in sample_words:
                print(f"{''.join(word)}")

        # Check if the guess is correct
        if guess == secret_word:
            print(f"Answer: {secret_word}")
            break
    else:  # If the loop completes without breaking, the user ran out of guesses
        print(f"Answer: {secret_word}")