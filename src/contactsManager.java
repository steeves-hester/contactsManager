import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class contactsManager {

    final static String dir = "data";
    final static String fileName = "contacts.txt";
    public static List<String> contacts;


    public static void mainMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("*----------Contacts Manager----------*");
        System.out.println("1. Show All Contacts");
        System.out.println("2. Search By Name");
        System.out.println("3. Add Contact");
        System.out.println("4. Delete Contact");
        System.out.println("5. Exit");
        System.out.println("(Enter an option 1, 2, 3, 4, 5)");
        int input = sc.nextInt();
        sc.nextLine();

        switch (input) {

            case 1:
                showAll();
                System.out.println("Would you like to return to the main menu? Y/N");
                String ans = sc.nextLine();
                if(ans.equalsIgnoreCase("yes") || ans.equalsIgnoreCase("y")) {
                    mainMenu();
                }
                    break;
            case 2:
                search();
                System.out.println("Would you like to return to the main menu? Y/N");
                String ans2 = sc.nextLine();
                if(ans2.equalsIgnoreCase("yes") || ans2.equalsIgnoreCase("y")) {
                    mainMenu();
                }
                break;
            case 3:
                add();
                System.out.println("Would you like to return to the main menu? Y/N");
                String ans3 = sc.nextLine();
                if(ans3.equalsIgnoreCase("yes") || ans3.equalsIgnoreCase("y")) {
                    mainMenu();
                }
                break;
            case 4:
                delete();
                System.out.println("Would you like to return to the main menu? Y/N");
                String ans4 = sc.nextLine();
                if(ans4.equalsIgnoreCase("yes") || ans4.equalsIgnoreCase("y")) {
                    mainMenu();
                }
                break;
            case 5:
                break;
        }


    }

    public static void showAll() {
        Path path = Paths.get(dir);
        Path filePath = Paths.get(dir, fileName);
        try {
            List<String> names = Files.readAllLines(filePath);
            System.out.println("*----------All Contacts----------*");
            for (String name : names){
                System.out.println(name.toUpperCase());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void search() {
        Scanner sc = new Scanner(System.in);
        Path path = Paths.get(dir);
        Path filePath = Paths.get(dir, fileName);
        try {
            List<String> names = Files.readAllLines(filePath);
            List<String> tempContact = new ArrayList<>();
            System.out.println("Enter the name you are looking for:");
            //we're here, trying to figure out how to search. String.split at the | ??
            String contactName = sc.nextLine();
            String phoneBook = contactName.toLowerCase();
            for (String name : names){

                if (name.contains(phoneBook)) {
                    tempContact.add(name);
                    System.out.println(tempContact);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void add() {
        Scanner sc = new Scanner(System.in);
        Path path = Paths.get(dir);
        Path filePath = Paths.get(dir, fileName);

            System.out.println("Enter name of the new contact:");
            String contactName = sc.nextLine();
            System.out.println("Enter phone number of the new contact:");
            String contactNumber = sc.nextLine();
            contacts.add(contactName + " | " + contactNumber);
        try {
            Files.write(filePath, contacts);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public static void delete() {
        Scanner sc = new Scanner(System.in);
        System.out.println("What is the name of the person you want to delete?");
        String search = sc.nextLine();
        Path path = Paths.get(dir);
        Path filePath = Paths.get(dir, fileName);
        int place = -1;
        boolean found = false;
        String phoneBook = search.toLowerCase();
        for (String contact : contacts) {
            if (search.equals(phoneBook)) {
                found = true;
                place = contacts.indexOf(contact);
            }
        }

        if (found) {
            contacts.remove(place);
        } else {
            System.out.println("Could not find that contact.");
        }
        try {
            Files.write(filePath, contacts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Path path = Paths.get(dir);
        //why is this one inside the psvm and not outside with the others
        Path filePath = Paths.get(dir, fileName);


        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
                try {
                    Files.createFile(filePath);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        contacts = new ArrayList<>();
        contacts.add("fer | 2104354444");
        contacts.add("sophie | 2108388883");
        contacts.add("vivian | 2109356789");

        try {
            Files.write(filePath, contacts);
        } catch (IOException e) {
            e.printStackTrace();
        }

        mainMenu();



    }


}
