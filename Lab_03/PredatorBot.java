package School.CS_102.Labs.Lab_03;

/**
 * A robot type which attacks the enemy with the highest health
 */
public class PredatorBot extends Robot{

    // Ranges for PredatorBot
    public static final double HEALTH_RANGE_UPPER_LIMIT = 3;
    public static final double HEALTH_RANGE_LOWER_LIMIT = 2;
    public static final double ATTACK_RANGE_UPPER_LIMIT = 3;
    public static final double ATTACK_RANGE_LOWER_LIMIT = 2;
    public static final double SPEED_RANGE_UPPER_LIMIT = 1;
    public static final double SPEED_RANGE_LOWER_LIMIT = 0.5;

    /**
     * Returns a robot which attacks the enemy with the highest health
     * 
     * @param name name of the robot
     * @param isRedTeam team of the robot
     */
    public PredatorBot(String name, boolean isRedTeam){
        super(name, isRedTeam,
                HEALTH_RANGE_LOWER_LIMIT, HEALTH_RANGE_UPPER_LIMIT,
                ATTACK_RANGE_LOWER_LIMIT, ATTACK_RANGE_UPPER_LIMIT,
                SPEED_RANGE_LOWER_LIMIT, SPEED_RANGE_UPPER_LIMIT);
    }

    /**
     * Attacks the enemy with the highest health from opponent team
     * 
     * @param s game simulation to attack enemy team
     */
    public void attack(Simulation s){
        Robot attacked = s.getHighestHealth(!isRedTeam);
        System.out.println(this.name + " attacks " + attacked.name + " which has the highest health.");
        if (attacked.getHitAndIsDestroyed(attack)) {
            s.addToLastDestroyedIndexes(s.getIndex(attacked));
            s.removeRobot(attacked);
        }
    }
}
