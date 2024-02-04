import java.util.Scanner;

public class Jeff {
    private static void printIndented(String s) {
        System.out.println("     " + s);
    }

    private static void printDivider() {
        String divider = "    ____________________________________________________________";
        System.out.println(divider);
    }

    public static void main(String[] args) {
        Task[] tasks = new Task[100];
        int totalTasks = 0;
        Scanner in = new Scanner(System.in);

        printDivider();
        printIndented("Hello! I'm Jeff");
        printIndented("What can I do for you?");
        printDivider();

        while (true) {
            String line = in.nextLine();
            printDivider();
            try {
                if (line.equals("list")) {
                    printIndented("Here are the tasks in your list:");
                    for (int i = 0; i < totalTasks; i++) {
                        printIndented((i + 1) + "." + tasks[i]);
                    }
                } else if (line.startsWith("todo ") || line.startsWith("deadline ") || line.startsWith("event ")) {
                    if (line.startsWith("todo ")) {
                        String description = line.substring(5);
                        tasks[totalTasks] = new Todo(description);
                    } else if (line.startsWith("deadline ")) {
                        int byIndex = line.indexOf("/by ");
                        String description = line.substring(9, byIndex);
                        String by = line.substring(byIndex + 4);
                        tasks[totalTasks] = new Deadline(description, by);
                    } else {
                        int fromIndex = line.indexOf("/from ");
                        int toIndex = line.indexOf("/to ");
                        String description = line.substring(6, fromIndex);
                        String from = line.substring(fromIndex + 6, toIndex);
                        String to = line.substring(toIndex + 4);
                        tasks[totalTasks] = new Event(description, from, to);
                    }
                    printIndented("Got it. I've added this task:");
                    printIndented("  " + tasks[totalTasks]);
                    totalTasks++;
                    printIndented("Now you have " + totalTasks + " tasks in the list.");
                } else if (line.startsWith("mark ")) {
                    int currentIndex = Integer.parseInt(line.substring(5)) - 1;
                    Task currentTask = tasks[currentIndex];
                    currentTask.mark();
                    printIndented("Nice! I've marked this task as done:");
                    printIndented("  " + currentTask);
                } else if (line.startsWith("unmark ")) {
                    int currentIndex = Integer.parseInt(line.substring(7)) - 1;
                    Task currentTask = tasks[currentIndex];
                    currentTask.unmark();
                    printIndented("OK, I've marked this task as not done yet:");
                    printIndented("  " + currentTask);
                } else if (line.equals("bye")) {
                    printIndented("Bye. Hope to see you again soon!");
                    printDivider();
                    return;
                } else {
                    printIndented("Invalid input.");
                }
            } catch (Exception e) {
                printIndented("Invalid input.");
            }
            printDivider();
        }
    }
}
