public class main {


    public static void main(String[] args) {

        Student student = new Student(111, "Kaori", "Minami");
        System.out.println(student.id + student.first_name + student.last_name);
        student.Print_Student();
    }
}
