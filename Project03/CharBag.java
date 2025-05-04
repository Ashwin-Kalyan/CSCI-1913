import java.util.Random;

/**
 * CharBag is a class that represents a bag of characters, allowing for the addition,
 * removal, and retrieval of character counts. It also provides functionality to get a
 * random character from the bag.
 */
public class CharBag {
    private static final int ALPHABET_SIZE = 26;
    private static final char STOP_CHAR = '.';
    private int[] counts;
    private int size;
    private Random random;

    /**
     * Constructor for CharBag. Initializes the counts array and sets the size to 0.
     */
    public CharBag() {
        counts = new int[ALPHABET_SIZE + 1]; // 26 letters + 1 for STOP_CHAR
        size = 0;
        random = new Random();
    }

    /**
     * Adds a character to the CharBag. If the character is not a letter or is the STOP_CHAR,
     * it is normalized to STOP_CHAR.
     * @param character - the character to add
     */
    public void add(char character) {
        character = normalizeChar(character);
        int index = charToIndex(character);
        counts[index]++;
        size++;
    }

    /**
     * Normalizes the character to lowercase if it is an uppercase letter.
     * If it is not a letter, it is set to STOP_CHAR.
     * @param character - the character to normalize
     * @return - the normalized character
     */
    private char normalizeChar(char character) {
        if (character >= 'A' && character <= 'Z') return Character.toLowerCase(character);
        if (character < 'a' || character > 'z') return STOP_CHAR;
        return character;
    }

    /**
     * Converts a character to an index in the counts array.
     * 'a' to 'z' are mapped to 0-25, and STOP_CHAR is mapped to 26.
     * @param character - the character to convert
     * @return - the index in the counts array
     */
    private int charToIndex(char character) {
        if (character >= 'a' && character <= 'z') return character - 'a';
        return ALPHABET_SIZE; // the stop character goes last
    }

    /**
     * Removes a character from the CharBag. If the character is not a letter or is the STOP_CHAR,
     * it is normalized to STOP_CHAR.
     * If the character count is greater than 0, it decrements the count and size.
     * @param character - the character to remove
     */
    public void remove(char character) {
        character = normalizeChar(character);
        int index = charToIndex(character);
        if (counts[index] > 0) {
            counts[index]--;
            size--;
        }
    }

    /**
     * gets the count of a character in the CharBag. If the character is not a letter or is the STOP_CHAR,
     * @param character - the character to get the count of
     * @return - the count of the character in the CharBag
     */
    public int getCount(char character) {
        character = normalizeChar(character);
        int index = charToIndex(character);
        return counts[index];
    }

    public int getSize() {
        return size;
    }

    /** 
     * Returns a random character from the CharBag. The character is chosen based on the counts of each character.
     * If the CharBag is empty, it returns STOP_CHAR.
     * @return - a random character from the CharBag
     */
    public char getRandomChar() {
        if (size == 0) return STOP_CHAR;

        int randomIndex = random.nextInt(size);

        for (char i = 'a'; i <= 'z'; i++) {
            int index = charToIndex(i);
            randomIndex -= counts[index];
            if (randomIndex < 0) return i;
        }

        return STOP_CHAR;
    }

    /**
     * Returns a string representation of the CharBag, showing the counts of each character.
     * Format: {a:count, b:count, ..., z:count, .:count}
     * @return - a string representation of the CharBag
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("CharBag{");
        
        // Append letters a-z with their counts
        for (char i = 'a'; i <= 'z'; i++) {
            sb.append(i).append(":").append(getCount(i)).append(", ");
        }
        
        // Append STOP character count
        sb.append(".:").append(getCount(STOP_CHAR));
        
        sb.append("}");
        return sb.toString();
    }
}