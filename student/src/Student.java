import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 *
 */
public class Student {

    private String firstName;
    private String lastName;
    private int numID;
    private final HashMap<String, ArrayList<Integer>> grades = new HashMap<>();

    public Student(int id, String fname, String lname) {
        firstName = fname;
        lastName = lname;
        numID = id;
    }//end of Student

    /***
     * Creates a student from a valid json string
     * @param studentData json to parse
     * @return A student if successful null otherwise
     */
    public static Student createFromJson(JSONObject studentData)
    {
        Student result = null;

        JSONObject studentDetails = (JSONObject) studentData.get("studentData");
        String firstName = (String) studentDetails.get("firstname");
        String lastName = (String) studentDetails.get("lastname");
        int id = (int) (long) studentDetails.get("id");

        result = new Student(id, firstName, lastName);
        HashMap<String, ArrayList<Long>> studentGrades = (HashMap<String, ArrayList<Long>>) studentDetails.get("subject");

        if (!studentGrades.isEmpty()) {
            for (String subject : studentGrades.keySet()) {
                ArrayList<Integer> tmp = new ArrayList<>();
                ArrayList<Long> gradesList = studentGrades.get(subject);
                gradesList.forEach(grade -> tmp.add((int) (long) grade));
                result.addGrades(subject, tmp);
            }
        }
        else
        {
            result = null;
        }
        return result;
    }

    public void printStudent() {
        System.out.println("ID :" + numID + " " + "Name: " + lastName + " , " + firstName);
    }

    public void printGrade() {
        //System.out.println("inside printGrade()");
        if (gradeExist()) {
            for (String subject : grades.keySet()) {
                System.out.println("Subject:  " + subject);
                for (int i : grades.get(subject))
                    System.out.println("Grades : " + i);
            }
        }//end of if
        else
            System.out.println("There is no grade recorded for this student");
    }//end of printGrade()

    /**
     * Adds a grade to the student
     * @param subject The subject to add a grade too.
     * @param point The grade
     */
    public void addGrade(String subject, int point) {
        if (!grades.containsKey(subject))
            grades.put(subject, new ArrayList<>());

        grades.get(subject).add(point);
    }

    public void addGrades(String subject, ArrayList<Integer> gradeList) {
        grades.put(subject, gradeList);
    }

    public boolean gradeExist() {
        return !grades.isEmpty();
    }

    public int getAvg(String subject) {
        int sum = 0;
        for (int i : grades.get(subject))
            sum += i;
        return sum / grades.get(subject).size();

    }

    public void printAvg() {
        if (gradeExist()) {
            for (String subject : grades.keySet()) {
                System.out.println("Subject:  " + subject);
                int avg = getAvg(subject);
                System.out.println("Average is :" + avg);
            }
        } else
            System.out.println("There is no grade recorded for this student");
    }//end of printAvg

    public String getFirst() {
        return firstName;
    }

    public String getLast() {
        return lastName;
    }

    public int getID() {
        return numID;
    }

    public Set<String> getSubjects() {
        return this.grades.keySet();
    }

    public ArrayList<Integer> getGrade(String subject) {
        return new ArrayList<>(grades.get(subject));
    }

    @SuppressWarnings("unused")
    public void setStudent(String fname, String lname, int numID) {
        this.firstName = fname;
        this.lastName = lname;
        this.numID = numID;
    }
}

