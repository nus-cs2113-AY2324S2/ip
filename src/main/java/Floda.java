import java.util.Scanner;

public class Floda {
    private static int taskCounter = 0;
    public static void main(String[] args) {
        String name = "Floda";
        System.out.println("Hello! I'm " + name);
        Task[] list = new Task[100];
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
                        if (taskCounter == 0) {
                            System.out.println("Your to-do list is empty.");
                        } else {
                            System.out.println("List so far: ");
                            for (int i = 0; i < taskCounter; i++) {
                                System.out.println((i + 1) + "." + list[i]);
                            }
                        }
                        break;
                    case "mark":
                        handleMarkTask(line, list);
                        break;
                    case "unmark":
                        handleUnmarkTask(line, list);
                        break;
                    case "deadline":
                        handleDeadlineTask(line, list);
                        break;
                    case "todo":
                        handleTodoTask(line, list);
                        break;
                    case "event":
                        handleEventTask(line, list);
                        break;
                    default:
                        throw new InvalidInputException("Invalid input!");
                }
            } catch (InvalidInputException e) {
            }
        }
    }

    private static void handleMarkTask(String line, Task[] list) throws InvalidInputException {
        Scanner taskScanner = new Scanner(line);
        taskScanner.next();
        if (taskScanner.hasNextInt()) {
            int taskNumber = taskScanner.nextInt() - 1;
            if (isValidTaskNumber(taskNumber)) {
                list[taskNumber].setDone(true);
                System.out.println("I have marked this task as done:\n" + list[taskNumber]);
            } else {
                throw new InvalidInputException("Invalid task number! Please check with 'list'.");
            }
        } else {
            throw new InvalidInputException("Invalid input! Please check with 'list'.");
        }
        taskScanner.close();
    }

    private static void handleUnmarkTask(String line, Task[] list) throws InvalidInputException {
        Scanner taskScanner = new Scanner(line);
        taskScanner.next();
        if (taskScanner.hasNextInt()) {
            int taskNumber = taskScanner.nextInt() - 1;
            if (isValidTaskNumber(taskNumber)) {
                list[taskNumber].setDone(false);
                System.out.println("I have marked this task as not done:\n" + list[taskNumber]);
            } else {
                throw new InvalidInputException("Invalid task number! Please check with 'list'.");
            }
        } else {
            throw new InvalidInputException("Invalid input! Please check with 'list'.");
        }
        taskScanner.close();
    }

    private static void handleDeadlineTask(String line, Task[] list) throws InvalidInputException {
        Scanner taskScanner = new Scanner(line);
        taskScanner.next();
        String remaining = taskScanner.nextLine().trim();
        String[] parts = remaining.split("/by");
        String description = parts[0].trim();
        String by = parts[1].trim();
        list[taskCounter] = new Deadline(description, by);
        taskCounter++;
        System.out.println("Added: " + list[taskCounter - 1] + "\nNow you have " + (taskCounter) + " items in the list!");
    }

    private static void handleTodoTask(String line, Task[] list) throws InvalidInputException {
        Scanner taskScanner = new Scanner(line);
        taskScanner.next();
        String remaining = taskScanner.nextLine().trim();
        list[taskCounter] = new ToDo(remaining);
        taskCounter++;
        System.out.println("Added: " + list[taskCounter - 1] + "\nNow you have " + (taskCounter) + " items in the list!");
    }

    private static void handleEventTask(String line, Task[] list) throws InvalidInputException {
        Scanner taskScanner = new Scanner(line);
        taskScanner.next();
        String remaining = taskScanner.nextLine().trim();
        remaining = remaining.replace("/to", "/from");
        String[] parts = remaining.split("/from");
        String description = parts[0].trim();
        String from = parts[1].trim();
        String to = parts[2].trim();
        list[taskCounter] = new Events(description, from, to);
        taskCounter++;
        System.out.println("Added: " + list[taskCounter - 1] + "\nNow you have " + (taskCounter) + " items in the list!");

    }

    private static boolean isValidTaskNumber(int taskNumber) {
        return taskNumber >= 0 && taskNumber < taskCounter;
    }
}
