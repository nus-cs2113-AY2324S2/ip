import java.util.Scanner;

public class Floda {
    public static void main(String[] args) {
        String name = "Floda";
        System.out.println("Hello! I'm " + name);
        Task[] list = new Task[100];
        int taskCounter = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("I can keep track of a to-do list for you! Just type what you want to add to the list.");
        while (true) {
            String line = scanner.nextLine().trim();
            if (line.equals("bye")) {
                break;
            } else if (line.equals("list")) {
                if (taskCounter == 0) {
                    System.out.println("Your to-do list is empty.");
                } else {
                    System.out.println("List so far: ");
                    for (int i = 0; i < taskCounter; i++) {
                        System.out.println((i + 1) + "." + list[i]);
                    }
                }
            } else if (line.startsWith("mark")) {
                handleMarkTask(line, list, taskCounter);
            } else if (line.startsWith("unmark")) {
                handleUnmarkTask(line, list, taskCounter);
            } else if (line.startsWith("deadline")) {
                handleDeadlineTask(line, list, taskCounter);
            } else if (line.startsWith("todo")) {
                handleTodoTask(line, list, taskCounter);
            } else if (line.startsWith("event")) {
                handleEventTask(line, list, taskCounter);
            } else {
//                list[taskCounter] = new Task(line);
//                taskCounter++;
//                System.out.println("Added: " + line + "\nNow you have " + taskCounter + " items in the list!");
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
        scanner.close();
    }

    private static void handleMarkTask(String line, Task[] list, int taskCounter) {
        Scanner taskScanner = new Scanner(line);
        taskScanner.next();
        if (taskScanner.hasNextInt()) {
            int taskNumber = taskScanner.nextInt() - 1;
            if (isValidTaskNumber(taskNumber, taskCounter)) {
                list[taskNumber].setDone(true);
                System.out.println("I have marked this task as done:\n" + list[taskNumber]);
            } else {
                System.out.println("Invalid task number! Please check with 'list'.");
            }
        } else {
            System.out.println("Invalid input! Please check with 'list'.");
        }
        taskScanner.close();
    }

    private static void handleUnmarkTask(String line, Task[] list, int taskCounter) {
        Scanner taskScanner = new Scanner(line);
        taskScanner.next();
        if (taskScanner.hasNextInt()) {
            int taskNumber = taskScanner.nextInt() - 1;
            if (isValidTaskNumber(taskNumber, taskCounter)) {
                list[taskNumber].setDone(false);
                System.out.println("I have marked this task as not done:\n" + list[taskNumber]);
            } else {
                System.out.println("Invalid task number! Please check with 'list'.");
            }
        } else {
            System.out.println("Invalid input! Please check with 'list'.");
        }
        taskScanner.close();
    }

    private static void handleDeadlineTask(String line, Task[] list, int taskCounter) {
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

    private static void handleTodoTask(String line, Task[] list, int taskCounter) {
        Scanner taskScanner = new Scanner(line);
        taskScanner.next();
        String remaining = taskScanner.nextLine().trim();
        list[taskCounter] = new ToDo(remaining);
        taskCounter++;
        System.out.println("Added: " + list[taskCounter - 1] + "\nNow you have " + (taskCounter) + " items in the list!");
    }

    private static void handleEventTask(String line, Task[] list, int taskCounter) {
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

    private static boolean isValidTaskNumber(int taskNumber, int taskCounter) {
        return taskNumber >= 0 && taskNumber < taskCounter;
    }
}
