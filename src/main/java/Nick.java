import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.Logger;

public class Nick {
    private static final Logger LOGGER = Logger.getLogger(Nick.class.getName());
    public static void main(String[] args) {

        try {
            String name = new String(Files.readAllBytes(Paths.get("name.txt")));
            System.out.print(name);
        } catch (IOException exception) {
            LOGGER.severe(exception.toString());
        }

        System.out.println("____________________________________________________________");
        System.out.println("Welcome to the Ultimate Nick Bot!");
        System.out.println("What can I do for you?\n");

        Scanner input = new Scanner(System.in);
        Task[] userTasks = new Task[100];
        String command;
        int taskCount = 0;

        while (true) {
            command = input.nextLine();
            if (command.equals("bye")) {
                break;
            }
            if (command.equals("list")) {
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println("\t" + Integer.toString(i + 1) + ".[" + userTasks[i].getStatusIcon() + "] " + userTasks[i].description);
                }
                System.out.println("____________________________________________________________");
                continue;
            }
            if (command.split(" ")[0].contains("mark")) {
                String[] markCommand = command.split(" ");
                if (markCommand[0].equals("mark")) {
                    userTasks[Integer.parseInt(markCommand[1]) - 1].markAsDone();
                    System.out.println("____________________________________________________________");
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("\t" + "[" + userTasks[Integer.parseInt(markCommand[1]) - 1].getStatusIcon() + "] " + userTasks[Integer.parseInt(markCommand[1]) - 1].description);
                    System.out.println("____________________________________________________________");
                } else if (markCommand[0].equals("unmark")){
                    userTasks[Integer.parseInt(markCommand[1]) - 1].markAsUndone();
                    System.out.println("____________________________________________________________");
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("\t" + "[" + userTasks[Integer.parseInt(markCommand[1]) - 1].getStatusIcon() + "] " + userTasks[Integer.parseInt(markCommand[1]) - 1].description);
                    System.out.println("____________________________________________________________");
                }
                continue;
            }
            Task task = new Task(command);
            userTasks[taskCount] = task;
            taskCount++;
            System.out.println("____________________________________________________________");
            System.out.println("\t" + "added: " + command);
            System.out.println("____________________________________________________________");
        }
        System.out.println("____________________________________________________________");
        System.out.println("\t" + "Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
