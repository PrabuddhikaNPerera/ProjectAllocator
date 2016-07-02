package Model;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by Hyperex on 7/2/2016.
 */
public class CandidateSolution {
    private Hashtable<String, CandidateAssignment> table;
    private final int PENALTY = 1000;

    public CandidateSolution(PreferenceTable p) {
        this.table = new Hashtable<String, CandidateAssignment>();
        for(StudentEntry student : p.getAllStudentEntries()) {
            table.put(student.getStudentName(), new CandidateAssignment(student));
        }
    }

    public CandidateAssignment getAssignmentFor(String sname) {
        return table.get(sname);
    }

    public CandidateAssignment getRandomAssignment() {
        Vector<String> keySet = new Vector<String>(table.keySet());
        return table.get(keySet.elementAt(PreferenceTable.RND.nextInt(keySet.size())));
    }

    /**
     * Iterates through all candidateAssignments summing their energy. During each iteration
     * if an assignment has appeared before, a counter is incremented. This counter is
     * multiplied by PENALTY and summed with energy.
     */
    public int getEnergy() {
        int energy = 0, counter = 0;
        Iterator<String> it = table.keySet().iterator();
        Hashtable<String, Integer> collisionCounter = new Hashtable<String, Integer>();

        while (it.hasNext()) {
            String sname = it.next();
            energy += table.get(sname).getEnergy();
            if (!collisionCounter.containsKey(table.get(sname).getPreference()))  {
                collisionCounter.put(table.get(sname).getPreference(), 1);
            } else {
                counter++;
            }
        }
        energy += (counter*PENALTY);
        return energy;
    }

    public int getFitness() {
        return -(getEnergy());
    }
}
