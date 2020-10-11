public class Person {

    private String firstName;
    private String lastName;
    private int numID;

    public Person(int numID, String firstName, String lastName) {
        this.firstName = firstName.toUpperCase();
        this.lastName = lastName.toUpperCase();
        this.numID = numID;
    }


    public String getFirst() {
        return firstName;
    }

    public String getLast() {
        return lastName;
    }

    public int getID() {
        return numID;
    }



}
