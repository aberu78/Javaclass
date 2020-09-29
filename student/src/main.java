import java.util.*;
import java.io.File;
//import java.util.Scanner; //used to read input
//import java.util.ArrayList;
//import java.util.Collections;

public class main {

    static boolean isAlpha(String name) {
        //System.out.println("Inside isAlpha");
        if (name == null) {
            System.out.println("Invalid name,  try again.");
            return false;
        }
        for (int i = 0; i < name.length(); i++) {
            char alpha = name.charAt(i);
            if (!(alpha >= 'A' && alpha <= 'Z') && !(alpha >= 'a' && alpha <= 'z')) {
                System.out.println("Invalid name,  try again.");
                return false;
            }
        }
        return true;
    }//end of isAlpha

    static boolean isNumber(String num) {
        //System.out.println("Inside isNumber");
        try {
            Integer numID = Integer.parseInt(num);
            return true;
        } catch (Exception e) {
            System.out.println("Invalid number, try again.");
            return false;
        }
    }//end of isNumber


    public static void main(String[] args) {
        boolean userCond = true; //until q is selected
        Scanner myStudent = new Scanner(System.in);
        File myObj = new File("Student.txt");

        while(userCond) {
            System.out.println("\nPlease enter \"a\" to ADD a student");
            System.out.println("Please enter \"d\" to remove a student");
            System.out.println("Please enter \"p\" to print student list");
            System.out.println("Please enter \"g\" to print student list, grades,or grades");
            System.out.println("Please enter \"q\" to exit\n");
            System.out.println(">>>>");

            String userInput = myStudent.nextLine(); //read options from user

            switch (userInput) {
                case "a" -> {
                    System.out.println("Inside input a");
                    boolean isValid = true;
                    while (isValid) {
                        System.out.println("Enter First Name");
                        String firstName = myStudent.nextLine();

                        System.out.println("Enter Last Name");
                        String lastName = myStudent.nextLine();

                        System.out.println("Enter student id");
                        String studentID = myStudent.nextLine();

                        //check to see if valid input is entered, error message is added within methods
                        if (isAlpha(firstName) && isAlpha(lastName) && isNumber(studentID)) {
                            Student student = new Student(Integer.parseInt(studentID), firstName, lastName);
                            //System.out.println(student.getID() + student.getFirst() + student.getLast());
                            student.Print_Student();
                            isValid = false;
                        }//end of input name user validation.
                    }//end of while(isValid)
                    break;
                }
                case "d" -> System.out.println("Inside input d");
                case "p" -> System.out.println("Inside input p");
                case "g" -> System.out.println("Inside input g");
                case "q" -> {
                    System.out.println("Inside input q");
                    userCond = false;
                }
                default -> System.out.println("Invalid option entered, try again");
            }//end of switch
        }//end of while(userInput)
        System.out.println("good bye");
    }
}
