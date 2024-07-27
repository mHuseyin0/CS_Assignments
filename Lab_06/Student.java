/**
 * Represents a Student object with name, surname, schoolID, age, and {@code Grade}s
 */
public class Student{
    protected String name;
    protected String surname;
    protected String schoolID;
    protected int age;
    protected Grade[] grades;
    private boolean isGraded;
    
    /**
     * Returns a Student object with an empty {@code Grade} array
     * 
     * @param name name of the {@code Student}
     * @param surname surname of the {@code Student}
     * @param schoolID unique ID of the {@code Student}
     * @param age age of the {@code Student}
     */
    public Student(String name, String surname, String schoolID, int age){
        this.name = name;
        this.surname = surname;
        this.schoolID = schoolID;
        this.age = age;
        this.isGraded = false;

        grades = new Grade[1];
    }

    /**
     * Adds a new grade with the given parameters. If the given examName is already exists in this
     * Student's grades, sets its values with the given parameters
     * 
     * @param examName name of the exam, must be longer than 3 characters
     * @param weight weight of the exam
     * @param points points that this student got from the exam
     */
    public void setGrade(String examName, float weight, float points) throws Exception{
        if (examName.length() <= 3) {
            throw new Exception(examName + " must be longer than 3 characters!");
        }
        

        // If exam already exists
        for (Grade grade : grades) {
            if (grade != null && grade.getExamName().equals(examName)) {
                grade.setPoints(points);
                grade.setWeight(weight);
                return;
            }
        }


        Grade newGrade = new Grade(examName, weight, points); 
        // If that is the first grade
        if (!isGraded) {
            grades[0] = newGrade;
            isGraded = true;
            return;
        }

        // Add a new grade to the end
        Grade[] newGrades = new Grade[grades.length + 1];
        
        // Copy the array with one more length
        for (int i = 0; i < grades.length; i++) {
            newGrades[i] = grades[i];
        }
        newGrades[grades.length] = newGrade;
        
        this.grades = newGrades;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getSchoolID() {
        return schoolID;
    }

    public int getAge() {
        return age;
    }

    public Grade[] getGrades() {
        return grades;
    }

    @Override
    public String toString() {
        return String.format("%s, %s %s, %d", getSchoolID(), getName(), getSurname(), getAge());
    }
}