from wordle import check_word

def filter_word_list(words, clues):
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