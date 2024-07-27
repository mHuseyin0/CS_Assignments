package School.CS_102.Labs.Lab_03;

import java.util.Scanner;

/**
 * Driver code for the robot game
 */
public class RobotGameMenu {
    public static void main(String[] args) {
        
        // Initialize scanner and determine team sizes by taking input from the user
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter the team size: ");
        int teamSize = scanner.nextInt();

        // Initialize and run the game
        Simulation sim = new Simulation();
        sim.initialize(teamSize);
        sim.simulate();

        scanner.close();
    }
}
