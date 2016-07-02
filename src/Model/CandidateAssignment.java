package Model;

/**
 * Created by Hyperex on 7/2/2016.
 */
public class CandidateAssignment {

    private StudentEntry student;
    private String previousPreference, currentPreference;

    public CandidateAssignment(StudentEntry s) {
        student = s;
        randomizeAssignment();
    }

    public void randomizeAssignment() {
        previousPreference = currentPreference;
        currentPreference = student.getRandomPreference().intern();
    }

    public void undoChange() {
        currentPreference = previousPreference;
    }

    /**
     * Function for producing: energy = (rank + 1)^2
     */
    public int getEnergy() {
        return (int) Math.pow((student.getRanking(currentPreference) + 1), 2);
    }

    public String getPreference() {
        return currentPreference;
    }

    public String toString() {
        return student.getStudentName()+": "+currentPreference;
    }
}
