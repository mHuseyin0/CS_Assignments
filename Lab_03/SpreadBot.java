package School.CS_102.Labs.Lab_03;

/**
 * A robot type which can attack up to 3 enemies 
 */
public class SpreadBot extends Robot{

    // Ranges for SpreadBot
    public static final double HEALTH_RANGE_UPPER_LIMIT = 3;
    public static final double HEALTH_RANGE_LOWER_LIMIT = 2;
    public static final double ATTACK_RANGE_UPPER_LIMIT = 1;
    public static final double ATTACK_RANGE_LOWER_LIMIT = 0.5;
    public static final double SPEED_RANGE_UPPER_LIMIT = 1.5;
    public static final double SPEED_RANGE_LOWER_LIMIT = 0.5;

    /**
     * Returns a robot which can attack up to 3 enemies
     * 
     * @param name name of the robot
     * @param isRedTeam team of the robot
     */
    public SpreadBot(String name, boolean isRedTeam){
        super(name, isRedTeam,
                HEALTH_RANGE_LOWER_LIMIT, HEALTH_RANGE_UPPER_LIMIT,
                ATTACK_RANGE_LOWER_LIMIT, ATTACK_RANGE_UPPER_LIMIT,
                SPEED_RANGE_LOWER_LIMIT, SPEED_RANGE_UPPER_LIMIT);
    }

    /**
     * Attacks 3 enemies, if exists, from opponent team. If there are less than 
     * 3 enemies, attacks the existing ones
     * 
     * @param s game simulation to attack enemy team
     */
    public void attack(Simulation s){
        Robot[] robots = s.getLowestSpeed3(!isRedTeam);
        System.out.println(this.name + " attacks following targets (ones with the slowest speed):");
        for (Robot robot : robots) {
            System.out.print(robot.getName() + " ");
        }
        System.out.println();

        for (Robot r : robots) {
            if(r.getHitAndIsDestroyed(attack)){
                s.addToLastDestroyedIndexes(s.getIndex(r));
                s.removeRobot(r);
            };
        }
    }
}
