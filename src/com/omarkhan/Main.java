package com.omarkhan;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.Random;


public class Main {

    public static student[] stud = new student[5];
    public static question[] bank = new question[95];

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args)
    {

        Scanner scan = null;
        try
        {
            scan = new Scanner (new File("userbase.txt"));
        } catch(FileNotFoundException e) {}

        Scanner qs = null;
        try
        {
            qs = new Scanner (new File("Testbank.txt")).useDelimiter((Pattern.compile(("^\\s*$"), Pattern.MULTILINE)));
        } catch (FileNotFoundException e) {}



        //reading questions from question bank
        for (int i = 0; i < bank.length; i++)
        {
                String question = qs.next();
                String option1 = qs.next();
                String option2 = qs.next();
                String option3 = qs.next();
                String option4 = qs.next();
                String answer = qs.next();

                bank[i] = new question(question,option1,option2,option3,option4,answer);

        }
        //reading students from userbase
        for (int i = 0; i < stud.length; i++)
         {
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

        private static void login(student[] stud)
        {

            String name;
            String password;

            System.out.println("Please enter your name and your password.");

            System.out.println("Name: ");
            name = scanner.nextLine();

            System.out.println("Password: ");
            password = scanner.nextLine();

            boolean isValid = verifyLogin(name,password,stud);

            while (!isValid)
            {
                System.out.println("Incorrect login, please try again.");

                System.out.println("Name: ");
                name = scanner.nextLine();

                System.out.println("Password: ");
                password = scanner.nextLine();

                isValid = verifyLogin(name,password,stud);
            }

        }

        private static boolean verifyLogin(String username, String password, student[] s)
        {
            for (int i = 0; i < s.length; i++)
            {
                if (username.equals(s[i].getName()) && password.equals(s[i].getPassword()))
                {
                    System.out.println("Successful login!");
                    return true;
                }
            }
            return false;
        }

        private static void menu()
        {
            System.out.println("Welcome to the Online Test System...");

            System.out.println("Please enter the number of choice you want to select from the following menu: ");
            System.out.println("1. Take a test");
            System.out.println("2. View leaderboard");
            System.out.println("3. View my profile");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            boolean isValid;

            switch(choice)
            {
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
        private static boolean menuValidity(boolean flag)
        {
            while (!flag) {
                menu();
                return false;
            }
            return true;
        }

        //function invoked from choosing menu option 4
        private static void menuExit() { System.exit(0); }

        private static void menuTest()
        {
            question[] chosenQuestions = new question[10];

            for (int i = 0; i < chosenQuestions.length; i++)
            {
                //selecting random number to refer in question bank
                Random rand = new Random();
                int n = rand.nextInt(50) + 1;

                //array to store the question number so same question doesn't appear twice
                int[] chosenAlready = new int[10];


                //NOPE SCREW THAT THAT'S SO WRONG HOT DAMN DUDE HOW YOU SO BAD
                //DAMN NP YO WE GON GET UP TOMORROW AND DO THIS AGAIN
                for (int k = 0; k < 10; k++)
                {
                    //checking if the question number has already been selected
                    if(!(chosenAlready[k] == n))
                    {
                        //first storing question with options and answer in array
                        chosenQuestions[i] = bank[n];
                        //storing randomly selected question number in array to prevent same question appearing twice
                        chosenAlready[i] = n;
                    }
                }

            }
        }
}
