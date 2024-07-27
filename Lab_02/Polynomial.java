import java.util.ArrayList;

/**
 * Represents polynomials with some basic functionality
 * 
 * @version 2.0
 * @author Muhammed Huseyin AYDIN
 */
public class Polynomial{
    private double[] coefficients;
    private int degree;

    /**
     * Returns zero polynomial with a degree 0.
     */
    public Polynomial(){
        this(0, 0);
    }

    public Polynomial(Polynomial clonePolynomial){
        this.coefficients = clonePolynomial.coefficients;
        this.degree = clonePolynomial.degree;
    }

    /**
     * Returns a polynomial with the given array of coefficients
     * 
     * @param coefficients list of the coefficients of the polynomial starting from 
     * constant up to x^({@code coefficients.length - 1}).
     * 0 must be passed instead of non-existing terms.
     */
    public Polynomial(double[] coefficients){
        this(coefficients, true);
    }

    /**
     * Helper constructor for easiness in operations
     * 
     * @param coefficients coefficients of the polynomial
     * @param reverseCoefficients whether coefficients array should be reversed or not
     */
    private Polynomial(double[] coefficients, boolean reverseCoefficients){
        this();

        // Check if there is only 1 term in order to save memory
        boolean singleTerm = true;
        int coefficientCount = 0;
        int singleTermIndex = -1; // To silence the compiler
        for (int i = 0; i < coefficients.length && singleTerm; i++) {
            if (coefficients[i] != 0) {
                coefficientCount++;
                singleTermIndex = i;
            }
            if (coefficientCount > 1) {
                singleTerm = false;
            }
        }

        if (singleTerm) {
            // To handle zero polynomial
            if (singleTermIndex == -1) {
                singleTermIndex = 0;
            }

            this.coefficients = new double[1];
            this.degree = singleTermIndex;
            this.coefficients[0] = coefficients[singleTermIndex];
            return;
        }
        int trailingZeroCount = 0;
        boolean foundDegree = false;

        // Handle the trailing zeros if exists
        if (reverseCoefficients) {
            for (int i = coefficients.length - 1; i >= 0 && !foundDegree; i--) {
                if (coefficients[i] != 0) {
                    foundDegree = true;
                }
                else{
                    trailingZeroCount++;
                }
            }
        }
        else {
            for (int i = 0; i < coefficients.length && !foundDegree; i++) {
                if (coefficients[i] != 0) {
                    foundDegree = true;
                }
                else{ // Leading zero count for this case
                    trailingZeroCount++;
                }
            }
        }

        // Keep zero polynomial if all the elements are 0
        if (!foundDegree) {
            return;
        }

        this.degree = coefficients.length - trailingZeroCount - 1;
        this.coefficients = new double[this.degree + 1];
        
        if (reverseCoefficients){
            for (int i = this.degree; i >= 0; i--) {
                this.coefficients[this.degree - i] = coefficients[i];
            }
        }
        else{ // Leading zero count for this case
            for (int i = trailingZeroCount; i < coefficients.length; i++) {
                this.coefficients[i - trailingZeroCount] = coefficients[i];
            }
        }        
    }

