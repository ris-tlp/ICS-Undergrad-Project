package com.omarkhan;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;


public class Main {

    public static Scanner s = new Scanner(System.in);

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

        student[] stud = new student[5];
        question[] bank = new question[95];

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
            name = s.nextLine();

            System.out.println("Password: ");
            password = s.nextLine();

            boolean isValid = verifyLogin(name,password,stud);

            while (!isValid)
            {
                System.out.println("Incorrect login, please try again.");

                System.out.println("Name: ");
                name = s.nextLine();

                System.out.println("Password: ");
                password = s.nextLine();

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

        }
}
