public class Student {

    private String firstName;
    private String lastName;
    private int numID;


    public Student(int id, String fname, String lname) {
        firstName = fname;
        lastName = lname;
        numID = id;

    }
    public void PrintStudent(){
        System.out.println("inside Print_Student()");
        System.out.println("ID :" + numID + " " + "Name: " + firstName + " " + lastName);
    }

    public String getFirst(){
        return firstName;
    }
    public String getLast(){
        return lastName;
    }
    public int getID(){
        return numID;
    }

    public void setStudent(String fname, String lname, int numID){
        this.firstName = fname;
        this.lastName = lname;
        this.numID = numID;
    }
}