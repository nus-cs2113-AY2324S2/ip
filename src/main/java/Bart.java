import java.util.ArrayList;
import java.util.Scanner;

public class Bart {
    private static final String LINE = "____________________________________________________________";
    private static final ArrayList<Task> tasksList = new ArrayList<>();

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

            if (command.equals("list")) {
                listTasks();
            } else if (command.startsWith("mark")) {
                markTask(command, true);
            } else if (command.startsWith("unmark")) {
                markTask(command, false);
            } else if (command.startsWith("delete")) {
                deleteTask(command);
            } else {
                addNewTask(command);
            }
        }
    }
    
    private static void addNewTask(String command) {
        String[] commandParts = command.split(" ");
        String taskType = commandParts[0];

        switch (taskType) {
            case "todo":
                try {
                    tasksList.add(new Todo(command));
                } catch (IllegalArgumentException e) {
                    System.out.println(LINE + "\nOOPS!!! The description of a todo cannot be empty.\n" + LINE);
                    return;
                }
                break;

            case "deadline":
                try {
                    tasksList.add(new Deadline(command));
                } catch (IllegalArgumentException e) {
                    System.out.println(LINE + "\nOOPS!!! The description of a deadline cannot be empty.\n" + LINE);
                    return;
                }
                break;
            case "event":
                try {
                    tasksList.add(new Event(command));
                } catch (IllegalArgumentException e) {
                    System.out.println(LINE + "\nOOPS!!! The description of a event cannot be empty.\n" + LINE);
                    return;
                }
                break;
            default:
                System.out.println(LINE + "\nOOPS!!! I'm sorry, but I don't know what that means :-(\n" + LINE);
                return;
        }
        tasksList.get(tasksList.size() - 1).printTask(tasksList.size());
    }

    public static void listTasks() {
        System.out.println(LINE + "\nHere are the tasks in your list:");
        //Edge case: If list empty
        if (tasksList.isEmpty()) {
            System.out.println("Nothing added here....");
        }

        for (int i = 0; i < tasksList.size(); i++) {
            System.out.println((i + 1) + "." + tasksList.get(i).toString());
        }
        System.out.println(LINE);
    }

    public static void markTask(String command, boolean mark) {
        int taskIndex = Integer.parseInt(command.substring(command.indexOf(' ') + 1).trim()) - 1;
        if (taskIndex >= 0 && taskIndex < tasksList.size()) {
            Task task = tasksList.get(taskIndex);
            if (mark) {
                task.markAsDone();
                System.out.println(LINE + "\nNice! I've marked this task as done:");
            } else {
                task.markAsUndone();
                System.out.println(LINE + "\nOK, I've marked this task as not done yet:");
            }
            System.out.println(task.getTaskMark() + " " + task.description + "\n" + LINE );
        } else {
            System.out.println(LINE + "\nInvalid task number.\n" + LINE);
        }
    }

    private static void deleteTask(String command) {
        int taskIndex = Integer.parseInt(command.substring(command.indexOf(' ') + 1).trim()) - 1;
        if (taskIndex >= 0 && taskIndex < tasksList.size()) {
            Task deletedTask = tasksList.remove(taskIndex);
            System.out.println(LINE + "\nNoted. I've removed this task:\n");
            System.out.println(deletedTask.toString());
            System.out.println("Now you have " + tasksList.size() + " tasks in the list.\n");
        } else {
            System.out.println(LINE + "\nInvalid task number.\n" + LINE);
        }
    }

    private static void byeUser() {
        System.out.println("Bye. Hope to see you again soon!\n" + LINE);
    }
}