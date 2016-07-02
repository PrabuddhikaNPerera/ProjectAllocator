package Model;

import java.util.Vector;

/**
 * Created by Hyperex on 7/2/2016.
 */
public class StudentEntry {

    private String name;
    private boolean hasProjectPreassigned;
    private Vector<String> orderedPreferences;
    private int numberOfStatedPreferences;
    private String projectAssigned = null;

    public StudentEntry(String n) {
        name = n;
    }

    /**
     *
     * @return a String containing the name of this particular student
     */
    public String getStudentName() {
        return name;
    }

    public void setHasProjectPreassigned(boolean bool) {
        hasProjectPreassigned = bool;
    }

    public void setOrderedPreferences(Vector<String> v) {
        orderedPreferences = v;
        numberOfStatedPreferences = orderedPreferences.size();
    }

    public Vector<String> getOrderedPreferences() {
        return orderedPreferences;
    }

    public void preassignProject(String pname) {
        if(orderedPreferences.size()==1 && hasProjectPreassigned) {
            projectAssigned = pname;
        }
    }

    public boolean hasPreassignedProject() {
        if(hasProjectPreassigned && projectAssigned != null) {
            return true;
        }
        return false;
    }

    public int getNumberOfStatedPreferences() {
        return numberOfStatedPreferences;
    }

    public void addProject(String pname) {
        if(!hasPreference(pname)) {
            orderedPreferences.addElement(pname);
        }
    }

    public String getRandomPreference() {
        return orderedPreferences.get(PreferenceTable.RND.nextInt(orderedPreferences.size()));
    }

    public boolean hasPreference(String preference) {
        return orderedPreferences.contains(preference.intern());
    }

    /**
     * This method calculates the level of disappointment a student has with it assigned
     * project.
     *
     * @param pname the name of project in preferenceList to find rank for
     * @return return rank value
     */
    public int getRanking(String pname) {
        if(hasPreassignedProject()) {
            return 0;
        }
        else if(hasPreference(pname.intern())) {
            return orderedPreferences.indexOf(pname);
        }
        else {
            return -1;
        }
    }

    public String toString() {
        return "Name: "+name+" prefereces:"+getOrderedPreferences();
    }
}

