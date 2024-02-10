import java.util.Scanner;

public class Bart {
    private static final String LINE = "____________________________________________________________";
    private static final Task[] tasksArray = new Task[100];
    private static int taskCount = 0;

    public static void main(String[] args) {
        greetUser();
        manageTask();
        byeUser();
    }
    public static void greetUser() {
        System.out.println(LINE + "\nHello! I'm Bartholomew, but you can call me Bart for short :)");
        System.out.println("What can I do for you?\n" + LINE);
    }
    public static void manageTask() {
        Scanner in = new Scanner(System.in);
        String command = "";

        while (!command.equals("bye")) {
            command = in.nextLine();

            switch (command) {
                case "list":
                    listTasks();
                    break;
                case "bye":
                    break;
                default:
                    handleMarking(command);
                    break;
            }
        }
    }

    private static void handleMarking(String command) {
        if (command.startsWith("mark")) {
            markTask(command, true);
        } else if (command.startsWith("unmark")) {
            markTask(command, false);
        } else {
            addNewTask(command);
        }
    }

    private static void addNewTask(String command) {
        if (taskCount < tasksArray.length) {
            switch (command.split(" ")[0]) {
                case "todo":
                    tasksArray[taskCount] = new Todo(command);
                    break;
                case "deadline":
                    tasksArray[taskCount] = new Deadline(command);
                    break;
                case "event":
                    tasksArray[taskCount] = new Event(command);
                    break;
                default:
                    tasksArray[taskCount] = new Task(command);
                    break;
            }
            tasksArray[taskCount].printT(taskCount);
            taskCount++;
        } else {
            System.out.println("Sorry, task list is full.");
        }
    }

    public static void listTasks() {
        System.out.println(LINE);
        //Edge case: If list empty
        if (taskCount == 0) {
            System.out.println("Nothing added");
        }

        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + "." + tasksArray[i].toString());
        }
        System.out.println(LINE);
    }

    public static void markTask(String command, boolean mark) {
        int taskIndex = Integer.parseInt(command.substring(command.indexOf(' ') + 1).trim()) - 1;
        if (taskIndex >= 0 && taskIndex < taskCount) {
            Task task = tasksArray[taskIndex];
            if (mark) {
                task.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
            } else {
                task.markAsUndone();
                System.out.println("OK, I've marked this task as not done yet:");
            }
            System.out.println(tasksArray[taskIndex].getTaskMark() + " " + tasksArray[taskIndex].description);
        } else {
            System.out.println("Invalid task number.");
        }
        System.out.println(LINE);
    }

    private static void byeUser() {
        System.out.println("Bye. Hope to see you again soon!\n" + LINE);
    }
}
