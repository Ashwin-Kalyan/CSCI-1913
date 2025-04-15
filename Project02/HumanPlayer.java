// CSCI 1913 - Project 2
// @author: Ashwin Kalyan

import java.util.Scanner;

/**
 * Implementation of Player that allows a human to play ConnectFwar through the console.
 */
public class HumanPlayer implements Player {
    private Scanner scanner;
    
    public HumanPlayer() {
        scanner = new Scanner(System.in);
    }
    
    /**
     * Gets the human player's move choice from console input
     * @param card - The card to be played
     * @param board - The current game board
     * @return The column choice (0-3) or any other number to discard
     */
    public int nextMove(Card card, Board board) {
        // Print the card to be played
        System.out.println("Current card: " + card.toFancyString());
        
        // Print the current board state
        System.out.println("Current board:");
        System.out.println(board);
        
        // Get player input
        System.out.print("Enter the column you want to play, or any other number to discard: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a valid number!");
            scanner.next(); // Clear invalid input
        }
        return scanner.nextInt();
    }

    /**
     * Main method to allow playing a game with this player
     */
    public static void main(String[] args) {
        HumanPlayer player = new HumanPlayer();
        int score = ConnectFwar.play(player);
        System.out.println("Game over! Your score: " + score);
    }
}