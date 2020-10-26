import org.json.simple.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Student extends Person {

    private HashMap<String, Subject> grades = new HashMap<>();

    public Student(int numID, String firstName, String lastName) {
        super(numID, firstName, lastName);

    }
    /*

    public Student(int numID, String firstName, String lastName, HashMap<String, Subject> grades) {
        super(numID, firstName, lastName);
        this.grades = grades;
    }
*/

    /***
     * Creates a student from a valid json string
     * @param studentData json to parse
     * @return A student if successful null otherwise
     */
    public static Student createFromJson(JSONObject studentData)
    {

        JSONObject studentDetails = (JSONObject) studentData.get("studentData");
        String firstName = (String) studentDetails.get("firstname");
        String lastName = (String) studentDetails.get("lastname");
        int id = (int) (long) studentDetails.get("id");

        HashMap<String, ArrayList<Long>> studentGrades = (HashMap<String, ArrayList<Long>>) studentDetails.get("subject");

        Student student = new Student(id, firstName, lastName);

        if (!studentGrades.isEmpty()) {
            for (String subject : studentGrades.keySet()) {
                student.addGrades(subject, studentGrades.get(subject));
                System.out.println(subject + " : " + studentGrades.get(subject));

            }
        }


        return student;

    }

    public void printStudent() {
        System.out.println("ID :" + getID() + " " + "Name: " + getLast() + " , " + getFirst());
    }

    public void printAvg() {
        if (gradeExist()) {
            for (String subject : grades.keySet()) {
                System.out.println("Subject:  " + subject);
                long avg = grades.get(subject).getAvg();
                System.out.println("Average is :" + avg);
            }
        } else
            System.out.println("There is no grade recorded for this student");
    }//end of printAvg


    public  boolean gradeExist() {
        return !grades.isEmpty();
    }

  /*  public long getAvg(String subject) {

        return getAvg(subject);

    }

*/

    public Set<String> getSubjects() {
        return this.grades.keySet();
    }


    public void addGrades(String subject, ArrayList<Long> gradeList) {
        //build subject then add
        for (Long aLong : gradeList)
            addGrade(subject, aLong);

    }

    public void addGrade(String subject, long point) {

        if (!grades.containsKey(subject)) {
            Subject aSubject = new Subject(subject);
            aSubject.addGrade(point);
            grades.put(subject,aSubject);
        }
        else
            grades.get(subject).addGrade(point);
    }


    public ArrayList<Long> getGrade(String subject) {
        return grades.get(subject).getGrades();
    }

    public void printGrade() {
        //System.out.println("inside printGrade()");
        if (gradeExist()) {
            for (String subject : grades.keySet()) {
               grades.get(subject).printGrades();
            }
        }
        else
            System.out.println("There is no grade recorded for this student");
    }



}

