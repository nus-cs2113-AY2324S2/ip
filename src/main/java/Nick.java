import javax.sound.midi.SysexMessage;
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
                    System.out.println("\t" + Integer.toString(i + 1) + "." + userTasks[i]);
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

            String taskType = command.split(" ")[0];
            int taskDescriptionIndex = taskType.length() + 1;
            int taskDescriptionEndIndex = command.indexOf("/") - 1;

            if (taskType.equals("todo")) {
                String taskName = command.substring(taskDescriptionIndex);
                Todo task = new Todo(taskName);
                userTasks[taskCount] = task;
            } else if (taskType.equals("deadline")) {
                int deadlineIndex = command.indexOf("/by") + 4;
                String taskName = command.substring(taskDescriptionIndex, taskDescriptionEndIndex);
                String deadline = command.substring(deadlineIndex);
                Deadline task = new Deadline(taskName, deadline);
                userTasks[taskCount] = task;
            } else if (taskType.equals("event")) {
                int fromIndex = command.indexOf("/from");
                int toIndex = command.indexOf("/to");
                String taskName = command.substring(taskDescriptionIndex, taskDescriptionEndIndex);
                String from = command.substring(fromIndex + 6, toIndex - 1);
                String to = command.substring(toIndex + 4);
                Event task = new Event(taskName, from, to);
                userTasks[taskCount] = task;
            }
            System.out.println("____________________________________________________________");
            System.out.println("\t" + "Got it. I've added this task:");
            System.out.println("\t" + userTasks[taskCount]);
            System.out.println("\t" + "Now you have " + (taskCount + 1) + " tasks in the list.");
            System.out.println("____________________________________________________________");
            taskCount++;
        }
        System.out.println("____________________________________________________________");
        System.out.println("\t" + "Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
