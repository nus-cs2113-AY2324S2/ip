import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____  _    _  ____ _____ ___  ____  \n"
                + "|  _ \\| |  | |/ ___|_   _/ _ \\|  _ \\ \n"
                + "| | | | |  | | |     | || | | | | | |\n"
                + "| |_| | |__| | |___  | || |_| | |_| |\n"
                + "|____/ \\____/ \\____| |_| \\___/|____/ \n";
        System.out.println("Hello from Duke\n" + logo +
                " Hello! I'm Ductod\n" +
                " What can I do for you?\n" +
                "____________________________________________________________");

        Scanner in = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int taskCount = 0;
        int taskNumber;
        String line;
        System.out.println("What can I do for you?" + "\n"
                + "____________________________________________________________");

        do {
            line = in.nextLine();
            System.out.println("____________________________________________________________");
            if (line.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    if (tasks[i].isDone) {
                        System.out.println((i + 1) + "." + "[" + "X" + "] " + tasks[i].description);
                    } else {
                        System.out.println((i + 1) + "." + "[" + " ]" + tasks[i].description);
                    }
                }
                System.out.println("____________________________________________________________");
            } else if (line.startsWith("mark ")) {
                taskNumber = Integer.parseInt(line.substring(5)) - 1; // Subtract 1 for zero-based index
                if (taskNumber >= 0 && taskNumber < taskCount) { // Check if the task number is valid
                    tasks[taskNumber].markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + tasks[taskNumber].description);
                } else {
                    System.out.println("Task number is invalid.");
                }
            } else if (line.startsWith("unmark ")) {
                taskNumber = Integer.parseInt(line.substring(7)) - 1; // Subtract 1 for zero-based index
                if (taskNumber >= 0 && taskNumber < taskCount) { // Check if the task number is valid
                    tasks[taskNumber].markAsNotDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  " + tasks[taskNumber].description);
                } else {
                    System.out.println("Task number is invalid.");
                }
            } else if (!line.equals("bye")) {
                tasks[taskCount] = new Task(line);
                System.out.println("added: " + tasks[taskCount].description);
                taskCount++;
            }
            System.out.println("____________________________________________________________");
        } while (!line.equals("bye"));

        System.out.println("Bye. Hope to see you again soon!");
        in.close();
    }
}
