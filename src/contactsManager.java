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
                break;
            case 3:
                break;
            case 4:
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


    public static void search(ArrayList<String> contacts) {
        Scanner sc = new Scanner(System.in);
        Path path = Paths.get(dir);
        Path filePath = Paths.get(dir, fileName);
        try {
            List<String> names = Files.readAllLines(filePath);
            List<String> tempContact = new ArrayList<>();
            System.out.println("Enter the name you are looking for:");
            String contactName = sc.nextLine();
            for (String name : names){
                if (name.equals(contactName.getName())) {
                    tempContact.add(name);
                    System.out.println(tempContact);
                }
            }

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
        List<String> contacts = new ArrayList<>();
        contacts.add("Fer | 2104354444");
        contacts.add("Sophie | 2108388883");
        contacts.add("Vivian | 2109356789");

        try {
            Files.write(filePath, contacts);
        } catch (IOException e) {
            e.printStackTrace();
        }

        mainMenu();



    }


}
