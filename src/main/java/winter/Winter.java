package winter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;


public class Winter {
    public static void main(String[] args) {
        String logo = "  __      __.__        __                \n" +
                "/  \\    /  \\__| _____/  |_  ___________ \n" +
                "\\   \\/\\/   /  |/    \\   __\\/ __ \\_  __ \\\n" +
                " \\        /|  |   |  \\  | \\  ___/|  | \\/\n" +
                "  \\__/\\  / |__|___|  /__|  \\___  >__|   \n" +
                "       \\/          \\/          \\/    ";
        System.out.println("Hello from\n" + logo);
        sayHi();
        //echo();
        try {
            File f = new File("data/winter.txt");
            Scanner s = new Scanner(f);
            try {
                Manager.readFile(s);
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            } /*catch (ArrayIndexOutOfBoundsException e2) {
                System.out.println("Something went wrong: " + e2.getMessage());
                Files.delete(Paths.get("data/winter.txt"));
            }*/
            try {
                Manager.acceptInput();
            } catch(IOException e2) {
                System.out.println("Cannot add task");
            }
        } catch (FileNotFoundException e) {
            try {
                Manager.acceptInput();
            } catch(IOException e2) {
                System.out.println("Cannot add task");
            }
        }
    }
    // Method for greeting message
    private static void sayHi() {
        String line = "-----------------------------------\n";
        String greet = "Hello! I'm Winter!\nWhat can I do for you?";
        System.out.print(line);
        System.out.println(greet);
        System.out.print(line);
    }

}