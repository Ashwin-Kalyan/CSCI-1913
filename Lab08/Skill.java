// @author: Ashwin Kalyan

public class Skill {
    private String name;
    private int strength;
    private int usageLimit;
    private int usageLeft;

    /**
     * Constructor for Skill
     * @param name - name of the skill
     * @param strength - strength of the skill
     * @param usageLimit - number of times the skill can be used
     */
    public Skill(String name, int strength, int usageLimit) {
        this.name = name;
        this.strength = strength;
        this.usageLimit = usageLimit;
        this.usageLeft = usageLimit;
    }

    public String getName() {
        return name;
    }

    public int getStrength() {
        return strength;
    }

    public int getUsageLimit() {
        return usageLimit;
    }

    public int getUsageLeft() {
        return usageLeft;
    }

    public void refresh() {
        usageLeft = usageLimit;
    }

    /**
     * Uses the skill on the foe. If the skill has usage left, it applies the changes to the foe.
     * @param me - the monster using the skill
     * @param foe - the monster being attacked
     */
    public void useSkill(CodeMonster me, CodeMonster foe) {
        if (usageLeft > 0) {
            usageLeft--;
            applyChanges(me, foe);
        }
    }

    public void applyChanges(CodeMonster me, CodeMonster foe) {
        foe.adjustHealth(-strength); // Reduce foe's health by the strength of the skill
    }

    public String toString() {
        return name + " " + usageLeft + "/" + usageLimit;
    }
}