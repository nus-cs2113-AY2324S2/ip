package bart;
import bart.task.*;
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
            String[] commandParts = command.split(" ");
            String taskType = commandParts[0];

            switch (taskType) {
                case "todo":
                    try {
                        tasksArray[taskCount] = new Todo(command);
                    } catch (IllegalArgumentException e) {
                        System.out.println(LINE + "\nOOPS!!! The description of a todo cannot be empty.\n" + LINE);
                        return;
                    }
                    break;
                case "deadline":
                    try {
                        tasksArray[taskCount] = new Deadline(command);
                    } catch (IllegalArgumentException e) {
                        System.out.println(LINE + "\nOOPS!!! The description of a deadline cannot be empty.\n" + LINE);
                        return;
                    }
                    break;
                case "event":
                    try {
                        tasksArray[taskCount] = new Event(command);
                    } catch (IllegalArgumentException e) {
                        System.out.println(LINE + "\nOOPS!!! The description of a event cannot be empty.\n" + LINE);
                        return;
                    }
                    break;
                default:
                    System.out.println(LINE + "\nOOPS!!! I'm sorry, but I don't know what that means :-(\n" + LINE);
                    return;
            }
            tasksArray[taskCount].printTask(taskCount);
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
                System.out.println(LINE + "\nNice! I've marked this task as done:\n" + LINE);
            } else {
                task.markAsUndone();
                System.out.println(LINE + "\nOK, I've marked this task as not done yet:\n" + LINE);
            }
            System.out.println(tasksArray[taskIndex].getTaskMark() + " " + tasksArray[taskIndex].description);
        } else {
            System.out.println(LINE + "Invalid task number.\n" + LINE);
        }
    }

    private static void byeUser() {
        System.out.println("Bye. Hope to see you again soon!\n" + LINE);
    }
}