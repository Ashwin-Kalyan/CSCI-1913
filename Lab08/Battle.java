// @author: Ashwin Kalyan

public class Battle {
    /**
     * Simulates one turn of battle between two CodeMonsters.
     * The monster with the lowest nextTurnTime attacks first.
     * @param one - first CodeMonster
     * @param two - second CodeMonster
     */
    public static void doOneTurn(CodeMonster one, CodeMonster two) {
        CodeMonster attacker = (one.getNextTurnTime() <= two.getNextTurnTime()) ? one : two;
        CodeMonster defender = (attacker == one) ? two : one;

        Skill skill = attacker.takeTurn();
        System.out.println(attacker + " uses " + skill + " on " + defender);
        skill.useSkill(attacker, defender);
    }

    /**
     * Simulates a battle between two CodeMonsters.
     * The monster with the lowest nextTurnTime attacks first.  
     * @param one - first CodeMonster
     * @param two - second CodeMonster
     * @return - the winner of the battle
     */
    public static CodeMonster battle(CodeMonster one, CodeMonster two) {
        one.prepForBattle();
        two.prepForBattle();
        System.out.println(one + " vs. " + two);

        while (one.isAlive() && two.isAlive()) {
            doOneTurn(one, two);
        }

        CodeMonster winner = one.isAlive() ? one : two;
        System.out.println(winner + " wins!");
        return winner;
    }
}