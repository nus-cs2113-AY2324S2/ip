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
            if (line.equals("list")) {
                printDivider();
                printIndented("Here are the tasks in your list:");
                for (int i = 0; i < totalTasks; i++) {
                    printIndented((i + 1) + ".[" + tasks[i].getStatusIcon() + "] " + tasks[i].description);
                }
                printDivider();
            } else if (line.startsWith("mark ")) {
                try {
                    Task currentTask = tasks[Integer.parseInt(line.substring(5)) - 1];
                    currentTask.mark();
                    printDivider();
                    printIndented("Nice! I've marked this task as done:");
                    printIndented("  [X] " + currentTask.getDescription());
                    printDivider();
                } catch (Exception e) {
                    printDivider();
                    printIndented("Invalid input.");
                    printDivider();
                }
            } else if (line.startsWith("unmark ")) {
                try {
                    Task currentTask = tasks[Integer.parseInt(line.substring(7)) - 1];
                    currentTask.unmark();
                    printDivider();
                    printIndented("OK, I've marked this task as not done yet:");
                    printIndented("  [ ] " + currentTask.getDescription());
                    printDivider();
                } catch (Exception e) {
                    printDivider();
                    printIndented("Invalid input.");
                    printDivider();
                }
            } else if (line.equals("bye")) {
                printDivider();
                printIndented("Bye. Hope to see you again soon!");
                printDivider();
                return;
            } else {
                printDivider();
                printIndented("added: " + line);
                printDivider();
                tasks[totalTasks] = new Task(line);
                totalTasks++;
            }
        }
    }
}
