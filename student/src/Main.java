import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Main {

    static boolean isNumExist(String number, HashMap<Integer, Student> myObj) {
        int numInt = Integer.parseInt(number);
        return myObj.containsKey(numInt);
    }

    static boolean isGradeValid(String aGrade) {
        return Integer.parseInt(aGrade) <= 100;
    }

    public static void load(HashMap<Integer, Student> myStudent) {
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("student_list.json")) {
            Object obj = jsonParser.parse(reader);
            JSONArray studentLists = (JSONArray) obj;
            System.out.println("Inside the load ");
            System.out.println(studentLists);

            for (Object studentData : studentLists) {
                Student tmp = Student.createFromJson((JSONObject)studentData);
                if(tmp != null) {
                    myStudent.put(tmp.getID(), tmp);
                }
            }
            System.out.println("load success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void save(HashMap<Integer, Student> myStudent) {
        JSONArray studentList = new JSONArray(); //array  that will store studentDetails obj
        for (int id : myStudent.keySet()) {
            JSONObject studentGrades = new JSONObject();
            JSONObject studentDetails = new JSONObject();
            JSONObject studentData = new JSONObject();

            if (myStudent.get(id).gradeExist()) {
                HashMap<String, ArrayList<Long>> result = new HashMap<>();//<subject,grades[]>

                for (String sub : myStudent.get(id).getSubjects()) {
                    result.put(sub, myStudent.get(id).getGrade(sub));
                }
                studentGrades.putAll(result);
            }
            String fName = myStudent.get(id).getFirst();
            String lName = myStudent.get(id).getLast();
            int idNum = myStudent.get(id).getID();
            studentDetails.put("id", idNum);
            studentDetails.put("firstname", fName);
            studentDetails.put("lastname", lName);
            studentDetails.put("subject", studentGrades);
            studentData.put("studentData", studentDetails);

            studentList.add(studentData);
        }

        try (FileWriter file = new FileWriter("Student_list.json")) {
            studentList.writeJSONString(file);
            System.out.println("save success");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//end of save()

    public static void main(String[] args) {
        boolean userCond = true; //until q is selected
        Scanner inputIn = new Scanner(System.in);
        HashMap<Integer, Student> myStudent = new HashMap<>();
        load(myStudent);
        while (userCond) {
            System.out.println("\nPlease enter \"a\" to ADD a student");
            System.out.println("Please enter \"d\" to remove a student");
            System.out.println("Please enter \"p\" to print student list");
            System.out.println("Please enter \"g\" to print student list, grades,or grades");
            System.out.println("Please enter \"q\" to exit\n");
            System.out.println(">>>>");

            String userInput = inputIn.nextLine(); //read options from user

            switch (userInput) {
                case "a" -> {
                    //System.out.println("Inside input a");
                    boolean isValid = true;
                    while (isValid) {
                        boolean isNameValid = true;
                        boolean isNumValid = true;
                        String firstName = "";
                        String lastName = "";
                        String studentID = "";

                        //check to see if valid input is entered, error message is added within methods
                        while (isNameValid) {
                            System.out.println("Enter First Name");
                            firstName = inputIn.nextLine();
                            System.out.println("Enter Last Name");
                            lastName = inputIn.nextLine();

                            if (StringUtils.isAlpha(firstName) && StringUtils.isAlpha(lastName))
                                isNameValid = false;

                            else
                                System.out.println("Invalid name,  try again.\n");


                        }//end of isNameValid

                        while (isNumValid) {
                            System.out.println("Enter student id");
                            studentID = inputIn.nextLine();

                           if (StringUtils.isNumeric(studentID) && !isNumExist(studentID, myStudent)) {
                                isNumValid = false;
                                isValid = false;
                            } else
                                System.out.println("Invalid number, try again.\n");
                        }//end of isNumValid

                        Student student = new Student(Integer.parseInt(studentID), firstName, lastName);
                        myStudent.put(Integer.parseInt(studentID), student);

                        System.out.println("Student added");
                    }//end of while(isValid)
                }
                case "d" -> {
                    System.out.println("Please enter student ID");
                    String id = inputIn.nextLine();

                    if (StringUtils.isNumeric(id) && isNumExist(id, myStudent))
                        myStudent.remove(Integer.parseInt(id));
                    else
                        System.out.println("Invalid student id, try again");

                }
                case "p" -> {
                    System.out.println("Please enter 1 to print all student Names");
                    System.out.println("Please enter 2 to all grades by a student ID");
                    System.out.println("Please enter 3 to print the average grade by a student ID");
                    String num = inputIn.nextLine();
                    switch (num) {
                        case "1" -> {
                            System.out.println("Inside option 1");
                            for (int i : myStudent.keySet()) {
                                myStudent.get(i).printStudent();
                            }//end of for
                        }//end of case 1
                        case "2" -> {
                            System.out.println("Please enter student ID");
                            String id = inputIn.nextLine();
                            if (isNumExist(id, myStudent))
                                myStudent.get(Integer.parseInt(id)).printGrade();
                            else
                                System.out.println("Student ID you entered does not have grades recorded.");
                        }
                        case "3" -> {
                            System.out.println("Please enter student ID");
                            String id = inputIn.nextLine();
                            if (isNumExist(id, myStudent))
                                myStudent.get(Integer.parseInt(id)).printAvg();
                            else
                                System.out.println("Student ID you entered does not have grades recorded.");
                        }
                        default -> System.out.println("Invalid option entered, try again");
                    }//end of switch
                }
                case "g" -> { //adding student grades
                    boolean isValid = true;
                    while (isValid) {
                        System.out.println("Please enter student ID");
                        String id = inputIn.nextLine();

                        if (StringUtils.isNumeric(id) && isNumExist(id, myStudent)) {
                            System.out.println("Enter a Subject : ");
                            String subject = inputIn.nextLine();
                            System.out.println("Enter a grade : ");
                            String grade = inputIn.nextLine();
                            if (StringUtils.isNumeric(grade) && isGradeValid(grade)) {
                                myStudent.get(Integer.parseInt(id)). addGrade(subject.toUpperCase(), Integer.parseInt(grade));
                                isValid = false;
                                System.out.println("a grade is added ");
                            } else
                                System.out.println("Invalid grade entered, try again");
                        } else {
                            System.out.println("Student ID you entered does not have grades recorded, try again.");
                            isValid = false;
                        }
                    }//end of while isValid
                }
                case "q" -> {
                    //System.out.println("Inside input q");
                    save(myStudent);
                    userCond = false;
                }
                default -> System.out.println("Invalid option entered, try again");
            }//end of switch
        }//end of while(userInput)
        System.out.println("good bye");
    }
}
