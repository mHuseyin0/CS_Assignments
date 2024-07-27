package School.CS_102.Labs.Lab_03;

import java.util.ArrayList;

/**
 * Determines and simulates how robots and teams attack and defend
 */
public class Simulation {

    // Strings to display each corresponding a robot type
    private static final String SIMPLE_BOT_CODE = "S";
    private static final String PREDATOR_BOT_CODE = "P";
    private static final String DEFENCE_BOT_CODE = "D";
    private static final String SPEED_BOT_CODE = "X";
    private static final String SPREAD_BOT_CODE = "K";
    private static final String ONE_BOT_CODE = "O";

    // Indexes to use in program each corresponding a robot type
    private static final int SIMPLE_BOT_INDEX = 0;
    private static final int PREDATOR_BOT_INDEX = 1;
    private static final int DEFENCE_BOT_INDEX = 2;
    private static final int SPEED_BOT_INDEX = 3;
    private static final int SPREAD_BOT_INDEX = 4;
    private static final int ONE_BOT_INDEX = 5;

    // Total robot type count in the current design
    public static final int ROBOT_TYPE_COUNT = 6;
   
    // Instance variables
    private ArrayList<Integer> destroyedIndexes;
    private int totalRobotCount;
    public ArrayList<Robot> redTeam;
    public ArrayList<Robot> blueTeam;
    public int teamSizes;

    /**
     * Creates a new game simulation. {@code initialize()} method should be called to initialize game.
     * After initialization, {@code simulate()} method should be called to start simulation
     */
    public Simulation(){
        this.destroyedIndexes = new ArrayList<Integer>(3); // Because at most 3 robots can be destroyed in one turn
        this.totalRobotCount = 0; // To give each robot a unique number
        this.redTeam = new ArrayList<Robot>();
        this.blueTeam = new ArrayList<Robot>();
    }

    /**
     * Initialize the teams with the given sizes.
     * Also prints out the information about the teams.
     * 
     * @param teamSize robot count for each team
     */
    public void initialize(int teamSize){
        teamSizes = teamSize;
        initializeTeam(redTeam, true);
        initializeTeam(blueTeam, false);
    
        System.out.println("Red Team:");
        for (Robot r : redTeam) {
            System.out.println(r);
        }

        System.out.println();

        System.out.println("Blue Team:");
        for (Robot r : blueTeam) {
            System.out.println(r);
        }
        
        System.out.println();
    }

    /**
     * Helper method for initializing teams
     * 
     * @param team team to create
     * @param isRedTeam red or blue team
     */
    private void initializeTeam(ArrayList<Robot> team, boolean isRedTeam){
        Robot newRobot;
        int randomInt;
        int insertIndex;

        for (int i = 0; i < teamSizes; i++) {
            // Randomly choose a robot type for each index
            randomInt = (int) (Math.random() * ROBOT_TYPE_COUNT);

            switch (randomInt) {
                case SIMPLE_BOT_INDEX:
                    newRobot = new SimpleBot(SIMPLE_BOT_CODE + totalRobotCount++, isRedTeam);
                    break;
                case PREDATOR_BOT_INDEX:
                    newRobot = new PredatorBot(PREDATOR_BOT_CODE + totalRobotCount++, isRedTeam);
                    break;
                case DEFENCE_BOT_INDEX:
                    newRobot = new DefenceBot(DEFENCE_BOT_CODE + totalRobotCount++, isRedTeam);
                    break;
                case SPEED_BOT_INDEX:
                    newRobot = new SpeedBot(SPEED_BOT_CODE + totalRobotCount++, isRedTeam);
                    break;
                case SPREAD_BOT_INDEX:
                    newRobot = new SpreadBot(SPREAD_BOT_CODE + totalRobotCount++, isRedTeam);
                    break;
                case ONE_BOT_INDEX:
                    newRobot = new OneBot(ONE_BOT_CODE + totalRobotCount++, isRedTeam);
                    break;
                default:
                    String teamName = "blue";
                    if (isRedTeam) {
                        teamName = "red";
                    }
                    newRobot = null;
                    System.out.printf("An error occured during the initialization of the robot #%d" +
                    "in %s team.\n", totalRobotCount % teamSizes, teamName);
                    break;
            }

            // Insert the new robot in its correct place in the team according to the speeds
            insertIndex = -1;
            for (int index = 0; index < team.size(); index++) {
                if (team.get(index).getSpeed() < newRobot.getSpeed()) {
                    insertIndex = index;
                    break;
                }
            }
            if (insertIndex == -1) {
                insertIndex = team.size();
            }

            team.add(insertIndex, newRobot);
        }
    }

