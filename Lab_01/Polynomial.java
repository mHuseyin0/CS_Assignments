/**
 * Represents polynomials with some basic functionality
 * 
 */
public class Polynomial{
    private double[] coefficients;
    private int degree;

    /**
     * Returns zero polynomial with a degree 0.
     */
    Polynomial(){
        this(0, 0);
    }

    /**
     * Returns a polynomial with the given array of coefficients
     * 
     * @param coefficients list of the coefficients of the polynomial starting from 
     * constant up to x^({@code coefficients.length - 1}).
     * 0 must be passed instead of non-existing terms.
     */
    Polynomial(double[] coefficients){
        this.coefficients = new double[coefficients.length];
        
        // Reverse the entered coefficients
        for (int i = 0; i < coefficients.length; i++){
            this.coefficients[i] = coefficients[coefficients.length - i - 1];
        }

        // Assign the degree considering the last element in the double array might be 0
        this.degree = -1;
        for (int i = 0; i < coefficients.length && degree == -1; i++) {
            if (this.coefficients[i] != 0 || i == coefficients.length - 1) {
                this.degree = coefficients.length - i - 1;
            }
        }
    }

    /**
     * Returns a polynomial consisting of a single term with the given power and coefficient
     * 
     * @param power degree of the polynomial
     * @param coefficient coefficient of the term
     */
    Polynomial(int power, double coefficient){
        this.coefficients = new double[1];
        this.degree = power;
        this.coefficients[0] = coefficient;
    }

    /**
     * Returns the value of the polynomial at the given {@code x}
     * 
     * @param x number to be passed to the function of the polynomial
     * @return value of the polynomial at the given {@code x}
     */
    public double eval(double x){

        double sum = 0;
        
        for (int i = 0; i < coefficients.length; i++) {
            if (x == 0 && degree - i == 0) { // To prevent 0 to the 0 (which returns NaN) case
                sum += coefficients[i];
            }
            else{
                sum += coefficients[i] * Math.pow(x, degree - i);
            }
        }

        return sum;
    }

    /**
     * Returns the value of the polynomial at the given {@code x}.
     * Evaluates the result by using Horner's method
     * 
     * @param x number to be passed to the function of the polynomial
     * @return value of the polynomial at the given {@code x}
     */
    public double eval2(double x){
        double sum = 0;
        
        if (degree != 0 && coefficients.length == 1) {
            sum = coefficients[0] * Math.pow(x, degree);

            if (sum == 0){ // To prevent returning -0.00
                sum = 0;
            }

            return sum;
        }

        
        for (int i = 0; i < coefficients.length - 1; i++) {
            sum += coefficients[i];
            sum *= x;
        }

        sum += coefficients[coefficients.length - 1];

        return sum;
    }

    /**
     * Returns the coefficient of the given degree. If it does not exist, returns 0.
     * 
     * @param degree power of the term whose coefficient is wanted
     * @return coefficient of the term with the specified degree
     */
    public double getCoefficient(int degree) {
        if (degree < coefficients.length && degree >= 0){
            return coefficients[degree];
        }
        else{
            return 0;
        }
    }

    public int getDegree() { return degree; }

    /**
     * Returns the string representation of the polynomial with this format:    
     * <p>c<sub>0</sub> + c<sub>1</sub>x + c<sub>2</sub>x^2 + ... + c<sub>n</sub>x^n </p>
     */
    @Override
    public String toString() {
        
        String polynomial = "";
        String xterm = ""; // To silence the compiler
        boolean emptyString = true;
        double currentCoefficient;
        char sign;
        int currentPower;

        for (int i = 0; i < coefficients.length; i++) {
            currentCoefficient = coefficients[i];
            currentPower = degree - i;

            if (currentCoefficient < 0) {
                sign = '-';
            }
            else{
                sign = '+';
            }

            if (currentPower > 1) {
                xterm = String.format("x^%d", currentPower);
            }
            else if (currentPower == 1){
                xterm = "x";
            }
            else if (currentPower == 0) {
                xterm = "";
            }

            if (currentCoefficient != 0) {
                polynomial = String.format("%c %.1f%s ", sign, Math.abs(currentCoefficient), xterm) + polynomial;
                emptyString = false;
            }
        }

        if (emptyString) {
            polynomial = String.format("%.1f", 0.0);
        }

        if (polynomial.charAt(0) == '+'){
            polynomial = polynomial.replaceFirst("\\+", "");
        }

        return polynomial;
    }

    /**
     * Prints a detailed info about this polynomial
     * 
     * @param name name of the polynomial that is wanted to be displayed
     */
    public void printDetailedInfo(String name){
        System.out.println("-".repeat(50));
        System.out.println(name + ": " + toString());
        System.out.println("Degree: " + degree);
        System.out.printf("Coefficients (Starting from the least significant one):\n");
        for (int i = coefficients.length - 1; i >= 0; i--) {
            System.out.printf("%.1f\t", coefficients[i]);
        }
        System.out.println();
        System.out.println();

        int randomUpperBound = 10; // Exclusive
        int randomLowerBound = -10; // Inclusive
        double random;
        System.out.printf("Evaluation results for some random x between %d and %d:\n", randomLowerBound, randomUpperBound);
        for (int i = 0; i < 10; i++) {
            System.out.println();
            random = Math.random() * (randomUpperBound - randomLowerBound) + randomLowerBound;
            random = (int) random;
            System.out.printf("x: %.1f\n" +
                "-Eval 1: %.1f\n" +
                "-Eval 2: %.1f\n", random, eval(random), eval2(random));
        }
        System.out.println("-".repeat(50));
    }
}