import java.util.Scanner;

public class Duke {
    static String LINE = "____________________________________________________________";
    static Task[] tasksArray = new Task[100];
    static int taskCount = 0;
    public static void main(String[] args) {
        greetUser();
        manageTask();
        byeUser();
    }
    public static void greetUser() {
        System.out.println(LINE);
        System.out.println("Hello! I'm Bartholomew, but you can call me Bart for short :)");
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }
    public static void manageTask() {
        Scanner in = new Scanner(System.in);
        String command = "";
        while (!command.equals("bye")) {
            command = in.nextLine();
            if (command.equals("list")) {
                listTasks();
            } else if (command.startsWith("mark")) {
                markTask(command);
            } else if (command.startsWith("unmark")) {
                unmarkTask(command);
            } else {
                if (taskCount < tasksArray.length) {
                    tasksArray[taskCount] = new Task(command); // Create new Task object
                    taskCount++;
                    System.out.println(LINE);
                    System.out.println("added: " + command);
                    System.out.println(LINE);
                } else {
                    System.out.println("Sorry, task list is full.");
                }
            }
        }
    }
    public static void listTasks() {
        System.out.println(LINE);
        //Edge case: If list empty
        if (taskCount == 0) {
            System.out.println("Nothing added");
        }
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + "." + tasksArray[i].getStatusIcon() + " " + tasksArray[i].description);
        }
        System.out.println(LINE);
    }

    public static void markTask(String command) {
        int taskIndex = Integer.parseInt(command.substring(5).trim()) - 1;

        // Check if the task index is within the valid range
        if (taskIndex >= 0 && taskIndex < taskCount) {
            tasksArray[taskIndex].markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasksArray[taskIndex].getStatusIcon() + " " + tasksArray[taskIndex].description);
        } else {
            System.out.println("Invalid task number.");
        }
        System.out.println(LINE);
    }

    //similar to markTask
    public static void unmarkTask(String command) {
        int taskIndex = Integer.parseInt(command.substring(7).trim()) - 1;
        if (taskIndex >= 0 && taskIndex < taskCount) {
            tasksArray[taskIndex].markAsUndone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(tasksArray[taskIndex].getStatusIcon() + " " + tasksArray[taskIndex].description);
        } else {
            System.out.println("Invalid task number.");
        }
        System.out.println(LINE);
    }

    private static void byeUser() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }
}
