public class Student {

    private String first_name;
    private String last_name;
    private int num_id;


    public Student(int id, String fname, String lname) {
       first_name = fname;
       last_name = lname;
       num_id = id;

    }
    public void Print_Student(){
        System.out.println("insude Print_Student method");
        System.out.println("ID :" + num_id + " " + "Name: " + first_name + " " + last_name);
    }

    public String getFirst(){
        return first_name;
    }
    public String getLast(){
        return last_name;
    }
    public int getID(){
        return num_id;
    }

    public void setStudent(String fname, String lname, int num_id){
        this.first_name = fname;
        this.last_name = lname;
        this.num_id = num_id;
    }
}