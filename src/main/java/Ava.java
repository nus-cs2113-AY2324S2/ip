import java.util.Scanner;

public class Ava {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        greet();
        addTask();
        exit();
    }

    public static void addTask() {
        Task[] tasks = new Task[100];
        Scanner in = new Scanner(System.in);
        int taskCount = 0;
        String task = in.nextLine();
        tasks[taskCount] = new Task(task);
        String description = tasks[taskCount].getDescription();
        if (description.equals("bye")) {
            return;
        }
        while (!description.equals("bye")) {
            if (description.equals("list")) {
                printLine();
                System.out.println("Here are the tasks in your list:");
                listTask(tasks, taskCount);
                printLine();
            } else if (description.contains("mark")) {
                markTask(tasks, description);
            } else {
                printLine();
                System.out.println("added: " + tasks[taskCount].getDescription());
                printLine();
                taskCount += 1;
            }
            task = in.nextLine();
            tasks[taskCount] = new Task(task);
            description = tasks[taskCount].getDescription();
        }
    }

    public static void markTask(Task[] tasks, String command) {
        if (command.contains("unmark")) {
            printLine();
            command = command.replace("unmark ", "");
            int changedTask = Integer.parseInt(command);
            tasks[changedTask - 1].markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  [" + tasks[changedTask - 1].getStatusIcon() + "] "
                    + tasks[changedTask - 1].getDescription());
            printLine();
        } else {
            printLine();
            command = command.replace("mark ", "");
            int changedTask = Integer.parseInt(command);
            tasks[changedTask - 1].markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  [" + tasks[changedTask - 1].getStatusIcon() + "] "
                    + tasks[changedTask - 1].getDescription());
            printLine();
        }
    }
    public static void listTask(Task[] tasks, int taskCount) {
        int noOftask = 0;
        while (noOftask < taskCount) {
            System.out.println((noOftask + 1) + ".[" + tasks[noOftask].getStatusIcon()
                    + "] " + tasks[noOftask].getDescription());
            noOftask += 1;
        }
    }

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    public static void greet() {
        printLine();
        System.out.println(" Hello!!! AvavaAVA!!! Here is Ava!!!");
        System.out.println(" Let's have a relaxing and happy chat together!!!");
        System.out.println(" What can I do for you?");
        printLine();
    }

    public static void exit() {
        printLine();
        System.out.println(" Bye!!! Hope to see you again soon!!!");
        printLine();
    }
}
