/**
 * Tests the {@code Polynomial} class
 */
public class PolynomialTester{
    public static void main(String[] args){

        double[] coefficients1 = {0,0,0,1,2,0,0,0};
        double[] coefficients2 = {0,0,0,0,4,0,1,0,0,0,0};
        double[] coefficients3 = {2,1,0};
        double[] coefficients4 = {3,4,1};


        Polynomial zeroPolynomial = new Polynomial();
        Polynomial consantPolynomial = new Polynomial(0, -1);
        Polynomial multiTermPolynomial1 = new Polynomial(coefficients1);
        Polynomial multiTermPolynomial2 = new Polynomial(coefficients2);
        Polynomial singleTermPolynomial1 = new Polynomial(2, 13);
        Polynomial singleTermPolynomial2 = new Polynomial(10, -11);
        Polynomial linearPolynomial = new Polynomial(coefficients3);
        Polynomial quadraticPolynomial = new Polynomial(coefficients4);


        Polynomial[] polynomials = {zeroPolynomial, singleTermPolynomial1, singleTermPolynomial2,
            multiTermPolynomial1, multiTermPolynomial2, linearPolynomial, quadraticPolynomial};

        Polynomial polynomial1, polynomial2;
        int[] equalityTest;

        for (int i = 0; i < polynomials.length; i++) {
            polynomial1 = polynomials[i];
            for (int j = i; j < polynomials.length; j++) {
                polynomial2 = polynomials[j];
                System.out.println("-".repeat(100));
                
                System.out.println(polynomial1);
                System.out.println(polynomial2);
                
                System.out.printf("%-20s: ", "Addition");
                System.out.println(polynomial1.add(polynomial2));
                System.out.printf("%-20s: ", "Reverse Direction");
                System.out.println(polynomial2.add(polynomial1));

                System.out.println();

                System.out.printf("%-20s: ", "Multiplication");
                System.out.println(polynomial1.mul(polynomial2));
                System.out.printf("%-20s: ", "Reverse Direction");
                System.out.println(polynomial2.mul(polynomial1));

                System.out.println();

                System.out.printf("%-20s: ", "Composition");
                System.out.println(polynomial1.compose(polynomial2));
                System.out.printf("%-20s: ", "Reverse Direction");
                System.out.println(polynomial2.compose(polynomial1));

                System.out.println("-".repeat(100));

                if (!polynomial1.equals(polynomial2)) {
                    System.out.printf("%-20s: ", "Equal Values");
                    equalityTest = polynomial1.findEqual(polynomial2);
                    for (int k : equalityTest) {
                        System.out.print(k + " ");
                    }
                    System.out.println();
                }
            }
        }

        double[] divisionCoefficients = {3,4,1,3,0,2};
        Polynomial divisionTest1 = new Polynomial(divisionCoefficients);

        System.out.println(divisionTest1);
        System.out.println(linearPolynomial);
        System.out.println(divisionTest1.div(linearPolynomial));

    }
}