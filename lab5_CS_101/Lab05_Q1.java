import java.util.Scanner;

/*
 * Print out a pyramid whose height is taken from the user with the ASCII characters starting from A.
 * 
 * @author Muhammed Huseyin AYDIN
 */
public class Lab05_Q1 {
    public static void main(String[] args) {
        // Variables
        int heightOfPyramid = 0;

        // Take valid input
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.print("Please enter the height of pyramid between 0 and 60: ");
            if (scanner.hasNextInt()){
                heightOfPyramid = scanner.nextInt();
            }
            else{
                scanner.nextLine();
            }
        } while(heightOfPyramid >= 60 || heightOfPyramid <= 0);
        
        // Print out the pyramid
        for (int i = 1; i <= heightOfPyramid; i++) {
            System.out.print(" ".repeat(heightOfPyramid - i)); // Empty space at the beginning of lines
            for (int j = 1; j <= i; j++) {
                System.out.print((char) (j + 'A' - 1));
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}