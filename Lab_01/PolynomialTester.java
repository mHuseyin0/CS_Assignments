/**
 * Tests the {@code Polynomial} class
 */
public class PolynomialTester{
    public static void main(String[] args){

        double[] coefficients1 = new double[10];
        for (int i = 0; i < coefficients1.length; i++) {
            coefficients1[i] =  (int) (Math.random() * 11 - 5); // Pick a random int in the interval [-5,5]
        }

        double[] coefficients2 = new double[3];
        for (int i = 0; i < coefficients2.length; i++) {
            coefficients2[i] = Math.random() * 11 - 5; // Pick a random double in the interval [-5,6)
        }

        Polynomial zeroPolynomial = new Polynomial();
        Polynomial singleTermPolynomial = new Polynomial(9, -2);
        Polynomial multiTermPolynomial1 = new Polynomial(coefficients1);
        Polynomial multiTermPolynomial2 = new Polynomial(coefficients2);

        zeroPolynomial.printDetailedInfo("Zero Polynomial");
        singleTermPolynomial.printDetailedInfo("Single Term Polynomial");
        multiTermPolynomial1.printDetailedInfo("Multi Term Polynomial 1");
        multiTermPolynomial2.printDetailedInfo("Multi Term Polynomial 2");
    }
}