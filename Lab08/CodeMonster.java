// @author: Ashwin Kalyan

public class CodeMonster {
    private String name;
    private int maxHp;
    private int hp;
    private double speedScore;
    private double nextTurnTime;
    private Skill[] moves;
    private int moveIndex;

    /**
     * Constructor for CodeMonster
     * @param maxHp - maximum health points
     * @param speedScore - speed score (lower is faster)
     * @param name - name of the monster
     * @param moves - array of skills the monster can use
     */
    public CodeMonster(int maxHp, double speedScore, String name, Skill[] moves) {
        this.name = name;
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.speedScore = speedScore;
        this.nextTurnTime = speedScore;
        this.moves = moves;
        this.moveIndex = 0;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public double getSpeedScore() {
        return speedScore;
    }

    public double getNextTurnTime() {
        return nextTurnTime;
    }

    public void setNextTurnTime(double nextTurnTime) {
        this.nextTurnTime = nextTurnTime;
    }

    public Skill[] getMoves() {
        return moves;
    }

    public boolean isAlive() {
        return hp > 0;
    }

    /**
     * Adjusts the health of the monster by a specified amount.
     * If the amount is positive, it heals the monster.
     * If the amount is negative, it damages the monster.
     * The health cannot exceed maxHp or go below 0.
     * @param amount - the amount to adjust health by (can be negative or positive)
     */
    public void adjustHealth(int amount) {
        hp += amount; 
        if (hp > maxHp) {
            hp = maxHp;
        }
        if (hp < 0) {
            hp = 0;
        }
    }

    /**
     * Simulates one turn of battle for the monster.
     * The monster uses its current skill and updates its next turn time.
     * @return - the skill used by the monster
     */
    public Skill takeTurn() {
        Skill currentMove = moves[moveIndex]; 
        moveIndex = (moveIndex + 1) % moves.length;
        nextTurnTime += speedScore;
        return currentMove;
    }

    /**
     * Prepares the monster for battle by resetting its health and next turn time.
     * Also refreshes the skills for the monster.
     */
    public void prepForBattle() {
        hp = maxHp;
        nextTurnTime = speedScore;
        moveIndex = 0;
        for (Skill move : moves) {
            move.refresh();
        }
    }

    public String toString() {
        return name + " " + hp + "/" + maxHp;
    }
}