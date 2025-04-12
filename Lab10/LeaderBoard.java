// Changes made by: Ashwin Kalyan

/**
 * An object that tracks the top-N values of a larger collection.
 * This data structure stores N elements of any sortable type.
 * (NOTE -- while we initially envisioned this in relation to GameScores -- we're building this with generics
 *          and interfaces so it can work with ANY sortable data type. This is a common way to build software
 *          you use an example to determine a need -- but you design to be as flexible as possible.)
 * @param <T> the type of data stored in this LeaderBoard object -- must be a sortable type with defines the compareTo method.
 */
public class LeaderBoard<T extends Comparable<T>> {

    // do not change.
    private T[] scores;

    /**
     * Create a new leaderboard
     * @param size the size of the leaderboard
     * @param dflt the default value for each position in the leaderboard.
     */
    @SuppressWarnings("unchecked")
    public LeaderBoard(int size, T dflt) {
        // do not change.

        // we don't want to deal with small-size boards.
        if (size < 2) {
            size = 2;
        }
        // This line will always have a warning on it (if we hadn't suppressed that on line 17)
        // quite frankly it's a bit messy to begin with -- but it turns out generics and arrays don't play well together.
        // and this is generally understood to be the best we can do for making a generic array.
        scores = (T[]) new Comparable[size];

        for (int i = 0; i < size; i++) {
            scores[i] = dflt;
        }
    }

    public int getSize(){
        return scores.length;
    }

    public T highScore() {
        return scores[0];
    }

    public T lowScore() {
        return scores[scores.length - 1];
    }

    /**
     * Add a new score to the leaderboard.
     * If the new score is higher than the current lowest score,
     * it will be sorted into the correct position in the leaderboard.
     * @param newScore the new score to be added to the leaderboard
     */
    public void add(T newScore) {
        // Checks if the new score is less than or equal to the lowest score
        if (newScore.compareTo(lowScore()) <= 0) {
            return;
        }

        scores[scores.length - 1] = newScore;

        int currentIndex = scores.length - 1; // To calculate the correct position of the new score

        while (currentIndex > 0 && scores[currentIndex].compareTo(scores[currentIndex - 1]) > 0) {
            // Swap the new score with the previous score
            T temp = scores[currentIndex];
            scores[currentIndex] = scores[currentIndex - 1];
            scores[currentIndex - 1] = temp;
            currentIndex--;
        }
    }

    @Override
    public String toString() {
        // do not change.
        String retVal = "";
        for (int i = 0; i < scores.length; i++) {
            retVal += (i+1) + ". " + scores[i].toString() + "\n";
        }
        return retVal;
    }

}
