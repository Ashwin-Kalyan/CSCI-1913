import java.util.Random;

public class CharBag {
    private static final int ALPHABET_SIZE = 26;
    private static final char STOP_CHAR = '.';
    private int[] counts;
    private int size;
    private Random random;

    public CharBag() {
        counts = new int[ALPHABET_SIZE + 1]; // 26 letters + 1 for STOP_CHAR
        size = 0;
        random = new Random();
    }

    public void add(char character) {
        character = normalizeChar(character);
        int index = charToIndex(character);
        counts[index]++;
        size++;
    }

    private char normalizeChar(char character) {
        if (character >= 'A' && character <= 'Z') return Character.toLowerCase(character);
        if (character < 'a' || character > 'z') return STOP_CHAR;
        return character;
    }

    private int charToIndex(char character) {
        if (character >= 'a' && character <= 'z') return character - 'a';
        return ALPHABET_SIZE; // the stop character goes last
    }

    public void remove(char character) {
        character = normalizeChar(character);
        int index = charToIndex(character);
        if (counts[index] > 0) {
            counts[index]--;
            size--;
        }
    }

    public int getCount(char character) {
        character = normalizeChar(character);
        int index = charToIndex(character);
        return counts[index];
    }

    public int getSize() {
        return size;
    }

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