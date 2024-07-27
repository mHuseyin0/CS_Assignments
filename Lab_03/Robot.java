package School.CS_102.Labs.Lab_03;

/**
 * Represents robot with health, attack, speed, name, and team properties and
 * some functionality like attack and be destroyed
 */
public abstract class Robot {

    // Instance variables
    protected double health;
    protected double attack;
    protected double speed;
    protected boolean isRedTeam;
    protected String name;

    /**
     * Returns a robot object with the random values between the given ranges and
     * specified name and team
     * 
     * @param name name of the robot
     * @param isRedTeam team of the robot (blue or red)
     * @param lowerHealthLimit minimum health for random health
     * @param upperHealthLimit maximum health for random health
     * @param lowerAttackLimit minimum attack for random attack
     * @param upperAttackLimit maximum attack for random attack
     * @param lowerSpeedLimit minimum speed for random speed
     * @param upperSpeedLimit maximum speed for random speed
     */
    public Robot(String name, boolean isRedTeam,
                double lowerHealthLimit, double upperHealthLimit,
                double lowerAttackLimit, double upperAttackLimit,
                double lowerSpeedLimit, double upperSpeedLimit){
        
        this.name = name;
        this.isRedTeam = isRedTeam;
        
        this.health = pickRandomBetweenRange(lowerHealthLimit, upperHealthLimit);
        this.attack = pickRandomBetweenRange(lowerAttackLimit, upperAttackLimit);
        this.speed = pickRandomBetweenRange(lowerSpeedLimit, upperSpeedLimit);
    }

    /**
     * Attacks an enemy. That enemy is determined according to the type of the robot who is
     * attacking
     * 
     * @param s game simulation to determine the enemy to attack
     */
    public abstract void attack(Simulation s);

    /**
     * Takes damage and print outs remaining health
     * 
     * @param damage the amount of damage this will take
     * @return {@code true} if this robot is destroyed and {@code false} otherwise
     */
    public boolean getHitAndIsDestroyed(double damage){
        System.out.printf("%s receives %.3f damage -> remaining health: ", this, damage);
        if (this.health <= damage) {
            this.health = 0;
            System.out.printf("%.3f\n", health);
            System.out.printf("%s destroyed\n", this.getName());
            return true;
        }
        else{
            this.health -= damage;
            System.out.printf("%.3f\n", health);
            return false;
        }
    }

    /**
     * Returns a random double between the given values
     * 
     * @param lowerLimit minimum value for the random double
     * @param upperLimit maximum value for the random double
     * @return the random double in the range
     */
    public static double pickRandomBetweenRange(double lowerLimit, double upperLimit){
        double random = Math.random() * (upperLimit - lowerLimit) + lowerLimit;
        int roundedRandom = (int) Math.round(1000 * random);
        return roundedRandom / 1000.0;
    }

    @Override
    public String toString() {
        return String.format("%-3s Health: %.3f Attack: %.3f Speed: %.3f", name, health, attack, speed);
    }

    public double getHealth() {
        return health;
    }

    public double getAttack() {
        return attack;
    }

    public double getSpeed() {
        return speed;
    }

    public String getName() {
        return name;
    }

    public boolean isRedTeam() {
        return isRedTeam;
    }
}
