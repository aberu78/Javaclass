public class Person {

    private final String firstName;
    private final String lastName;
    private final int numID;

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
