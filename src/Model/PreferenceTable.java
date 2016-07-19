package Model;

import java.io.*;
import java.util.Hashtable;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.Vector;

public class PreferenceTable {
    private Vector<Vector<String>> table;
    private Hashtable<String, StudentEntry> studentLookup = new Hashtable<String, StudentEntry>();
    static Random RND = new Random();

    /**
     * Empty constructor
     */
    public PreferenceTable() {

    }

    /**
     * Constructor that takes a file name, opens said file and laods the contents
     *
     * @param  filename  the path to the file
     */
    public PreferenceTable(File filename) {
        table = loadContentFromFile(filename);
    }

    /**
     * Opens a file, reads in each line which it splits into tokens and then stores
     * each token as a String in a Vector.
     * Each line is represented as a Vector of Strings and the table is represented
     * as a Vector of Vectors of Strings.
     *
     * @param filename the path to the file
     * @return the vector representing the file content
     */
    public Vector<Vector<String>> loadContentFromFile(File filename) {
        Vector<Vector<String>> t = new Vector<Vector<String>>();
        FileInputStream stream = null;
        BufferedReader input;
        String line;
        StringTokenizer tokens;

        try {
            stream = new FileInputStream(filename);
        } catch (FileNotFoundException e) {
            System.out.println("File is not found!");
            e.printStackTrace();
        }

        input = new BufferedReader(new InputStreamReader(stream));

        try {
            while((line=input.readLine())!=null) {
                tokens = new StringTokenizer(line, "\t");
                Vector<String> v = new Vector<String>();
                while(tokens.hasMoreTokens()){
                    String next = tokens.nextToken();
                    v.add(next);
                }
                t.add(v);
                if(t.size()>1) {
                    StudentEntry student = new StudentEntry(v.get(0));
                    if(v.get(1).equalsIgnoreCase("yes")) {
                        student.setHasProjectPreassigned(true);
                    } else {
                        student.setHasProjectPreassigned(false);
                    }
                    Vector<String> preferredProjects = new Vector<String>();
                    for(int i=2; i<v.size(); i++) {
                        preferredProjects.add(v.get(i));
                    }
                    student.setOrderedPreferences(preferredProjects);
                    studentLookup.put(v.get(0), student);
                }
            }
            input.close();
        } catch (IOException e) {
            System.out.println("Error reading file!");
            e.printStackTrace();
        }

        return t;
    }

    public Vector<StudentEntry> getAllStudentEntries() {
        Vector<StudentEntry> studentEntries = new Vector<StudentEntry>();
        for(String key : studentLookup.keySet()) {
            studentEntries.addElement(studentLookup.get(key));
        }
        return studentEntries;
    }

    public StudentEntry getEntryFor(String sname) {
        try {
            return studentLookup.get(sname);
        } catch (NullPointerException e) {
            return null;
        }

    }

    public StudentEntry getRandomStudent() {
        Vector<String> keySet = new Vector<String>(studentLookup.keySet());
        return studentLookup.get(keySet.elementAt(RND.nextInt(keySet.size())));
    }

    public String getRandomPreference() {
        StudentEntry student;
        String randomPreference = null;
        while (randomPreference == null) {
            student = getRandomStudent();
            if(!student.hasPreassignedProject()) {
                randomPreference = student.getRandomPreference();
            }
        }
        return randomPreference;
    }

    public void fillPreferencesOfAll(int maxPrefs) {
        for(StudentEntry student : getAllStudentEntries()) {
            if(!student.hasPreassignedProject()) {
                while(student.getOrderedPreferences().size() != maxPrefs) {
                    student.addProject(getRandomPreference());
                }
            }
        }
    }

    /**
     * Prints the Vector out to the console, used for testing
     */
    public void printVectorContents() {
        System.out.println(table);
    }
}
