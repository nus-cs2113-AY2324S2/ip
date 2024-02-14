import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String name = "Duck";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________\n" + "Hello! I'm " + name
                + "\n" + "What can I do for you?\n" + "\n"
                + "____________________________________________________________\n");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        userInput = userInput.toLowerCase();
        Task[] tasks = new Task[100];
        while (!userInput.contains("bye")) {

            System.out.println("____________________________________________________________\n");
            int indexOfMark;
            if (userInput.contains("list")) {
                for (int i = 0; i < Task.numOfTask; i++) {
                    System.out.println((i+1) + "." + tasks[i]);
                }

            } else if (userInput.contains("mark")) {
                if (userInput.contains("unmark")) {

                    indexOfMark = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    tasks[indexOfMark].markAsUndone();

                } else {
                    indexOfMark = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    tasks[indexOfMark].markAsDone();
                }
            } else {
                if (userInput.contains("deadline")) {
                    String descriptionAndBy = userInput.split(" ", 2)[1];
                    String Description = descriptionAndBy.split("/")[0];
                    String By = descriptionAndBy.split("/")[1];
                    tasks[Task.numOfTask] = new Deadline(Description, By);
                }
                else if (userInput.contains("todo")) {
                    String Description = userInput.split(" ", 2)[1];
                    tasks[Task.numOfTask] = new ToDo(Description);
                }
                else if (userInput.contains("event")) {
                    String descriptionAndRange = userInput.split(" ", 2)[1];
                    String Description = descriptionAndRange.split("/",3)[0];
                    String from = descriptionAndRange.split("/",3)[1];
                    String to = descriptionAndRange.split("/", 3)[2];
                    tasks[Task.numOfTask] = new Event(Description, from, to);
                }
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks[Task.numOfTask - 1]);
                System.out.println("Now you have " + Task.numOfTask + " tasks in the list.");
            }

            System.out.println("____________________________________________________________\n");
            userInput = scanner.nextLine();
            userInput = userInput.toLowerCase();
        }
        System.out.println("Bye. Hope to see you again soon!\n"
                + "\n" + "____________________________________________________________");

        }
}
