import java.util.ArrayList;
import java.util.Scanner;
public class Nehsik {
    public static final int MARK_TASK_INDEX = 5;
    public static final int UNMARK_TASK_INDEX = 7;
    public static final int DELETE_TASK_INDEX = 7;
    public static final int TODO_DESCRIPTION_POSITION = 5;
    public static final String DATA_FILE_PATH = "./nehsik.txt";

    public static void main(String[] args) {
        displayGreetings();

        Scanner in = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();
        DataManager.loadTasksFromFile(DATA_FILE_PATH, taskList);

        while (true) {
            try {
                String command = in.nextLine().trim();
                if (command.equals("list")) {
                    displayTaskList(taskList);
                } else if (command.startsWith("mark")) {
                    markTask(command, taskList);
                } else if (command.startsWith("unmark")) {
                    unmarkTask(command, taskList);
                }  else if (command.startsWith("todo")) {
                    addTodoTask(command, taskList);
                    acknowledgeTaskAdded(taskList);
                } else if (command.startsWith("deadline")) {
                    addDeadlineTask(command, taskList);
                    acknowledgeTaskAdded(taskList);
                } else if (command.startsWith("event")) {
                    addEventTask(command, taskList);
                    acknowledgeTaskAdded(taskList);
                } else if (command.startsWith("delete")) {
                    deleteTask(command, taskList);
                } else if (command.equals("bye")) {
                    displayExitMessage();
                    break;
                } else {
                    throw new NehsikException("Invalid Command");
                }
            } catch (NehsikException e) {
                printLine();
                System.out.println(e.getMessage());
                printLine();
            }

            DataManager.saveTasksToFile(DATA_FILE_PATH, taskList);
        }

        in.close();
    }

    private static void displayTaskList(ArrayList<Task> taskList) {
        printLine();
        int taskListSize = taskList.size();
        if (taskListSize > 0) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskListSize; i++) {
                System.out.println((i + 1) + "." + taskList.get(i).toString());
            }
        } else {
            System.out.println("You have no tasks");
        }
        printLine();
    }

    private static void markTask(String command, ArrayList<Task> taskList) throws NehsikException {
        if (command.length() < MARK_TASK_INDEX) {
            throw new NehsikException("Please mention the task number you would like to mark");
        }

        if (command.charAt(MARK_TASK_INDEX - 1) != ' ') {
            throw new NehsikException("Invalid Command");
        }

        int taskNum = Integer.parseInt(command.substring(MARK_TASK_INDEX)) - 1;
        int taskListSize = taskList.size();
        if (taskNum >= taskListSize || taskNum < 0) {
            throw new NehsikException("Please enter a valid task number. There are " + taskListSize + " tasks in the list");
        }
        printLine();
        System.out.println("Nice! I've marked this task as done:");
        taskList.get(taskNum).markAsDone();
        System.out.println(taskList.get(taskNum).toString());
        printLine();
    }

    private static void unmarkTask(String command, ArrayList<Task> taskList) throws NehsikException {
        if (command.length() < UNMARK_TASK_INDEX) {
            throw new NehsikException("Please mention the task number you would like to unmark");
        }

        if (command.charAt(UNMARK_TASK_INDEX - 1) != ' ') {
            throw new NehsikException("Invalid Command");
        }

        int taskNum = Integer.parseInt(command.substring(UNMARK_TASK_INDEX)) - 1;
        int taskListSize = taskList.size();
        if (taskNum >= taskListSize || taskNum < 0) {
            throw new NehsikException("Please enter a valid task number. There are " + taskListSize + " tasks in the list");
        }
        printLine();
        System.out.println("OK, I've marked this task as not done yet:");
        taskList.get(taskNum).markAsUndone();
        System.out.println(taskList.get(taskNum).toString());
        printLine();
    }

    private static void addTodoTask(String command, ArrayList<Task> taskList) throws NehsikException {
        if (command.length() < TODO_DESCRIPTION_POSITION) {
            throw new NehsikException("The description of a todo cannot be empty");
        }

        if (command.charAt(TODO_DESCRIPTION_POSITION - 1) != ' ') {
            throw new NehsikException("Invalid Command");
        }

        String taskDescription = command.substring(TODO_DESCRIPTION_POSITION).trim();
        taskList.add(new Todo(taskDescription));
    }

    private static void addDeadlineTask(String command, ArrayList<Task> taskList) throws NehsikException {
        int descriptionStartPosition = command.indexOf("deadline ") + 9;
        if (command.length() <= descriptionStartPosition) {
            throw new NehsikException("The description of a deadline cannot be empty");
        }

        if (command.charAt(descriptionStartPosition - 1) != ' ') {
            throw new NehsikException("Invalid Command");
        }

        int descriptionEndPosition = command.indexOf("/by ") - 1;
        String taskDescription = command.substring(descriptionStartPosition, descriptionEndPosition).trim();

        int byStringPosition = command.indexOf("/by ") + 4;
        String by = command.substring(byStringPosition).trim();

        taskList.add(new Deadline(taskDescription, by));
    }

    private static void addEventTask(String command, ArrayList<Task> taskList) throws NehsikException {
        int descriptionStartPosition = command.indexOf("event ") + 6;
        if (command.length() <= descriptionStartPosition) {
            throw new NehsikException("The description of an event cannot be empty");
        }
        if (command.charAt(descriptionStartPosition - 1) != ' ') {
            throw new NehsikException("Invalid Command");
        }

        int descriptionEndPosition = command.indexOf("/from ") - 1;
        String taskDescription = command.substring(descriptionStartPosition, descriptionEndPosition).trim();

        int fromStringStartPosition = command.indexOf("/from ") + 6;
        int fromStringEndPosition = command.indexOf("/to ") - 1;
        String from = command.substring(fromStringStartPosition, fromStringEndPosition).trim();

        int toStringPosition = command.indexOf("/to ") + 4;
        String to = command.substring(toStringPosition).trim();

        taskList.add(new Event(taskDescription, from, to));
    }

    private static void deleteTask(String command, ArrayList<Task> taskList) throws NehsikException {
        if (command.length() < DELETE_TASK_INDEX) {
            throw new NehsikException("Please mention the task number you would like to delete");
        }

        if (command.charAt(DELETE_TASK_INDEX - 1) != ' ') {
            throw new NehsikException("Invalid Command");
        }

        int taskNum = Integer.parseInt(command.substring(DELETE_TASK_INDEX)) - 1;
        int taskListSize = taskList.size();
        if (taskNum >= taskListSize || taskNum < 0) {
            throw new NehsikException("Please enter a valid task number. There are " + taskListSize + " tasks in the list");
        }

        printLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + taskList.get(taskNum).toString());
        taskList.remove(taskNum);
        System.out.println("Now you have " + taskList.size() + " tasks in the list");
        printLine();
    }

    private static void acknowledgeTaskAdded(ArrayList<Task> taskList) {
        int latestTaskIndex = taskList.size() - 1;
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + taskList.get(latestTaskIndex).toString());
        System.out.println("Now you have " + (latestTaskIndex + 1) + " tasks in the list");
        printLine();
    }

    private static void printLine() {
        System.out.println("____________________________________________________________");
    }

    private static void displayGreetings() {
        printLine();
        System.out.println("Hala habibi! Shlonik? Shakhbarak?");
        System.out.println("I'm Nehsik, What can I do for you?");
        printLine();
    }

    private static void displayExitMessage() {
        printLine();
        System.out.println("Yalla bye. Ka Mal Lah!");
        printLine();
    }
}
