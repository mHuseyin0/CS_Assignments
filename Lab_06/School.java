import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Represent a School which holds an array of students and contains some functionality about students
 */
public class School {
    private Student[] students;
    private int studentCount = 0;

    /**
     * Returns a school object with empty students array
     */
    public School(){
        students = new Student[10];
    }

    /**
     * Adds a student with the given parameters to this school
     * 
     * @param schoolID unique ID of the new student
     * @param name name of the new student
     * @param surname surname of the new student
     * @param age age of the new student
     * @throws Exception if the given ID already exists in this school
     */
    public void addStudent(String schoolID, String name, String surname, int age) throws Exception{
        for (Student student : students) {
            if (student != null && student.getSchoolID().equals(schoolID)){
                throw new Exception("Duplicate ID: " + schoolID);
            }
        }

        // Increase the size if needed
        if (studentCount + 1 > students.length / 2){
            Student[] newStudents = new Student[students.length * 2];
            for (int i = 0; i < studentCount; i++) {
                newStudents[i] = students[i];
            }
            this.students = newStudents;
        }

        // Find insert index
        int insertIndex = 0;
        for (int i = 0; i < studentCount; i++) {
            if (Double.parseDouble(students[i].getSchoolID()) < Double.parseDouble(schoolID)){
                insertIndex = i + 1;
            }
        }

        // Shift the students
        for (int i = studentCount - 1; i >= insertIndex; i--) {
            students[i + 1] = students[i];
        }

        students[insertIndex] = new Student(name, surname, schoolID, age);
        studentCount++;
    }

    /**
     * Returns the student with the given ID
     * 
     * @param schoolID unique ID of the student
     * @return Student whose ID was given
     * @throws Exception if there is no student with the given ID
     */
    public Student getStudent(String schoolID) throws Exception{

        // Binary Search

        int low = 0;
        int high = studentCount - 1;
        int mid;

        while (low <= high) {
            mid = low + (high - low) / 2;

            if (Double.parseDouble(students[mid].getSchoolID()) < Double.parseDouble(schoolID)) {
                low = mid + 1;
            }
            else if (Double.parseDouble(students[mid].getSchoolID()) > Double.parseDouble(schoolID)) {
                high = mid - 1;
            }
            else{
                return students[mid];
            }
        }

        throw new Exception(String.format("No such student with the ID %s!", schoolID));
    }

    /**
     * Returns the existing students in an array where the students are ordered by their name and surname
     * @return
     */
    public Student[] getStudentsByNameOrder(){
        Student[] studentsSorted = new Student[students.length];
        
        for (int i = 0; i < studentsSorted.length; i++) {
            studentsSorted[i] = students[i];
        }

        int leftCursor = 0;
        int rightCursor = studentCount - 1;
        
        quickSortStudentsByName(studentsSorted, leftCursor, rightCursor);
        return studentsSorted;
    }

    private void quickSortStudentsByName(Student[] studentArray, int start, int end){
        if (start >= end) {
            return;
        }

        Student pivot = studentArray[start];
        Student temp;

        int cursor1 = end;
        int cursor2 = end;

        while (start < cursor1) {
            if (compareStudentsByNameSurname(studentArray[cursor1], pivot) > 0) {
                temp = studentArray[cursor1];
                
                studentArray[cursor1] = studentArray[cursor2];
                studentArray[cursor2] = temp;

                cursor2--;
            }
            cursor1--;
        }

        temp = studentArray[cursor2];
                
        studentArray[cursor2] = studentArray[start];
        studentArray[start] = temp;

        quickSortStudentsByName(studentArray, start, cursor2 - 1);
        quickSortStudentsByName(studentArray, cursor2 + 1, end);
    }

    /**
     * Returns a negative number if s1 comes before alphabetically.
     * Returns a positive number if s2 comes before alphabetically.
     * Returns 0 if they are equal.
     * 
     * @param s1 student1 to compare
     * @param s2 student2 to compare
     * @return s1.getName().compareTo(s2.getName()) literally.
     * If the names are equal, applies the same for the surnames
     */
    private int compareStudentsByNameSurname(Student s1, Student s2){
        if (s1.getName().compareTo(s2.getName()) == 0) {
            return s1.getSurname().compareTo(s2.getSurname());
        }
        return s1.getName().compareTo(s2.getName());
    }

    /**
     * Prints students ordered by name
     */
    public void printStudentsByNameOrder(){
        System.out.println();
        
        Student[] s = getStudentsByNameOrder();

        for (int i = 0; i < studentCount; i++) {
            System.out.println(s[i]);;
        }
    }

    /**
     * Prints students ordered by their creation time
     */
    public void printStudents(){
        System.out.println();

        for (int i = 0; i < studentCount; i++) {
            System.out.println(students[i]);
        }
    }

    /**
     * Average of the all grades of a student
     * 
     * @param s student object to return average
     * @return average of the student
     * @throws Exception if student has no grade
     */
    public float getGradeAverage(Student s) throws Exception{
        return (new GradedStudent(s)).getAverage();
    }

