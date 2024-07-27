/**
 * Represents a Student who definitely has at least 1 grade. Can be used to calculate average.
 */
public class GradedStudent extends Student{
    protected float average;

    /**
     * Returns a GradedStudent representation of the given student. Average for the GradedStudent is
     * updated during the initialization and when a new grade is added
     * 
     * @param existingStudent student object with at least one grade
     * @throws Exception if the given student has no grade
     */
    public GradedStudent(Student existingStudent) throws Exception{
        super(
            existingStudent.getName(),
            existingStudent.getSurname(),
            existingStudent.getSchoolID(),
            existingStudent.getAge());

        try {
            if (existingStudent.getGrades()[0].equals(null)) {
                throw new Exception(existingStudent + "has no grades yet. Cannot create GradedStudent.");
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        grades = existingStudent.getGrades();

        updateAverage();
    }

    /**
     * Recalculates and updates average grade of this student
     */
    public void updateAverage(){
        float weightedSumOfGrades = 0;
        float sumOfWeights = 0;

        for (Grade grade : getGrades()) {
            if (grade == null) {
                continue;
            }
            weightedSumOfGrades += grade.getPoints() * grade.getWeight();
            sumOfWeights += grade.getWeight();
        }

        average = weightedSumOfGrades / sumOfWeights;
    }

    @Override
    public void setGrade(String examName, float weight, float points){
        super.setGrade(examName, weight, points);

        updateAverage();
    }

    public float getAverage() {
        return average;
    }
}
