// @author: Ashwin Kalyan

public class VampiricSkill extends Skill {
    /**
     * Constructor for VampiricSkill
     * @param name - name of the skill
     * @param strength - strength of the skill
     * @param usageLimit - number of times the skill can be used
     */
    public VampiricSkill(String name, int strength, int usageLimit) {
        super(name, strength, usageLimit);
    }

    /**
     * Applies the skill to the monster and foe.
     * The foe's health is reduced by the strength of the skill, and the monster's health is increased by the same amount.
     * @param me - the monster using the skill
     * @param foe - the monster being attacked
     */
    @Override
    public void applyChanges(CodeMonster me, CodeMonster foe) {
        foe.adjustHealth(-getStrength()); // Reduce foe's health by the strength of the skill
        me.adjustHealth(getStrength()); // Increase monster's health by the strength of the skill
    }
}