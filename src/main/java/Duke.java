import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
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
        String[] userCommands = new String[100];
        String command;
        int count = 0;

        while (true) {
            command = in.nextLine();
            if (command.equals("bye")) {
                break;
            }
            if (command.equals("list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < count; i++) {
                    System.out.println("\t" + Integer.toString(i + 1) + ". " + userCommands[i]);
                }
                System.out.println("____________________________________________________________");
                continue;
            }
            userCommands[count] = command;
            count++;
            System.out.println("____________________________________________________________");
            System.out.println("\t" + "added: " + command);
            System.out.println("____________________________________________________________");
        }
        System.out.println("____________________________________________________________");
        System.out.println("\t" + "Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
