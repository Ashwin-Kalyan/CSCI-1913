/*
 * CSCI 1913
 * Lab 06 
 * Author: Ashwin Kalyan
 * */


public class NIM {
    /**
     * cerate a game state array
     * @param size -- the number of rows
     * @param stickMax -- the largest value
     * @return an array representing a token array. The array in the first position will be one, each following will be
     *     one larger up to the max.
     */
    public static int[] createGameState(int size, int stickMax){
        int[] game_state = new int[size];
        
        if (size == 0) {
            return new int[0]; // return an empty array if size is 0
        }

        for (int i = 0; i < size; i++) {
            if (i + 1 <= stickMax) { 
                game_state[i] = i + 1; // if the position hasn't reached the max token value, set to i
            } else if (i >= stickMax) {
                game_state[i] = stickMax; // if so, set the value to max token value 
            }
       }

        return game_state;
    }

    /**
     * This provided function operators similarly to the python isDigit method. You give it a string and it will tell
     * you if the string contains only digits. Give it a read -- the actual design isn't hard, basically a linear search.
     * @param str any string
     * @return true if all letters in the string are digits.
     */
    private static boolean isDigit(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if a given move (as represented by two input strings) is valid. This should check if the inputs are numbers
     * if those numbers are in the valid range.
     * @param gameState an array representing the number of tokens in each row.
     * @param row a string representing which row the user wants to take from. 1-indexed.
     * @param takes a string representing how many tokens the user wants to take.
     * @return true if and only if the move would be valid
     */
    public static boolean isValidMove(int[] gameState, String row, String takes) {
        if (!isDigit(row) || !isDigit(takes)) {
            return false;
        }

        int row_int = Integer.parseInt(row);
        int takes_int = Integer.parseInt(takes);

        if (row_int < 1 || row_int > gameState.length) { // Check if the row is within the valid range
            return false;
        }

        if (takes_int < 1 || takes_int > 3) { // Check if the number of tokens to take is between 1 and 3
            return false;
        }

        if (takes_int > gameState[row_int - 1]) { // Check if the number of tokens to take is not greater than the tokens in the row
            return false;
        }

        return true; // If all checks pass, the move is valid
    }

    /**
     * User System.out.println to represent a token grid to the program user.
     * @param gameState an array representing the number of tokens in each row.
     */
    public static void drawGameState(int[] gameState){
        System.out.println("====================");

        for (int i = 0; i < gameState.length; i++) {
            System.out.print(i + 1 + " "); // Print the row number
            for (int j = 0; j < gameState[i]; j++) {
                System.out.print("#"); // Print the number of tokens in the row
            }
            System.out.println();
        }

        System.out.println("====================");
    }


    /**
     * Create an updated version of the game state. You can assume the row and takes are valid for the gameState array provided.
     * @param gameState an array representing the number of tokens in each row.
     * @param row the row the user wants to take from (0-indexed)
     * @param takes the number of tokens the user wants to take
     * @return a new array representing the state number of tokens in each row after the given numbers were removed.
     */
    public static int[] update(int[] gameState, int row, int takes){
        int[] updatedGameState = new int[gameState.length]; // Create a new array with the same length as gameState

        // Copy the contents of gameState into updatedGameState
        for (int i = 0; i < gameState.length; i++) {
            updatedGameState[i] = gameState[i];
        }

        // Update the specified row by subtracting the takes value
        updatedGameState[row] = updatedGameState[row] - takes;

        return updatedGameState;
    }

    /**
     *
     * @param gameState an array representing the number of tokens in each row.
     * @return true if and only if every element of gameState is false.
     */
    public static boolean isOver(int[] gameState) {
        for (int i = 0; i < gameState.length; i++) {
            if (gameState[i] != 0) {
                return false; // If any row has tokens, the game is not over
            }
        }

        return true; // If all rows are empty, the game is over
    }
}