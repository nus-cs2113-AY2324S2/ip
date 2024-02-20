import java.util.Scanner;
import java.util.ArrayList;

public class Floda {
    private static ArrayList<Task> list = new ArrayList<>();

    public static void main(String[] args) {
        String NAME = "Floda";
        System.out.println("Hello! I'm " + NAME);
        Scanner scanner = new Scanner(System.in);
        System.out.println("I can keep track of a to-do list for you! Just type what you want to add to the list.");
        while (true) {
            try {
                String line = scanner.nextLine().trim();
                String[] parts = line.split(" ", 2);
                String command = parts[0].trim();
                switch (command) {
                    case "bye":
                        System.out.println("Bye. Hope to see you again soon!");
                        scanner.close();
                        return;
                    case "list":
                        if (list.isEmpty()) {
                            System.out.println("Your to-do list is empty.");
                        } else {
                            System.out.println("List so far: ");
                            for (int i = 0; i < list.size(); i++) {
                                System.out.println((i + 1) + "." + list.get(i));
                            }
                        }
                        break;
                    case "mark":
                        handleMarkTask(line);
                        break;
                    case "unmark":
                        handleUnmarkTask(line);
                        break;
                    case "deadline":
                        handleDeadlineTask(line);
                        break;
                    case "todo":
                        handleTodoTask(line);
                        break;
                    case "event":
                        handleEventTask(line);
                        break;
                    case "delete":
                        handleDeleteTask(line);
                        break;
                    default:
                        throw new InvalidInputException("Invalid input!");
                }
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void handleDeleteTask(String line) throws InvalidInputException {
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

    private static void handleMarkTask(String line) throws InvalidInputException {
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

    private static void handleUnmarkTask(String line) throws InvalidInputException {
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

    private static void handleDeadlineTask(String line) throws InvalidInputException {
        Scanner taskScanner = new Scanner(line);
        taskScanner.next();
        String remaining = taskScanner.nextLine().trim();
        int byIndex = remaining.indexOf("/by");
        if (byIndex == -1 || byIndex == 0) {
            throw new InvalidInputException("Invalid input format! Use: 'deadline <description> /by <time>'");
        }
        String description = remaining.substring(0, byIndex).trim();
        String by = remaining.substring(byIndex + 3).trim();
        list.add(new Deadline(description, by));
        System.out.println("Added: " + list.get(list.size() - 1) + "\nNow you have " + list.size() + " items in the list!");
    }

    private static void handleEventTask(String line) throws InvalidInputException {
        Scanner taskScanner = new Scanner(line);
        taskScanner.next();
        String remaining = taskScanner.nextLine().trim();
        int fromIndex = remaining.indexOf("/from");
        if (fromIndex == -1 || fromIndex == 0) {
            throw new InvalidInputException("Invalid input format! Use: 'event <description> /from <start time> /to <end time>'");
        }
        int toIndex = remaining.indexOf("/to");
        if (toIndex == -1 || toIndex <= fromIndex + 5) {
            throw new InvalidInputException("Invalid input format! Use: 'event <description> /from <start time> /to <end time>'");
        }
        String description = remaining.substring(0, fromIndex).trim();
        String from = remaining.substring(fromIndex + 5, toIndex).trim();
        String to = remaining.substring(toIndex + 3).trim();
        list.add(new Events(description, from, to));
        System.out.println("Added: " + list.get(list.size() - 1) + "\nNow you have " + list.size() + " items in the list!");
    }

    private static void handleTodoTask(String line) throws InvalidInputException {
        Scanner taskScanner = new Scanner(line);
        taskScanner.next();
        String remaining = taskScanner.nextLine().trim();
        list.add(new ToDo(remaining));
        System.out.println("Added: " + list.get(list.size() - 1) + "\nNow you have " + list.size() + " items in the list!");
    }

    private static boolean isValidTaskNumber(int taskNumber) {
        return taskNumber >= 0 && taskNumber < list.size();
    }
}
