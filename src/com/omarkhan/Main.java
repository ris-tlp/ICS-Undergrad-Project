package com.omarkhan;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.Random;


public class Main {

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

            bank[i] = new question(question, option1, option2, option3, option4, answer);

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

        String name;
        String password;

        System.out.println("Please enter your name and your password.");

        System.out.println("Name: ");
        name = scanner.nextLine();

        System.out.println("Password: ");
        password = scanner.nextLine();

        boolean isValid = verifyLogin(name, password, stud);

        while (!isValid) {
            System.out.println("Incorrect login, please try again.");

            System.out.println("Name: ");
            name = scanner.nextLine();

            System.out.println("Password: ");
            password = scanner.nextLine();

            isValid = verifyLogin(name, password, stud);
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
                //call profile function
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

        for (int i = 0; i < chosenQuestions.length; i++)
        {
            //selecting random number to refer in question bank
            Random rand = new Random();
            int n = rand.nextInt(bank.length) + 1;

            //checking if random number generated has already been generated before
            for (int k = 0; k < chosenAlready.length; k++)
            {
                if (chosenAlready[k] == n) {
                    flag = true;
                }
            }

            if (flag) { }

            else
            {

                try
                {

                    String question = bank[n].getQuestion();
                    String option1 = bank[n].getOption1();
                    String option2 = bank[n].getOption2();
                    String option3 = bank[n].getOption3();
                    String option4 = bank[n].getOption4();
                    String answer = bank[n].getAnswer();

                    chosenQuestions[i] = new question(question, option1, option2, option3, option4, answer);
                    chosenAlready[i] = n;


                } catch (NullPointerException e) { }
            }

        }

        test(chosenQuestions,answersInput);




    }


    public static void test(question[] chosenQuestions, String[] answersInput)
    {


        for (int i = 0; i < chosenQuestions.length; i++)
        {
           try
           {
               System.out.println(i + " " + chosenQuestions[i].getQuestion());
               System.out.println("A " + chosenQuestions[i].getOption1());
               System.out.println("B " + chosenQuestions[i].getOption2());
               System.out.println("C " +chosenQuestions[i].getOption3());
               System.out.println("D " +chosenQuestions[i].getOption4());

               answersInput[i] = scanner.nextLine();

           } catch (NullPointerException e) {}

        }

       int marks = checkAnswers(chosenQuestions,answersInput);
        System.out.println(" ******************************YOUR MARKS ARE: " + marks);
    }

    public static int checkAnswers(question[] chosenQuestions, String[] answersInput)
    {
        int marks = 0;

        for (int i = 0; i < answersInput.length; i++)
        {
            try {
                if (chosenQuestions[i].getAnswer() == answersInput[i].toUpperCase())
                    {
                        marks++;
                    }

            } catch (NullPointerException e) {}
        }

        return marks;
    }

}