    /**
     * Print the students ordered by their average grades
     */
    public void printStudentGradeAverages(){
        
        // Detect the students who has at least 1 grade
        int gradedStudentCount = 0;
        GradedStudent[] gradedStudents = new GradedStudent[studentCount];
        for (int i = 0; i < studentCount; i++) {
            if (students[i].getGrades()[0] == null){
                continue;
            }
            try {
                gradedStudents[gradedStudentCount] = new GradedStudent(students[i]);
                gradedStudentCount++;
            }
            catch (Exception e){
                continue;
            }
        }

        // Shrink the arrays if there is any student who has no grade
        if (gradedStudentCount < studentCount) {
            GradedStudent[] temp = new GradedStudent[gradedStudentCount];
            for (int i = 0; i < temp.length; i++) {
                temp[i] = gradedStudents[i];
            }
            gradedStudents = temp;
        }

        // Bubble Sort
        GradedStudent swapTemp;
        for (int i = 0; i < gradedStudents.length; i++) {
            for (int j = 0; j < gradedStudents.length - 1; j++) {
                if (gradedStudents[j].getAverage() < gradedStudents[j + 1].getAverage() ||

                    (gradedStudents[j].getAverage() == gradedStudents[j + 1].getAverage() &&
                        Double.parseDouble(gradedStudents[j].getSchoolID()) > 
                        Double.parseDouble(gradedStudents[j + 1].getSchoolID()))) {

                    swapTemp = gradedStudents[j];
                    gradedStudents[j] = gradedStudents[j + 1];
                    gradedStudents[j + 1] = swapTemp;
                }
            }
        }

        // Print them in order
        System.out.println();
        for (GradedStudent gradedStudent : gradedStudents) {
            System.out.printf("%s - Average: %.1f%n", gradedStudent, gradedStudent.getAverage());
        }
    }

    /**
     * Prints grades of a student
     * 
     * @param schoolID unique ID of the student
     * @throws Exception if the given ID does not exist
     */
    public void printGradesOf(String schoolID) throws Exception{
        Student student = getStudent(schoolID);

        System.out.println();
        System.out.println("Student:" + student);
        System.out.println("Grades:");

        for (Grade grade : student.getGrades()) {
            System.out.println(grade);
        }
    }

    /**
     * Takes input from a text file line by line
     * 
     * @param filename filename of the textfile to be read
     * @throws Exception if the file does not exist
     */
    public void processTextFile(String filename) throws Exception{
        File inputFile = new File(filename);
        Scanner scanner;
        
        try {
            scanner = new Scanner(inputFile);
        } catch (FileNotFoundException e) {
            System.out.println(filename + " could not be found.");
            return;
        }

        String inputCommand;
        while (scanner.hasNextLine()) {
            inputCommand = scanner.nextLine().trim();

            try {
                if (inputCommand.contains("Student:")) {
                    processCreateStudent(inputCommand.replace("Student:", ""));
                }
                else if (inputCommand.contains("Grade:")) {
                    processAddGrade(inputCommand.replace("Grade:", ""));
                }
                else if (inputCommand.contains("GradesOf:")) {
                    processGradesOf(inputCommand.replace("GradesOf:", ""));
                }
                else if (inputCommand.equals("PrintByNameOrder")) {
                    printStudentsByNameOrder();
                }
                else if (inputCommand.equals("PrintByGradeAverages")){
                    printStudentGradeAverages();
                }
                else if (inputCommand.equals("PrintStudents")) {
                    printStudents();
                }
                else{
                    throw new Exception(inputCommand.trim() + " is not a valid input!");                    
                }
            } catch (Exception e) {
                System.out.println();
                System.out.println(e.getMessage());
            }
        }
        scanner.close();
    }

    /**
     * Creates a student with the given parameters in the textfile
     * 
     * @param command command excluding the indicator at the beginning
     * @throws Exception if the given ID is not uniuqe
     */
    private void processCreateStudent(String command) throws Exception{
        int commaIndex = command.indexOf(",");
        String nameSurname = command.substring(0, commaIndex);
        
        int spaceIndex = nameSurname.lastIndexOf(" ");

        String name = nameSurname.substring(0, spaceIndex).trim();
        String surname = nameSurname.substring(spaceIndex + 1);

        command = command.substring(commaIndex + 1);
        commaIndex = command.indexOf(",");

        int age = Integer.parseInt(command.substring(0, commaIndex).trim());
        command = command.substring(commaIndex + 1);
        
        String schoolID = command.substring(0).trim();

        addStudent(schoolID, name, surname, age);
    }

    /**
     * Adds a grade to a student specified in the command
     * 
     * @param command command excluding the indicator at the beginning
     * @throws Exception if the given ID pes not exist in this school
     */
    private void processAddGrade(String command) throws Exception{
        int commaIndex = command.indexOf(",");

        String schoolID = command.substring(0, commaIndex);
        command = command.substring(commaIndex + 1);

        commaIndex = command.indexOf(",");
        String examName = command.substring(0, commaIndex).trim();

        command = command.substring(commaIndex + 1);
        commaIndex = command.indexOf(",");

        float weigth = Float.parseFloat(command.substring(0, commaIndex).trim());
        float points = Float.parseFloat(command.substring(commaIndex + 1).trim());

        getStudent(schoolID).setGrade(examName, weigth, points);
    }

    private void processGradesOf(String command) throws Exception{
        printGradesOf(command);
    }

}
