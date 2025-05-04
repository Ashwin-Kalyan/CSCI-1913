// CSCI 1913 - Project 3
// @author: Ashwin Kalyan

import java.util.Random;

/**
 * Gibberisher is a class that generates gibberish words based on a training set of words.
 * It uses a Trie data structure to store segments of words and their corresponding next letters.
 * The generated words are based on the frequency of letter occurrences in the training set.
 */
public class Gibberisher {
    private Trie<CharBag> model;
    private int segmentLength;
    private int sampleCount;
    private Random random;

    /**
     * Constructor for Gibberisher.
     * Initializes the Trie model, segment length, sample count, and random number generator.
     * @param segmentLength - the length of the segments to be used for training and generation
     */
    public Gibberisher(int segmentLength) {
        this.model = new Trie<>();
        this.segmentLength = segmentLength;
        this.sampleCount = 0;
        this.random = new Random();
    }

    /**
     * Trains the Gibberisher model with a set of words.
     * This method processes each word, creates segments, and updates the model with the next letters.
     * @param words - an array of words to train the model with
     */ 
    public void train(String[] words) {
        for (String word : words) {
            LetterSample[] samples = LetterSample.toSamples(word, segmentLength);
            for (LetterSample sample : samples) {
                CharBag charBag = model.get(sample.getSegment());
                if (charBag == null) { // create a new CharBag if it doesn't exist
                    charBag = new CharBag();
                    model.put(sample.getSegment(), charBag);
                }
                
                charBag.add(sample.getNextLetter());
                sampleCount++;
            }
        }
    }

    public int getSampleCount() {
        return sampleCount;
    }

    /**
     * Generates a gibberish word based on the trained model.
     * This method starts with an empty string and appends characters based on the segments in the model.
     * It continues until a STOP character is generated, indicating the end of the word.
     * @return - the generated gibberish word
     */
    public String generate() {
        StringBuilder word = new StringBuilder();
        
        while (true) {
            String segment;
            if (word.length() <= segmentLength) segment = word.toString();
            else segment = word.substring(word.length() - segmentLength);

            // Get the CharBag for this segment
            CharBag charBag = model.get(segment);
            // If we haven't seen this segment before, use empty CharBag which will return STOP
            if (charBag == null) charBag = new CharBag();
            char nextChar = charBag.getRandomChar();
            if (nextChar == LetterSample.STOP) break;
            else word.append(nextChar);
        }

        return word.toString();
    }
}
