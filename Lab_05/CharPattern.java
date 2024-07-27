/**
 * A 2D char array with some fill functionality containing some patterns.
 */
public class CharPattern {
    private char[][] array;

    /**
     * Returns a {@code CharPattern} object that has a 2D char array with some functionality.
     * 
     * @param rows number of rows that 2D array has
     * @param columns number of columns that 2D array has
     */
    public CharPattern(int rows, int columns){
        this.array = createArray(rows, columns);
        // Initializes all the chars to 0 to demonstration
        fillAll('0');
    }

    /**
     * Creates a 2D array with the given dimensions
     * 
     * @param numRows number of rows that 2D array has
     * @param numColumns number of columns that 2D array has
     * @return 2D array with the given dimensions
     */
    public static char[][] createArray(int numRows, int numColumns){
        return new char[numRows][numColumns];
    }

    /**
     * Fills {@code this} with the given char inside the given rectangle
     * 
     * @param c Char to fill this {@code CharPattern} with
     * @param rowStart index of the top side of the rectangle to be filled, inclusive
     * @param rowEnd index of the bottom side of the rectangle to be filled, exclusive
     * @param columnStart index of the left side of the rectangle to be filled, inclusive
     * @param columnEnd index of the right side of the rectangle to be filled, exclusive
     */
    public void fillChar(char c, int rowStart, int rowEnd, int columnStart, int columnEnd){
        if (rowEnd == rowStart) {
            return;
        }
        fillChar(c, rowStart + 1, rowEnd, columnStart, columnEnd);
        fillRow(c, rowStart, columnStart, columnEnd);
    }

    /**
     * Fills the given row of {@code this} with the given char
     * 
     * @param c Char to fill this {@code CharPattern} with
     * @param rowIndex Row index to fill
     * @param columnStart index of the left side of the rectangle to be filled, inclusive
     * @param columnEnd index of the right side of the rectangle to be filled, exclusive
     */
    private void fillRow(char c, int rowIndex, int columnStart, int columnEnd){
        if (columnEnd == columnStart) {
            return;
        }
        fillRow(c, rowIndex, columnStart + 1, columnEnd);
        array[rowIndex][columnStart] = c;
    }

    /**
     * Fills {@code this} with the following pattern:
     * <pre>
     * 3x3      5x6         6x7
     * 
     * ***     ******     *******
     * *#*     *####*     *#####* 
     * ***     *#**#*     *#***#*  ...
     *         *####*     *#***#* 
     *         ******     *#####* 
     *                    *******
     * </pre>
     */
    public void pattern1(){
        pattern1Helper(0);
    }

    /**
     * Recursive method to implement pattern 1
     * 
     * @param currentStep Step number starting from 0 up to <br><br>
     * {@code (int) Math.ceil(Math.min(getRowLength(), getColumnHeight()) / 2) + 1}
     * <br><br>
     * Each step corresponds to a rectangle frame made of either * or #. The outmost rectangle
     * corresponds to step 0
     */
    private void pattern1Helper(int currentStep){
        // Base case
        if (currentStep - 1 == (int) Math.ceil(Math.min(getRowLength(), getColumnHeight()) / 2)){
            return;
        }

        // Decide the char to fill with
        char fillChar = '*';
        if (currentStep % 2 == 1){ 
            fillChar = '#';
        }

        pattern1Helper(currentStep + 1);

        // Fill top side, bottom side, left side, right side in order
        fillRow(fillChar, currentStep, currentStep , getRowLength() - currentStep);
        fillRow(fillChar, getColumnHeight() - 1 - currentStep, currentStep, getRowLength() - currentStep);
        fillChar(fillChar, currentStep, getColumnHeight() - currentStep, currentStep, currentStep + 1);
        fillChar(fillChar, currentStep, getColumnHeight() - currentStep, 
            getRowLength() - currentStep - 1, getRowLength() - currentStep);
    }

    /**
     * Fill the whole array with # and replace them with 1x{@code fillWidth} rectangles made of *.
     * Start from column index 0 and shift these rectangles by {@code shiftAmount} in each row. If these
     * *s hit the right side, continue to fill from column 0. An example:
     * <pre>
     * 10x10
     * *****#####
     * ##*****###
     * ####*****#
     * *#####****
     * ***#####**
     * *****#####
     * ##*****###
     * ####*****#
     * *#####****
     * ***#####**
     * </pre>
     * @param fillWidth width of the 1 height * rectangles
     * @param shiftAmount shift amount to move rectangles in each row
     */
    public void pattern2(int fillWidth, int shiftAmount){
        if (fillWidth > getRowLength()) {
            fillWidth = getRowLength();
        }
        pattern2Helper(fillWidth, shiftAmount, 0);
    }

    /**
     * Recursive method to implement pattern2
     *
     * @param fillWidth width of the 1 height * rectangles
     * @param shiftAmount shift amount to move rectangles in each row
     * @param currentRow keeps track of the row number
     */
    private void pattern2Helper(int fillWidth, int shiftAmount, int currentRow){
        // Base case
        if (currentRow >= getColumnHeight()) {
            return;
        }

        pattern2Helper(fillWidth, shiftAmount, currentRow + 1);
        
        // Fill the whole row with #
        fillRow('#', currentRow, 0, getRowLength());
        
        // Calculate the position of stars
        int starStart = (shiftAmount * currentRow) % getRowLength();
        int starEnd = starStart + fillWidth;
        if (starEnd > getRowLength()) {
            fillRow('*', currentRow, 0, starEnd % getRowLength());
            starEnd = getRowLength();
        }

        // Replace # with * in correct indexes
        fillRow('*', currentRow, starStart, starEnd);
    }

