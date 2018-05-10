package com.omarkhan;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.Random;


public class Main {

    public static String publicName;

    public static student[] stud = new student[5];
    public static question[] bank = new question[90];

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Scanner scan = null;
        try {
            scan = new Scanner(new File("userbase.txt"));
        } catch (FileNotFoundException e) {
        }

        Scanner qs = null;
        try {
            qs = new Scanner(new File("Testbank.txt")).useDelimiter((Pattern.compile(("^\\s*$"), Pattern.MULTILINE)));
        } catch (FileNotFoundException e) {
        }


        //reading questions from question bank
        for (int i = 0; i < bank.length; i++) {
            String question = qs.next();
            String option1 = qs.next();
            String option2 = qs.next();
            String option3 = qs.next();
            String option4 = qs.next();
            String answer = qs.next();
            String newAnswer = answer.replaceAll("\\s+", "");

            bank[i] = new question(question, option1, option2, option3, option4, newAnswer);

        }
        //reading students from userbase
        for (int i = 0; i < stud.length; i++) {
            int ID = scan.nextInt();
            String name = scan.next();
            String password = scan.next();
            int max = scan.nextInt();
            int attempts = scan.nextInt();

            stud[i] = new student(ID, name, password, max, attempts);
        }

        System.out.println("Welcome to the online test system.");
        login(stud);

        menu();


    }

    private static void login(student[] stud) {


        String password;

        System.out.println("Please enter your name and your password.");

        System.out.println("Name: ");
        publicName = scanner.nextLine();

        System.out.println("Password: ");
        password = scanner.nextLine();

        boolean isValid = verifyLogin(publicName, password, stud);

        while (!isValid) {
            System.out.println("Incorrect login, please try again.");

            System.out.println("Name: ");
            publicName = scanner.nextLine();

            System.out.println("Password: ");
            password = scanner.nextLine();

            isValid = verifyLogin(publicName, password, stud);
        }

    }

    private static boolean verifyLogin(String username, String password, student[] s) {
        for (int i = 0; i < s.length; i++) {
            if (username.equals(s[i].getName()) && password.equals(s[i].getPassword())) {
                System.out.println("Successful login!");
                return true;
            }
        }
        return false;
    }

    private static void menu() {
        System.out.println("Welcome to the Online Test System...");

        System.out.println("Please enter the number of choice you want to select from the following menu: ");
        System.out.println("1. Take a test");
        System.out.println("2. View leaderboard");
        System.out.println("3. View my profile");
        System.out.println("4. Exit");

        int choice = scanner.nextInt();
        boolean isValid;

        switch (choice) {
            case 1:
                System.out.println("You have chosen to take a test.");
                menuTest();
                break;

            case 2:
                System.out.println("You have chosen to view the leaderboard.");
                //call leaderboard function
                break;

            case 3:
                System.out.println("You have chosen to view your profile.");
                viewProfile(publicName);
                break;

            case 4:
                System.out.println("You have chosen to exit.");
                menuExit();
                break;

            default:
                System.out.println("You have not entered a valid choice, please try again.");
                isValid = false;
                isValid = menuValidity(isValid);

        }


    }

    //function invoked to check if the input from user is valid
    private static boolean menuValidity(boolean flag) {
        while (!flag) {
            menu();
            return false;
        }
        return true;
    }

    //function invoked from choosing menu option 4
    private static void menuExit() {
        System.exit(0);
    }


    private static void menuTest() {
        question[] chosenQuestions = new question[10];
        boolean flag = false;

        //array to store the question number so same question doesn't appear twice
        int[] chosenAlready = new int[10];
        String[] answersInput = new String[10];

        for (int i = 0; i < chosenQuestions.length; i++) {
            //selecting random number to refer in question bank
            Random rand = new Random();
            int n = rand.nextInt(bank.length) + 1;

            //checking if random number generated has already been generated before
            for (int k = 0; k < chosenAlready.length; k++) {
                if (chosenAlready[k] == n) {
                    flag = true;
                }
            }

            if (flag) {
            } else {

                try {

                    String question = bank[n].getQuestion();
                    String option1 = bank[n].getOption1();
                    String option2 = bank[n].getOption2();
                    String option3 = bank[n].getOption3();
                    String option4 = bank[n].getOption4();
                    String answer = bank[n].getAnswer();

                    chosenQuestions[i] = new question(question, option1, option2, option3, option4, answer);
                    chosenAlready[i] = n;

                    System.out.println("******************************" + answer);


                } catch (NullPointerException e) {
                }
            }

        }

        test(chosenQuestions, answersInput);


    }


    public static void test(question[] chosenQuestions, String[] answersInput) {


        for (int i = 0; i < chosenQuestions.length; i++) {
            try {

                System.out.println(chosenQuestions[i].getQuestion());
                System.out.println(chosenQuestions[i].getOption1());
                System.out.println(chosenQuestions[i].getOption2());
                System.out.println(chosenQuestions[i].getOption3());
                System.out.println(chosenQuestions[i].getOption4());

                answersInput[i] = scanner.next();

            } catch (NullPointerException e) {
            }

        }

        int marks = checkAnswers(chosenQuestions, answersInput);
        System.out.println(" ******************************YOUR MARKS ARE: " + marks);
    }

    public static int checkAnswers(question[] chosenQuestions, String[] answersInput) {
        int marks = 0;

        for (int i = 0; i < answersInput.length; i++) {

            try {
                if (chosenQuestions[i].getAnswer().equals(answersInput[i].toUpperCase())) {
                    marks++;
                }

            } catch (NullPointerException e) {
            }
        }

        return marks;
    }


    public static void viewProfile(String name)
        {
            boolean flag = false;
            int choice = 0;

            for (int i = 0; i < Integer.MAX_VALUE; i++)
            {
                if (stud[i].getName().equals(publicName)) {
                    System.out.println("Your ID is: " + stud[i].getID());
                    System.out.println("Your name is: " + stud[i].getName());
                    System.out.println("Your highest score is: " + stud[i].getHighestScore());
                    System.out.println("You have taken this test " + stud[i].getAttempts() + " times.");

                    flag = true;
                }

                if (flag) {
                    break;
                }

            }

            System.out.println("\n\nWhat would you like to do next?");
            System.out.println("Enter 1 to go back to the main menu.");
            System.out.println("Enter 2 to exit the program.");


//           try { choice = scanner.nextInt(); } catch (InputMismatchException e) {}
            boolean bError = true;




//                try {
                while (bError)
                {
                    if (scanner.hasNextInt())
                         choice = scanner.nextInt();
                    else
                        {
                         scanner.next();
                            System.out.println("Invalid input, please try again.");
                         continue;

                        }

                    switch (choice) {
                        case 1:
                            menu();
                            break;

                        case 2:
                            menuExit();
                            break;

                        default:
                            System.out.println("You have entered an incorrect input, automatically redirecting you to the main menu.\n");
                            menu();


                    }

                    bError = false;


//                } catch (Exception e) {
//                    System.out.println("Error! Redirecting you to the main menu!");

                }



        }
    }



