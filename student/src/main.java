import java.util.*;
import java.io.File;
//import java.util.Scanner; //used to read input
//import java.util.ArrayList;
//import java.util.Collections;

public class main {

    static boolean isAlpha(String name) {
        //System.out.println("Inside isAlpha");
        if (name == null) {
            //System.out.println("Invalid name,  try again.");
            return false;
        }
        for (int i = 0; i < name.length(); i++) {
            char alpha = name.charAt(i);
            if (!(alpha >= 'A' && alpha <= 'Z') && !(alpha >= 'a' && alpha <= 'z')) {
                //System.out.println("Invalid name,  try again.");
                return false;
            }
        }
        return true;
    }//end of isAlpha

    static boolean isNumExist(String number, HashMap<Integer,Object> myObj){
        int numInt = Integer.parseInt(number);

        for(int i : myObj.keySet()){
            if(numInt == i)
                return false;
        }
        return true;


    }

    static boolean isNumber(String num) {
        //System.out.println("Inside isNumber");
        try {
            Integer numID = Integer.parseInt(num);
            return true;
        } catch (Exception e) {
            //System.out.println("Invalid number, try again.");
            return false;
        }
    }//end of isNumber
    /*
    public static void addStudent(String studentID,String firstName, String lastName){
        //create student object;
        Student student = new Student(Integer.parseInt(studentID), firstName, lastName);

        //create an instance of Hashmap
        HashMap<Integer, Object> myStudent= new HashMap<Integer,Object>();

        myStudent.put(Integer.parseInt(studentID), student);
        student.printStudent();


    }*/

    public static void main(String[] args) {
        boolean userCond = true; //until q is selected
        Scanner inputIn = new Scanner(System.in);
        //File myObj = new File("Student.txt");
        HashMap<Integer, Object> myStudent = new HashMap<>();

        while(userCond) {
            System.out.println("\nPlease enter \"a\" to ADD a student");
            System.out.println("Please enter \"d\" to remove a student");
            System.out.println("Please enter \"p\" to print student list");
            System.out.println("Please enter \"g\" to print student list, grades,or grades");
            System.out.println("Please enter \"q\" to exit\n");
            System.out.println(">>>>");

            String userInput = inputIn.nextLine(); //read options from user

            switch (userInput) {
                case "a" -> {
                    System.out.println("Inside input a");
                    boolean isValid = true;
                    while (isValid) {
                        boolean isNameValid = true;
                        boolean isNumValid = true;
                        String firstName = "";
                        String lastName = "";
                        String studentID = "";

                        //check to see if valid input is entered, error message is added within methods
                        while(isNameValid) {

                            System.out.println("Enter First Name");
                            firstName = inputIn.nextLine();

                            System.out.println("Enter Last Name");
                            lastName = inputIn.nextLine();

                            if (isAlpha(firstName) && isAlpha(lastName)) {
                                isNameValid = false;

                            } else
                                System.out.println("Invalid name,  try again.");
                        }//end of isNameValid
                        while(isNumValid){
                            System.out.println("Enter student id");
                            studentID = inputIn.nextLine();

                             if(isNumber(studentID)){
                                if (isNumExist(studentID, myStudent)) {
                                    isNumValid = false;
                                    isValid = false;
                                }
                                else
                                    System.out.println("ID you entered exist, try again.");
                            }//end of if
                            else
                                 System.out.println("Invalid number, try again.");
                        }//end of isNumValid

                        //create student object;
                        Student student = new Student(Integer.parseInt(studentID), firstName, lastName);

                        myStudent.put(Integer.parseInt(studentID), student);

                            System.out.println("Student added");
                            student.printStudent();
                    }//end of while(isValid)
                    break;
                }
                case "d" -> System.out.println("Inside input d");
                case "p" -> {
                    System.out.println("Inside input p");

                    for(int i : myStudent.keySet())
                        System.out.println("Student ID :" +  i + "  Name: " +myStudent.get(i));
                    break;
                }
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
