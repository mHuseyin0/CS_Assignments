/**
 * Driver code for {@code CharPattern} class
 */
public class CharPatternTester {
    public static void main(String[] args) {
        CharPattern test1 = new CharPattern(4, 5);
        test1.print();
        System.out.println();

        test1.fillChar('x', 2, 4, 1,4);
        test1.print();
        System.out.println();

        test1.fillChar('y', 1, 3, 0, 5);
        test1.print();
        System.out.println();
        

        printDash();


        CharPattern pattern1test1 = new CharPattern(10, 10);
        pattern1test1.pattern1();
        pattern1test1.print();
        System.out.println();

        CharPattern pattern1test2 = new CharPattern(7, 19);
        pattern1test2.pattern1();
        pattern1test2.print();
        System.out.println();

        CharPattern pattern1test3 = new CharPattern(4, 3);
        pattern1test3.pattern1();
        pattern1test3.print();
        System.out.println();


        printDash();


        CharPattern pattern2test1 = new CharPattern(3, 13);
        pattern2test1.pattern2(5, 0);
        pattern2test1.print();
        System.out.println();

        pattern2test1.pattern2(5, 2);
        pattern2test1.print();
        System.out.println();

        CharPattern pattern2test2 = new CharPattern(7, 13);
        pattern2test2.pattern2(5, 4);
        pattern2test2.print();
        System.out.println();

        CharPattern pattern2test3 = new CharPattern(4, 19);
        pattern2test3.pattern2(9, 4);
        pattern2test3.print();
        System.out.println();


        printDash();


        CharPattern pattern3test1 = new CharPattern(5, 5);
        pattern3test1.pattern3();
        pattern3test1.print();
        System.out.println("Highest row sum: " + pattern3test1.findMaxRowSum());
        System.out.println();

        CharPattern pattern3test2 = new CharPattern(9, 35);
        pattern3test2.pattern3();
        pattern3test2.print();
        System.out.println("Highest row sum: " + pattern3test2.findMaxRowSum());
        System.out.println();
    }

    public static void printDash(){
        System.out.println("-".repeat(50));
    }
}