    /**
     * Simulate the game after initialization. Prints out attack and destruction information.
     */
    public void simulate(){
        double redSpeedSum = 0;
        double blueSpeedSum = 0;
        boolean isRedStarts = true;
        boolean isGameEnded = false;

        // Calculate the sum of the speeds
        for (Robot robot : blueTeam) {
            blueSpeedSum += robot.getSpeed();
        }

        for (Robot robot : redTeam) {
            redSpeedSum += robot.getSpeed();
        }

        // Print out them
        System.out.printf("Speed sum of red:  %.3f\n", redSpeedSum);
        System.out.printf("Speed sum of blue: %.3f\n", blueSpeedSum);
        System.out.println();

        // Determine which team starts first
        if (redSpeedSum < blueSpeedSum) {
            isRedStarts = false;
            System.out.println("Blue starts first.");
        }
        else{
            System.out.println("Red stars first.");
        }
        System.out.println();

        // Indexes of the robots who will attack next
        int starterCurrentIndex = 0;
        int nonStarterCurrentIndex = 0;

        // Assign starter and non starter team
        ArrayList<Robot> starterTeam, nonStarterTeam;
        if (isRedStarts){
            starterTeam = redTeam;
            nonStarterTeam = blueTeam;
        }
        else{
            starterTeam = blueTeam;
            nonStarterTeam = redTeam;
        }

        String winnerName = "Red";
        ArrayList<Robot> winnerTeam = null;
        int indexDifference;

        // Main loop to simulate attacks
        while (!isGameEnded) {

            // Attack and assign who will attack next
            starterCurrentIndex = teamAttackHelper(starterTeam, starterCurrentIndex);
            
            // Arrange the other team's index if any robot destroyed
            indexDifference = 0;
            for (Integer i : destroyedIndexes) {
                if (i < nonStarterCurrentIndex) {
                    indexDifference++;
                }
            }
            nonStarterCurrentIndex -= indexDifference;

            resetLastDestroyedIndexes();

            // Check if the starter team wins
            if (nonStarterTeam.size() == 0) {
                if (!isRedStarts) {
                    winnerName = "Blue";
                }
                winnerTeam = starterTeam;

                isGameEnded = true;
                break;
            }

            // Attack and assign who will attack next
            nonStarterCurrentIndex = teamAttackHelper(nonStarterTeam, nonStarterCurrentIndex);
            
            // Arrange the other team's index if any robot destroyed
            indexDifference = 0;
            for (Integer i : destroyedIndexes) {
                if (i < starterCurrentIndex) {
                    indexDifference++;
                }
            }
            starterCurrentIndex -= indexDifference;

            resetLastDestroyedIndexes();

            // Check if the starter team wins
            if (starterTeam.size() == 0) {
                if (isRedStarts) {
                    winnerName = "Blue";
                }
                winnerTeam = nonStarterTeam;
            
                isGameEnded = true;
            }
        }

        // Present the winner
        System.out.printf("\n%s team wins, remaining robots:\n", winnerName);
        for (Robot r : winnerTeam) {
            System.out.println(r);
        }

    }

    /**
     * Simulate attacks by calling {@code Robot.attack()} method for the given team.
     * 
     * @param team team which is going to attack
     * @param attackerIndex index of the robot who is gonna attack
     * @return index of the robot who is gonna attack next turn
     */
    private int teamAttackHelper(ArrayList<Robot> team, int attackerIndex){

        // Arrange the attacker index
        if (attackerIndex > team.size() - 1) {
            attackerIndex = 0;
        }

        // Simulate the attack
        System.out.println();
        team.get(attackerIndex++).attack(this);

        return attackerIndex;
    }

    /**
     * Returns a random target from the team determined by {@code isRedTeam}
     * 
     * @param isRedTeam determines the team for the random {@code Robot} to be returned
     * @return a random robot from the given team
     */
    public Robot getRandomTarget(boolean isRedTeam){
        if (isRedTeam) {
            return randomTargetHelper(redTeam);
        }
        return randomTargetHelper(blueTeam);
    }

    /**
     * Pick a random robot from the given team
     * 
     * @param team team from which a robot will be returned
     * @return a random robot from the {@code team}
     */
    private Robot randomTargetHelper(ArrayList<Robot> team){
        int randomIndex = (int) (Math.random() * team.size());
        return team.get(randomIndex);
    }

    /**
     * Returns the {@code Robot} with the highest health from the team determined by {@code isRedTeam}
     * 
     * @param isRedTeam determines the team for the {@code Robot} to be returned
     * @return robot who has the highest health from the given team
     */
    public Robot getHighestHealth(boolean isRedTeam){
        if (isRedTeam) {
            return highestHealthHelper(redTeam);
        }
        return highestHealthHelper(blueTeam);
    }

