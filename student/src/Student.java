public class Student {
    String first_name;
    String last_name;
    int id;

    public Student(int num_id, String fname, String lname) {
        first_name = fname;
        last_name = lname;
        id = num_id;
    }

    public void Print_Student(){
        System.out.println("ID :" + id + " " + "Name: " + first_name + " " + last_name);
    }


}