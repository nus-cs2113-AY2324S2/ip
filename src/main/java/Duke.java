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
        Task[] userCommands = new Task[100];
        String command;
        int count = 0;

        while (true) {
            command = in.nextLine();
            if (command.equals("bye")) {
                break;
            }
            if (command.equals("list")) {
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < count; i++) {
                    System.out.println("\t" + Integer.toString(i + 1) + ".[" + userCommands[i].getStatusIcon() + "] " + userCommands[i].description);
                }
                System.out.println("____________________________________________________________");
                continue;
            }
            if (command.split(" ")[0].contains("mark")) {
                String[] markCommand = command.split(" ");
                if (markCommand[0].equals("mark")) {
                    userCommands[Integer.parseInt(markCommand[1]) - 1].markAsDone();
                    System.out.println("____________________________________________________________");
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("\t" + "[" + userCommands[Integer.parseInt(markCommand[1]) - 1].getStatusIcon() + "] " + userCommands[Integer.parseInt(markCommand[1]) - 1].description);
                    System.out.println("____________________________________________________________");
                }
                else if (markCommand[0].equals("unmark")){
                    userCommands[Integer.parseInt(markCommand[1]) - 1].markAsUndone();
                    System.out.println("____________________________________________________________");
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("\t" + "[" + userCommands[Integer.parseInt(markCommand[1]) - 1].getStatusIcon() + "] " + userCommands[Integer.parseInt(markCommand[1]) - 1].description);
                    System.out.println("____________________________________________________________");
                }
                continue;
            }
            Task t = new Task(command);
            userCommands[count] = t;
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
