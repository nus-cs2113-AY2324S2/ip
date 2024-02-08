import java.util.Scanner;

public class Floda {
    public static void main(String[] args) {
        String name = "Floda";
        System.out.println("Hello! I'm " + name);
        Task[] list = new Task[100];
        int taskCounter = 0;
        Scanner scanner = new Scanner(System.in);
        String line;
        System.out.println("I can keep track of a to-do list for you! Just type what you want to add to the list.");
        while (!"bye".equals((line = scanner.nextLine()))) {
            if ("list".equals(line)) {
                System.out.println("List so far: ");
                for (int i = 0; i < taskCounter; i++) {
                    System.out.println((i + 1) + "." + list[i]);
                }
            } else if (line.startsWith("mark")) {
                Scanner taskScanner = new Scanner(line);
                taskScanner.next();
                if (taskScanner.hasNextInt()) {
                    int taskNumber = taskScanner.nextInt() - 1;
                    if (taskNumber >= 0 && taskNumber < taskCounter) {
                        list[taskNumber].setDone(true);
                        System.out.println("I have marked this task as done:\n" + list[taskNumber]);
                    } else {
                        System.out.println("Invalid task number! Please check with 'list'.");
                    }
                } else {
                    System.out.println("Invalid input! Please check with 'list'.");
                }
            } else if (line.startsWith("unmark")) {
                Scanner taskScanner = new Scanner(line);
                taskScanner.next();
                if (taskScanner.hasNextInt()) {
                    int taskNumber = taskScanner.nextInt() - 1;
                    if (taskNumber >= 0 && taskNumber < taskCounter) {
                        list[taskNumber].setDone(false);
                        System.out.println("I have marked this task as not done:\n" + list[taskNumber]);
                    } else {
                        System.out.println("Invalid task number! Please check with 'list'.");
                    }
                } else {
                    System.out.println("Invalid input! Please check with 'list'.");
                }
            } else if (line.startsWith("deadline")) {
                Scanner taskScanner = new Scanner(line);
                taskScanner.next();
                String remaining = taskScanner.nextLine().trim();
                String[] parts = remaining.split("/by");
                String description = parts[0].trim(); // Trim to remove leading/trailing spaces
                String by = parts[1].trim();
                list[taskCounter] = new Deadline(description, by);
                taskCounter++;
                System.out.println("Added: " + list[taskCounter - 1] + "\nNow you have " + (taskCounter) + " items in the list!");
            } else if (line.startsWith("todo")) {
                Scanner taskScanner = new Scanner(line);
                taskScanner.next();
                String remaining = taskScanner.nextLine().trim();
                list[taskCounter] = new ToDo(remaining);
                taskCounter++;
                System.out.println("Added: " + list[taskCounter - 1] + "\nNow you have " + (taskCounter) + " items in the list!");
            } else if (line.startsWith("event")) {
                Scanner taskScanner = new Scanner(line);
                taskScanner.next();
                String remaining = taskScanner.nextLine().trim();
                remaining = remaining.replace("/to", "/from");
                String[] parts = remaining.split("/from");
                String description = parts[0].trim(); // Trim to remove leading/trailing spaces
                String from = parts[1].trim();
                String to = parts[2].trim();
                list[taskCounter] = new Events(description, from, to);
                taskCounter++;
                System.out.println("Added: " + list[taskCounter - 1] + "\nNow you have " + (taskCounter) + " items in the list!");
            } else {
                list[taskCounter] = new Task(line);
                taskCounter++;
                System.out.println("Added: " + line + "\nNow you have " + (taskCounter) + " items in the list!");
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
