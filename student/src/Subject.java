import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Set;

public class Subject {

    private final String subject;

    private final ArrayList<Long> gradesList = new ArrayList<>();

    public Subject(String subject) {
        this.subject = subject;

    }


    public String getSubject() {
        return this.subject;
    }

    public ArrayList<Long> getGrades() {
        return this.gradesList;
    }


    public void addGrade(long point) {

        this.gradesList.add(point);
    }

    public void printGrades() {
        //System.out.println("inside printGrade()");
        System.out.println("Subject:  " + this.subject);
        for (Long aLong : this.gradesList) {
            System.out.println("Grades : " + aLong);

        }

    }

    public long getAvg() {
        long sum = 0;
        for (Long aLong : this.gradesList)
            sum += aLong;
        return sum / this.gradesList.size();

    }

    public void printAvg() {
        System.out.println("Subject :" + this.subject);
        System.out.println("Average is :" + getAvg());

    }



}