    /**
     * Pick the robot with the highest health from the given team
     * 
     * @param team team from which a robot will be returned
     * @return the robot who has the highest health from the {@code team}
     */
    private Robot highestHealthHelper(ArrayList<Robot> team){
        double health = 0;
        Robot robot = null;
        for (Robot r : team) {
            if (r.getHealth() > health) {
                health = r.getHealth();
                robot = r;
            }
        }
        return robot;
    }

    /**
     * Returns the {@code Robot} with the lowest health from the team determined by {@code isRedTeam}
     * 
     * @param isRedTeam determines the team for the {@code Robot} to be returned
     * @return robot who has the lowest health from the given team
     */
    public Robot getLowestHealth(boolean isRedTeam){
        if (isRedTeam) {
            return lowestHealthHelper(redTeam);
        }
        return lowestHealthHelper(blueTeam);
    }


    /**
     * Pick the robot with the lowest health from the given team
     * 
     * @param team team from which a robot will be returned
     * @return the robot who has the lowest health from the {@code team}
     */
    private Robot lowestHealthHelper(ArrayList<Robot> team){
        double health = Double.MAX_VALUE;
        Robot robot = null;
        for (Robot r : team) {
            if (r.getHealth() < health) {
                health = r.getHealth();
                robot = r;
            }
        }
        return robot;
    }

    /**
     * Returns the {@code Robot} with the lowest speed from the team determined by {@code isRedTeam}
     * 
     * @param isRedTeam determines the team for the {@code Robot} to be returned
     * @return robot who has the lowest speed from the given team
     */
    public Robot getLowestSpeed(boolean isRedTeam){
        if (isRedTeam) {
            return redTeam.get(redTeam.size() - 1);
        }
        return blueTeam.get(blueTeam.size() - 1);
    }

    /**
     * Returns the {@code Robot} with the lowest attack from the team determined by {@code isRedTeam}
     * 
     * @param isRedTeam determines the team for the {@code Robot} to be returned
     * @return robot who has the lowest attack from the given team
     */
    public Robot getLowestAttack(boolean isRedTeam){
        if (isRedTeam) {
            return lowestAttackHelper(redTeam);
        }
        return lowestAttackHelper(blueTeam);
    }

    /**
     * Pick the robot with the lowest attack from the given team
     * 
     * @param team team from which a robot will be returned
     * @return the robot who has the lowest attack from the {@code team}
     */
    private Robot lowestAttackHelper(ArrayList<Robot> team){
        double attack = Double.MAX_VALUE;
        Robot robot = null;
        for (Robot r : team) {
            if (r.getAttack() < attack) {
                attack = r.getAttack();
                robot = r;
            }
        }
        return robot;
    }

    /**
     * Returns 3 robots with the lowest 3 speed starting from the slowest one.
     * If the number of robots is less than 3, returns the present ones starting 
     * from the slowest one.
     * 
     * @param isRedTeam determines the team for the {@code Robot}s to be returned
     * @return 3 robots who have the lowest speed from the given team
     */
    public Robot[] getLowestSpeed3(boolean isRedTeam){
        if (isRedTeam) {
            return lowest3SpeedHelper(redTeam);
        }
        return lowest3SpeedHelper(blueTeam);
    }

    /**
     * Pick 3 robots with the lowest speed from the given team
     * 
     * @param team team from which robots will be returned
     * @return 3 robots who have the lowest speed from the {@code team}
     */
    private Robot[] lowest3SpeedHelper(ArrayList<Robot> team){
        int arraySize = Math.min(3, team.size());

        if (arraySize == 0) {
            return null;
        }

        Robot[] lowest3Speed = new Robot[arraySize];

        int iterator = 0;
        for (int index = team.size() - 1; index >= team.size() - arraySize; index--) {
            lowest3Speed[iterator++] = team.get(index);
        }

        return lowest3Speed;
    }

    /**
     * Remove a robot from its team
     * 
     * @param r robot to be removed from its team
     */
    public void removeRobot(Robot r){
        if (redTeam.contains(r)) {
            redTeam.remove(r);
            return;
        }
        blueTeam.remove(r);
    }

    /**
     * Return an index of a robot in its team
     * 
     * @param r robot whose index will be returned
     * @return the index of the robot in its team
     */
    protected int getIndex(Robot r){
        if (redTeam.contains(r)) {
            return redTeam.indexOf(r);
        }
        return blueTeam.indexOf(r);
    }

    /**
     * Helper method to be called when a robot is destroyed in order to arrange
     * the indexes in {@code simulate()} method
     * 
     * @param index index of the destroyed robot
     */
    protected void addToLastDestroyedIndexes(int index){
        this.destroyedIndexes.add(index);
    }

    /**
     * Reset the destroyed robots to record new ones
     */
    private void resetLastDestroyedIndexes(){
        this.destroyedIndexes = new ArrayList<Integer>(3);
    }
}