    /**
     * Returns a polynomial consisting of a single term with the given power and coefficient
     * 
     * @param power degree of the polynomial
     * @param coefficient coefficient of the term
     */
    public Polynomial(int power, double coefficient){
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
     * Returns P(x) + Q(x) where P(x) is {@code this} and Q(x) is {@code polynomial2}.
     * 
     * @param polynomial2 the second addend of the addition
     * @return sum of the two polynomials
     */
    public Polynomial add(Polynomial polynomial2){
        int newDegree = Math.max(this.degree, polynomial2.degree);

        double[] newCoefficients = new double[newDegree + 1];

        for (int i = 0; i < this.coefficients.length; i++) {
            newCoefficients[this.degree - i] += this.coefficients[i];
        }

        for (int i = 0; i < polynomial2.coefficients.length; i++) {
            newCoefficients[polynomial2.degree - i] += polynomial2.coefficients[i];
        }

        return new Polynomial(newCoefficients);
    }

    /**
     * Returns P(x) - Q(x) where P(x) is {@code this} and Q(x) is {@code polynomial2}.
     * 
     * @param polynomial2 subtrahend of the subtracion
     * @return difference between two polynomials
     */
    public Polynomial sub(Polynomial polynomial2){
        return add(polynomial2.mul(new Polynomial(0, -1)));
    }

    /**
     * Returns P(x) * Q(x) where P(x) is {@code this} and Q(x) is {@code polynomial2}.
     * 
     * @param polynomial2 factor to multiply with {@code this}
     * @return result of the polynomial multiplication
     */
    public Polynomial mul(Polynomial polynomial2){
        double[] newCoefficients = new double[getDegree() + polynomial2.getDegree() + 1];

        for (int i = 0; i < this.coefficients.length; i++) {
            for (int j = 0; j < polynomial2.coefficients.length; j++) {
                newCoefficients[newCoefficients.length - 1 - i - j]
                    += this.coefficients[i] * polynomial2.coefficients[j];
            }
        }

        return new Polynomial(newCoefficients);
    }

    /**
     * Returns the quotient of P(x) / Q(x) divison where 
     * P(x) is {@code this} and Q(x) is {@code polynomial2}.
     * Remainder will be lost.
     * If degree of P(x) is less than degree of Q(x), a zero
     * polynomial with a degree 0 is returned
     * 
     * @param polynomial2 divisor of the polynomial division
     * @return dividend of the polynomial division
     */
    public Polynomial div(Polynomial polynomial2){

        Polynomial quotient = new Polynomial(this);

        Polynomial dividend = new Polynomial();

        Polynomial factorT;

        while (quotient.degree >= polynomial2.degree) {
            factorT = new Polynomial(quotient.degree - polynomial2.degree,
                quotient.coefficients[0]);
            quotient = quotient.sub(polynomial2.mul(factorT));
            dividend = dividend.add(factorT);
        }

        return dividend;
    }

    /**
     * Returns P(Q(x)) where P(x) is {@code this} and Q(x) is {@code polynomial2}
     * 
     * @param polynomial2 second polynomial to plug into {@code this}
     * @return composition of two polinomials
     */
    public Polynomial compose(Polynomial polynomial2){

        Polynomial resultPolynomial = new Polynomial();

        Polynomial takePower;
        Polynomial multiplyCoefficient;

        for (int i = 0; i < coefficients.length; i++) {
            takePower = polynomial2.takePower(getDegree() - i);
            multiplyCoefficient = takePower.mul(new Polynomial(0, coefficients[i]));
            resultPolynomial = resultPolynomial.add(multiplyCoefficient);
        }

        return resultPolynomial;
    }

    // In order to use in javadoc of findEqual()
    private static final int SEARCH_LOWER_BOUND = 0;
    private static final int SEARCH_UPPER_BOUND = 200;

    /**
     * Returns the x values in the interval [{@value #SEARCH_LOWER_BOUND}, 
     * {@value #SEARCH_UPPER_BOUND}] satisfying P(x) = Q(x) where P(x) is
     * {@code this} and Q(x) is {@code polynomial2}
     * 
     * @param polynomial2 second polynomial to compare results
     * @return x values that makes two polynomial equal to each other
     */
    public int[] findEqual(Polynomial polynomial2){
        ArrayList<Integer> commonXValues = new ArrayList<Integer>();

        for (int index = SEARCH_LOWER_BOUND; index <= SEARCH_UPPER_BOUND; index++) {
            if (this.eval(index) == polynomial2.eval(index)) {
                commonXValues.add(index);
            }
        }

        int[] xValues = new int[commonXValues.size()];

        for (int i = 0; i < xValues.length; i++) {
            xValues[i] = commonXValues.get(i);
        }

        return xValues;
    }

    /**
     * Returns the multiplication of {@code this} with itself
     * {@code power} times . Returns itself if {@code power} is
     * less than or equal to 1
     * 
     * @param power number of times to multiply the polinomial by itself
     * @return multiplication of {@code this} with itself
     * {@code power} times
     */
    public Polynomial takePower(int power){

        if (power == 0) {
            return new Polynomial(0,1);
        }

        Polynomial newPolynomial = new Polynomial(this);

        for (int i = 0; i < power - 1; i++) {
            newPolynomial = newPolynomial.mul(this);
        }

        return newPolynomial;
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
     * <p>c<sub></sub> + c<sub>1</sub>x + c<sub>2</sub>x^2 + ... + c<sub>n</sub>x^n </p>
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
            polynomial = polynomial.replaceFirst("\\+ ", "");
        }

        return polynomial;
    }

    /**
     * Prints a detailed info about this polynomial
     * 
     * @param name name of the polynomial that is wanted to be displayed
     */
    public void printDetailedInfo(String name, boolean doRandomCalculations){
        System.out.println("-".repeat(50));
        System.out.println(name + ": " + this);
        System.out.println("Degree: " + degree);
        System.out.printf("Coefficients (Starting from the least significant one):\n");
        for (int i = coefficients.length - 1; i >= 0; i--) {
            System.out.printf("%.1f\t", coefficients[i]);
        }
        System.out.println();

        if (!doRandomCalculations) {
            return;
        }

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