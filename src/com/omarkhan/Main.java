package com.omarkhan;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;



public class Main {

    public static Scanner s = new Scanner(System.in);

    public static void main(String[] args)
    {

//        String filepath = "C:\\workspace\\ICS-Undergrad-Project\\userbase.txt";
//        Scanner scan = new Scanner(new File("userbase.txt"));

//        File file = new File("C:\\workspace\\ICS-Undergrad-Project\\userbase.txt");
//        Scanner scan = new Scanner(file);

        Scanner scan = null;
        try {
            scan = new Scanner (new File("userbase.txt"));


        } catch(FileNotFoundException e) {
        }

        student[] stud = new student[5];

        for(int i = 0; i < stud.length; i++)
        {
            int ID = scan.nextInt();
            String name = scan.next();
            String password = scan.next();
            int max = scan.nextInt();
            int attempts = scan.nextInt();


            stud[i] = new student(ID, name, password, max, attempts);
//            System.out.println(scan.nextLine());

        }

        System.out.println("Welcome to the online test system.");
        login(stud);

        }

        private static void login(student[] stewd)
        {

            String name;
            String password;

            System.out.println("Please enter your name and your password.");

            System.out.println("Name: ");
            name = s.nextLine();

            System.out.println("Password: ");
            password = s.nextLine();

            boolean isValid = verifyLogin(name,password,stewd);
            while (isValid == false)
            {
                System.out.println("Incorrect login, please try again.");

                System.out.println("Name: ");
                name = s.nextLine();

                System.out.println("Password: ");
                password = s.nextLine();

                verifyLogin(name,password,stewd);

            }

        }

        private static boolean verifyLogin(String username, String password, student[] s)
        {
            for (int i = 0; i < s.length; i++)
            {
                if (username.equals(s[i].getName()) && password.equals(s[i].getPassword()))
                {
                    System.out.println("Successful login!");
                    //invoke other shit now
                    return true;
                }
            }
            return false;
        }
}
