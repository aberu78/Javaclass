
import java.util.Scanner; //used to read input             00000

public class main {


    public static void main(String[] args) {

        String firstName;
        String lastName;
        int student_id;

        Scanner myStudent = new Scanner(System.in);

        System.out.println("Enter First Name");
        firstName = myStudent.nextLine();
        System.out.println("Enter Last Name");
        lastName = myStudent.nextLine();
        System.out.println("Enter student id");
        student_id = Integer.parseInt(myStudent.nextLine());

        Student student = new Student(student_id, firstName, lastName);
        //System.out.println(student.getID() + student.getFirst() + student.getLast());
        student.Print_Student();








    }
}
