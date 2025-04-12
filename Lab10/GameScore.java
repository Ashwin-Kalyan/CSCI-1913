// @author: Ashwin Kalyan

public class GameScore implements Comparable<GameScore>{
    private double score;
    private String name;
    private boolean hardMode;

    public GameScore(String name, double score, boolean hardMode) {
        this.name = name;
        this.score = score;
        this.hardMode = hardMode;
    }

    @Override
    public String toString() {
        return name + " " + score + (hardMode ? "*" : "");
    }

    /**
     * Compares this GameScore to another object for equality.
     * Two GameScore objects are considered equal if they have the same score, name, and hardMode status.
     * @param o the object to compare to
     * @return true if the object is equal to this GameScore, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof GameScore)) {
            return false;
        }

        GameScore other = (GameScore) o;
        return  other.score == this.score && other.name.equals(this.name) && other.hardMode == this.hardMode;
    }

    /**
     * Compares this GameScore to another GameScore for ordering.
     * The comparison is based on the hardMode status first, then the score.
     * If both GameScores have the same hardMode status, the one with the higher score is considered greater.
     * If both scores are equal, they are considered equal.
     * @param other the GameScore to compare to
     * @return a negative integer, zero, or a positive integer as this GameScore is less than, equal to, or greater than the specified GameScore
     */
    @Override
    public int compareTo(GameScore other) {
        // Our first ordering priority is hardMode status; hard mode comes before easy mode.
        if (this.hardMode && !other.hardMode) {
            return 1;
        }

        // If both are hard mode or both are easy mode, we compare the scores.
        if (!this.hardMode && other.hardMode) {
            return -1;
        }

        // Our second ordering priority is the score; higher scores come first.
        if (this.score > other.score) {
            return 1;
        }
        
        if (this.score < other.score) {
            return -1;
        }

        // If same mode and same score, they are considered equal for ordering purposes according to the Lab pdf.
        return 0;
    }

    public double getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public boolean isHardMode() {
        return hardMode;
    }
}
