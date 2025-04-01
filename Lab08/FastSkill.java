// @author: Ashwin Kalyan

public class FastSkill extends Skill {
    /**
     * Constructor for FastSkill
     * @param name - name of the skill
     * @param strength - strength of the skill
     * @param usageLimit - number of times the skill can be used
     */
    public FastSkill(String name, int strength, int usageLimit) {
        super(name, strength, usageLimit);
    }

    /**
     * Applies the skill to the monster and foe.
     * The foe's health is reduced by the strength of the skill, and the monster's next turn time is reduced by its speed score.
     * @param me - the monster using the skill
     * @param foe - the monster being attacked
     */
    @Override
    public void applyChanges(CodeMonster me, CodeMonster foe) {
        foe.adjustHealth(-getStrength()); // Reduce foe's health by the strength of the skill
        me.setNextTurnTime(me.getNextTurnTime() - me.getSpeedScore()); // Reduce monster's next turn time by its speed score
    }
}