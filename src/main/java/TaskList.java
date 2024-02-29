import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;

public class TaskList {
    public static ArrayList<Task> list = new ArrayList<>();
    private final Storage storage;
    static Boolean isActive = true;

    public TaskList(Storage storage) {
        this.storage = storage;
    }

    public static void setActive(Boolean value) {
        isActive = value;
    }

    public void loadTasks() throws FileNotFoundException, InvalidInputException{
        storage.loadTasks();
    }

    public static ArrayList<Task> getList() {
        return list;
    }

    static void handleByeTask() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    static void handleListTask() {
        if (list.isEmpty()) {
            System.out.println("Your to-do list is empty.");
        } else {
            System.out.println("List so far: ");
            for (int i = 0; i < list.size(); i++) {
                System.out.println((i + 1) + "." + list.get(i));
            }
        }
    }
    static void handleDeleteTask(String line) throws InvalidInputException {
        Scanner taskScanner = new Scanner(line);
        taskScanner.next();
        if (taskScanner.hasNextInt()) {
            int taskNumber = taskScanner.nextInt() - 1;
            if (isValidTaskNumber(taskNumber)) {
                System.out.println("Deleting task: " + list.get(taskNumber));
                list.remove(taskNumber);
                System.out.println("Task deleted successfully!");
            } else {
                throw new InvalidInputException("Invalid task number! Please check with 'list'.");
            }
        }
    }

    static void handleMarkTask(String line) throws InvalidInputException {
        Scanner taskScanner = new Scanner(line);
        taskScanner.next();
        if (taskScanner.hasNextInt()) {
            int taskNumber = taskScanner.nextInt() - 1;
            if (isValidTaskNumber(taskNumber)) {
                list.get(taskNumber).setDone(true);
                System.out.println("I have marked this task as done:\n" + list.get(taskNumber));
            } else {
                throw new InvalidInputException("Invalid task number! Please check with 'list'.");
            }
        } else {
            throw new InvalidInputException("Invalid input! Please check with 'list'.");
        }
        taskScanner.close();
    }

    static void handleUnmarkTask(String line) throws InvalidInputException {
        Scanner taskScanner = new Scanner(line);
        taskScanner.next();
        if (taskScanner.hasNextInt()) {
            int taskNumber = taskScanner.nextInt() - 1;
            if (isValidTaskNumber(taskNumber)) {
                list.get(taskNumber).setDone(false);
                System.out.println("I have marked this task as not done:\n" + list.get(taskNumber));
            } else {
                throw new InvalidInputException("Invalid task number! Please check with 'list'.");
            }
        } else {
            throw new InvalidInputException("Invalid input! Please check with 'list'.");
        }
        taskScanner.close();
    }

    static void handleDeadlineTask(String line) throws InvalidInputException {
        Scanner taskScanner = new Scanner(line);
        taskScanner.next();
        String remaining = taskScanner.nextLine().trim();
        int byIndex = remaining.indexOf("/by");
        if (byIndex == -1 || byIndex == 0) {
            throw new InvalidInputException("Invalid input format! Use: 'deadline <description> /by <time>'");
        }
        final int DESCRIPTION_END_INDEX = byIndex;
        final int BY_START_INDEX = byIndex + 3; // "/by".length() == 3
        String description = remaining.substring(0, DESCRIPTION_END_INDEX).trim();
        String by = remaining.substring(BY_START_INDEX).trim();
        list.add(new Deadline(description, by, false));
        System.out.println("Added: " + list.getLast() + "\nNow you have " + list.size() + " items in the list!");
    }

    static void handleEventTask(String line) throws InvalidInputException {
        Scanner taskScanner = new Scanner(line);
        taskScanner.next();
        String remaining = taskScanner.nextLine().trim();
        int fromIndex = remaining.indexOf("/from");
        if (fromIndex == -1 || fromIndex == 0) {
            throw new InvalidInputException("Invalid input format! Use: 'event <description> /from <start time> /to <end time>'");
        }
        final int DESCRIPTION_END_INDEX = fromIndex;
        final int FROM_START_INDEX = fromIndex + 5; // "/from".length() == 5
        int toIndex = remaining.indexOf("/to");
        if (toIndex == -1 || toIndex <= FROM_START_INDEX) {
            throw new InvalidInputException("Invalid input format! Use: 'event <description> /from <start time> /to <end time>'");
        }
        final int TO_START_INDEX = toIndex + 3; // "/to".length() == 3
        String description = remaining.substring(0, DESCRIPTION_END_INDEX).trim();
        String from = remaining.substring(FROM_START_INDEX, toIndex).trim();
        String to = remaining.substring(TO_START_INDEX).trim();
        list.add(new Events(description, from, to, false));
        System.out.println("Added: " + list.getLast() + "\nNow you have " + list.size() + " items in the list!");
    }


    static void handleTodoTask(String line) {
        Scanner taskScanner = new Scanner(line);
        taskScanner.next();
        String remaining = taskScanner.nextLine().trim();
        list.add(new ToDo(remaining, false));
        System.out.println("Added: " + list.getLast() + "\nNow you have " + list.size() + " items in the list!");
    }

    static void handleFindTask(String line) {
        String query = line.substring("find".length()).trim();
        ArrayList<Task> matchingTasks = findTasks(query);
        if (matchingTasks.isEmpty()) {
            System.out.println("No matching tasks found.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.println((i + 1) + "." + matchingTasks.get(i));
            }
        }
    }

    private static ArrayList<Task> findTasks(String query) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : list) {
            if (task.getDescription().contains(query)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    private static boolean isValidTaskNumber(int taskNumber) {
        return taskNumber >= 0 && taskNumber < list.size();
    }
}
