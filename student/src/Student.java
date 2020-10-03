import java.util.HashMap;
import java.util.*;
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

    public void printStudent(){
        System.out.println("ID :" + numID + " " + "Name: " + lastName + " , " + firstName);
    }

    public void printGrade(){
        //System.out.println("inside printGrade()");
        if(gradeExist()) {
            for (String subject : grades.keySet()) {
                System.out.println("Subject:  " + subject );
                for (int i : grades.get(subject))
                    System.out.println("Grades : " + i);
            }
        }//end of if
        else
            System.out.println("There is no grade recorded for this student");
    }//end of printGrade()



    public void addGrade(String subject , int point){
        if(!grades.containsKey(subject))
            grades.put(subject, new ArrayList<>());

        grades.get(subject).add(point);
    }

    public void addGrades(String subject , ArrayList<Integer> gradeList){
            grades.put(subject, gradeList);
    }
    public boolean gradeExist() {
        return !grades.isEmpty();
    }

    public int getAvg(String subject){
        int sum = 0;
        for (int i : grades.get(subject))
            sum += i;
        return sum / grades.get(subject).size();

    }

    public void printAvg(){
        if(gradeExist()) {
            for (String subject : grades.keySet()) {
                System.out.println("Subject:  " + subject);
                int avg = getAvg(subject);
                System.out.println("Average is :" + avg);
            }
        }
        else
            System.out.println("There is no grade recorded for this student");
        }//end of printAvg

    public String getFirst(){
        return firstName;
    }
    public String getLast(){
        return lastName;
    }
    public int getID(){
        return numID;
    }

    public Set<String> getSubjects(){
        return this.grades.keySet();
    }
    public ArrayList<Integer> getGrade(String subject ){
        return new ArrayList<>(grades.get(subject));
    }
    @SuppressWarnings("unused")
    public void setStudent(String fname, String lname, int numID){
        this.firstName = fname;
        this.lastName = lname;
        this.numID = numID;
    }
}

