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

    Scanner sc = new Scanner(System.in);
    final static String dir = "data";
    final static String fileName = "contacts.txt";

    public static void main(String[] args) {

        Path path = Paths.get(dir + "/" + fileName).normalize();
        //normalize will take the direction that you give it and normalize it into a string
        //why is this one inside the psvm and not outside with the others

        if(!Files.exists(path)){
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        List<String> contacts = new ArrayList<>();
        contacts.add("Fer");
        contacts.add("Sophie");
        contacts.add("Vivian");

        try {
            Files.write(path, contacts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
