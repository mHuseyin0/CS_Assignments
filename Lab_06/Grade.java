/**
 * Represents a grade taken from an exam by a student.
 */
public class Grade{
    private String examName;
    private float weight;
    private float points;

    /**
     * Returns a grade object with the given parameters
     * 
     * @param examName name of the exam
     * @param weight weight of the exam, used when calculating the average grade of the student
     * @param points point that this student got from the exam
     */
    public Grade(String examName, float weight, float points){

        this.examName = examName;
        this.weight = weight;
        this.points = points;
    }

    public void setPoints(float points) {
        this.points = points;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getExamName() {
        return examName;
    }

    public float getWeight() {
        return weight;
    }

    public float getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return String.format("%s (Weight: %.1f) %.1f", examName, weight, points);
    }
}