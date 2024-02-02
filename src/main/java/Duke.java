import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static void main(String[] args) {
        String name = "Floda";
        System.out.println("Hello! I'm " + name);
        String[] list = new String[100];
        Boolean[] marked = new Boolean[100];
        Arrays.fill(marked, Boolean.FALSE);
        int taskCounter = 0;
        Scanner scanner = new Scanner(System.in);
        String line;
        System.out.println("I can keep track of a to-do list for you! Just type what you want to add to the list.\nType 'list' to see what is in the list so far.\nType 'mark' and 'unmark' followed by the task number to update its status.\nType 'bye' to exit.");
        while (!"bye".equals((line = scanner.nextLine()))) {
            if ("list".equals(line)) {
                System.out.println("List so far: ");
                for (int i = 0; i < taskCounter; i++) {
                    System.out.println((i + 1) + ". " + getStatusIcon(marked[i]) + " " + list[i]);
                }
            } else if (line.startsWith("mark")) {
                Scanner taskScanner = new Scanner(line);
                taskScanner.next();
                if (taskScanner.hasNextInt()) {
                    int taskNumber = taskScanner.nextInt() - 1;
                    if (taskNumber >= 0 && taskNumber < taskCounter) {
                        marked[taskNumber] = true;
                        System.out.println("I have marked this task as done:\n" + getStatusIcon(marked[taskNumber]) + " " + list[taskNumber]);
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
                        marked[taskNumber] = false;
                        System.out.println("I have marked this task as not done:\n" + getStatusIcon(marked[taskNumber]) + " " + list[taskNumber]);
                    } else {
                        System.out.println("Invalid task number! Please check with 'list'.");
                    }
                } else {
                    System.out.println("Invalid input! Please check with 'list'.");
                }
            } else {
                list[taskCounter] = line;
                taskCounter++;
                System.out.println("Added: " + line);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static String getStatusIcon(boolean marked) {
        return marked ? "[X]" : "[ ]";
    }
}
