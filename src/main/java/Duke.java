import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        try {
            String name = new String(Files.readAllBytes(Paths.get("name.txt")));
            System.out.print(name);
        }
        catch (IOException ex) {
            System.out.println(ex);
        }

        System.out.println("____________________________________________________________");
        System.out.println("Welcome to the Ultimate Nick Bot!");
        System.out.println("What can I do for you?\n");

        Scanner in = new Scanner(System.in);
        String command;

        while (true) {
            command = in.nextLine();
            if (command.equals("bye")) {
                break;
            }
            System.out.println("____________________________________________________________");
            System.out.println("\t" + command);
            System.out.println("____________________________________________________________");
        }

        System.out.println("____________________________________________________________");
        System.out.println("\t" + "Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
