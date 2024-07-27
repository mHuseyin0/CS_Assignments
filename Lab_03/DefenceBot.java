package School.CS_102.Labs.Lab_03;

/**
 * A robot type which attacks the enemy with the lowest speed
 */
public class DefenceBot extends Robot{

    // Ranges for PredatorBot
    public static final double HEALTH_RANGE_UPPER_LIMIT = 6;
    public static final double HEALTH_RANGE_LOWER_LIMIT = 3;
    public static final double ATTACK_RANGE_UPPER_LIMIT = 1;
    public static final double ATTACK_RANGE_LOWER_LIMIT = 0.5;
    public static final double SPEED_RANGE_UPPER_LIMIT = 1;
    public static final double SPEED_RANGE_LOWER_LIMIT = 0.5;

    /**
     * Returns a robot which attacks the enemy with the lowest speed
     * 
     * @param name name of the robot
     * @param isRedTeam team of the robot
     */
    public DefenceBot(String name, boolean isRedTeam){
        super(name, isRedTeam,
                HEALTH_RANGE_LOWER_LIMIT, HEALTH_RANGE_UPPER_LIMIT,
                ATTACK_RANGE_LOWER_LIMIT, ATTACK_RANGE_UPPER_LIMIT,
                SPEED_RANGE_LOWER_LIMIT, SPEED_RANGE_UPPER_LIMIT);
    }

    /**
     * Attacks the enemy with the lowest speed from opponent team
     * 
     * @param s game simulation to attack enemy team
     */
    public void attack(Simulation s){
        Robot attacked = s.getLowestSpeed(!isRedTeam);
        System.out.println(this.name + " attacks " + attacked.name + " which has the lowest speed");
        if (attacked.getHitAndIsDestroyed(attack)) {
            s.addToLastDestroyedIndexes(s.getIndex(attacked));
            s.removeRobot(attacked);
        }
    }
}