    /**
     * Fill the first row and first column with 1.
     * Then, it fill the rest of the array such that for each element arr(x,y) = (arr(x-1,y) + arr(x,y-1)) % 10
     */
    public void pattern3(){
        //Fill the first row and column
        fillRow('1', 0, 0, getRowLength());
        fillChar('1', 0, getColumnHeight(), 0, 1);
        
        // Calculate and fill the rest
        pattern3Helper(getColumnHeight() - 1, getRowLength() - 1);
    }

    /**
     * Recursive method to implement pattern 3
     * 
     * @param currentRow keeps track of the current row starting from the bottom side
     * @param currentColumn keeps track of the current column starting from the right side
     */
    private void pattern3Helper(int currentRow, int currentColumn){
        if (currentRow == 0) {
            return;
        }
        // Goes the top side with recursion
        pattern3Helper(currentRow - 1, currentColumn);
        
        // Starts to fill the columns in this row
        pattern3ColumnFiller(currentRow, currentColumn);
    }

    /**
     * Recursive method to implement fill rows in a column according to the pattern 3
     *
     * @param currentRow keeps the current row for this recursion
     * @param currentColumn keep track of the current column in this row to fill
     */
    private void pattern3ColumnFiller(int currentRow, int currentColumn){
        // Base case
        if (currentColumn == 0) {
            return;
        }

        // Goes to the left side of the rectangle to fill
        pattern3ColumnFiller(currentRow, currentColumn - 1);

        // Finds the neccessary numbers
        char left = array[currentRow][currentColumn - 1];
        char top = array[currentRow - 1][currentColumn];

        // Assigns this cell
        array[currentRow][currentColumn] = String.valueOf((Integer.parseInt(String.valueOf(top)) +
            Integer.parseInt(String.valueOf(left))) % 10).charAt(0);
    
    }

    /**
     * Assuming pattern3() is called before, finds thehighest row sum
     * 
     * @return the highest row sum in a {@code CharPattern} implemented pattern 3
     */
    public int findMaxRowSum(){
        return findMaxRowSumHelper(getColumnHeight() - 1);

    }

    /**
     * Recursive method to find the max row sum for the rows before {@code row}, inclusive
     * 
     * @param row keep track of the current row
     * @return max row sum before {@code row}, inclusive
     */
    private int findMaxRowSumHelper(int row){
        if (row == -1) {
            return Integer.MIN_VALUE;
        }

        int rowSum = findRowSum(row, getRowLength() - 1);

        return Math.max(findMaxRowSumHelper(row - 1), rowSum);
    }

    /**
     * Recursive method to find a row's sum
     * 
     * @param rowIndex keep the row index for this recursion
     * @param columnIndex keep track of the column index starting from the right side
     * @return the sum of the row with the index {@code rowIndex}
     */
    private int findRowSum(int rowIndex, int columnIndex){
        if (columnIndex == -1) {
            return 0;
        }
        return findRowSum(rowIndex, columnIndex - 1) + Integer.parseInt(String.valueOf(array[rowIndex][columnIndex]));
    }

    /**
     * Fill the whole array with a single char
     * 
     * @param c char to fill this whole 2D array
     */
    public void fillAll(char c){
        fillChar(c, 0, array.length, 0, getRowLength());
    }

    /**
     * Total number of cells in this 2D array
     * 
     * @return literally {@code getRowLength() * getColumnHeight()}
     */
    public int getTotalLength(){
        return getRowLength() * getColumnHeight();
    }

    /**
     * Number of columns in this 2D array
     * 
     * @return literally {@code array[0].length}
     */
    public int getRowLength(){
        return array[0].length;
    }

    /**
     * Number of rows in this 2D array
     * 
     * @return literally {@code array.length}
     */
    public int getColumnHeight(){
        return array.length;
    }

    @Override
    public String toString() {
        String stringRepWithNewLine = stringRepresentationUpTo(getColumnHeight() - 1, getRowLength() - 1);
        return stringRepWithNewLine.substring(0, stringRepWithNewLine.length() - 1);
    }

    /**
     * Recursive method to find the string representation of this {@code CharPattern} 
     * from (0,0) up to a specific row and column
     *
     * @param rowIndex bottom side of the returned grid, inclusive
     * @param columnIndex right side of the returned grid, inclusive
     * @return string representation of this 2D array
     */
    private String stringRepresentationUpTo(int rowIndex, int columnIndex){
        if (rowIndex == -1){
            return "";
        }
        return stringRepresentationUpTo(rowIndex - 1, columnIndex) + rowRepresentationUpTo(rowIndex, columnIndex) + "\n";
    }

    /**
     * Returns the string representation of the row given by {@code rowIndex}
     * 
     * @param rowIndex keep the row index to iterate over
     * @param columnIndex keep track of the current column index to add
     * @return string representation of the given row
     */
    private String rowRepresentationUpTo(int rowIndex, int columnIndex){
        if (columnIndex == -1) {
            return "";
        }
        return rowRepresentationUpTo(rowIndex, columnIndex - 1) + array[rowIndex][columnIndex];
    }

    /**
     * Print this whole 2D array as grid
     */
    public void print(){
        System.out.println(toString());
    }
}