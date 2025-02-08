# These are the functions that take care of the calculations and mechanics of the NIM game
# @Author Ashwin Kalyan

def create_game_state(size, token_max):
    """
    This function initiates the game_state and stores all the relevant data for the game in a list, including the number of tokens per row. 

    Args:
        size (int): How many rows (elements) the game (game_state list) will have.
        token_max (int): The maximum amount of tokens per row.

    Return:
        list: A list of how many tokens are in each row, where a row corresponds to an index in the list.
    """
    
    global game_state 
    game_state = []

    adjusted_size = size + 1 # enables range() to properly count from 1 to size # without exclusion issues

    if size == 0:
        return []
    
    for i in range(1, adjusted_size):
        if i <= token_max: # checks if the position value has reached the max token value
            game_state.append(i) # if not, we want to append the position value
        elif i >= token_max:
            game_state.append(token_max) # if so, we want to append the max token value
    
    return game_state

def is_valid_move(game_state, row, takes):
    """
    This function makes sure a user's move does not violate any rules, including being out of bounds and taking a maximum of 3 tokens at a time.

    Args:
        game_state (list): A list of how many tokens are in each row, where a row corresponds to an index in the list.
        row (str): Which row the user wants to take from.
        takes (str): How many tokens the user wants to take.

    Return:
        bool: A boolean that determines whether the move is legal or not: True for it is, False for it is not.
    """

    if not row.isdigit() or not takes.isdigit(): # checks if the strings row and takes are valid digits
        return False
    
    row = int(row)
    takes = int(takes)

    if row < 1 or row > len(game_state): # check if the row is within the valid range (doesn't exceed the number of rows in game_state)
        return False
    
    if takes < 1 or takes > 3: # check if the number of tokens to take is between 1 and 3 per game rules
        return False

    if takes > game_state[row - 1]: # check if the number of tokens to take is not greater than the tokens in the row
        return False 

    return True # if all checks pass, the move is valid

def update(game_state, row, takes):
    """
    This function creates a copy of game_state and updates the values within it according to the user's move.

    Args:
        game_state (list): A list of how many tokens are in each row, where a row corresponds to an index in the list.
        row (int): Which row number needs to be updated.
        takes (int): How many tokens to take away.

    Return:
        list: An updated list of how many tokens are in each row, where a row corresponds to an index in the list.
    """
    
    updated_game_state = list(game_state) # create a new copy of the game_state list
    updated_game_state[row] = updated_game_state[row] - takes # in the copy, update the specified row by subtracting the current value by the takes value

    return updated_game_state

def draw_game_state(game_state):
    """
    Draws the game state in the form of the row number and its associated number of tokens.

    Args:
        game_state (list): A list of how many tokens are in each row, where a row corresponds to an index in the list.

    Return:
        None
    """
    print("=" * 20) # starting border

    for row in range(len(game_state)): # loops through each row in game_state
        print("{} ".format(row + 1) + "#" * game_state[row]) # this prints the row number adjusted by one (since we don't want a row 0), then prints the # needed according to the value stored at row in game_state

    print("=" * 20) # ending border

def is_over(game_state):
    """
    Checks if the game has ended by determining whether all rows in game_state are empty or not.

    Args:
        game_state (list): A list of how many tokens are in each row, where a row corresponds to an index in the list.

    Return:
        bool: A boolean that determines if a game is over or not.
    """
    for row in range(len(game_state)):
        if game_state[row] != 0: # checks if row is not empty
            return False
    
    return True