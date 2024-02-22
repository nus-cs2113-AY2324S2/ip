import java.util.Scanner;

public class Kapwa {
    private static final String DIVIDER_LINE = "____________________________________________________________";
    private static TaskManager taskManager = new TaskManager(100);

    public static void main(String[] args) throws KapwaException {
        Scanner scanner = null;
        try {
            displayWelcomeMessage();
            scanner = new Scanner(System.in);
            String inputLine;

            while (true) {
                inputLine = scanner.nextLine().trim();
                System.out.println(DIVIDER_LINE);
                if ("bye".equals(inputLine)) {
                    break;
                } else if ("list".equals(inputLine)) {
                    taskManager.displayTaskList();
                } else if (inputLine.startsWith("mark ")) {
                    try {
                        int taskNumber = Integer.parseInt(inputLine.replaceAll("\\D+", ""));
                        taskManager.markTask(taskNumber, true);
                    } catch (NumberFormatException e) {
                        throw new KapwaException(
                                "Invalid task number format for marking a task. Please enter a valid number.");
                    }
                } else if (inputLine.startsWith("unmark ")) {
                    try {
                        int taskNumber = Integer.parseInt(inputLine.replaceAll("\\D+", ""));
                        taskManager.markTask(taskNumber, false);
                    } catch (NumberFormatException e) {
                        throw new KapwaException(
                                "Invalid task number format for unmarking a task. Please enter a valid number.");
                    }
                } else {
                    try {
                        taskManager.addTask(inputLine);
                    } catch (KapwaException e) {
                        // Print the error message from addTask and throw a new exception to halt
                        // execution
                        System.out.println(e.getMessage());
                        throw new KapwaException("Failed to add task: " + e.getMessage());
                    }
                }
                System.out.println(DIVIDER_LINE);
            }

            System.out.println("Bye. Hope to see you again soon!");
        } catch (KapwaException e) {

            System.out.println("An error occurred: " + e.getMessage());
            
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    private static void displayWelcomeMessage() {
        String logo = " _  __    _    ____  __        __    _    _ \n"
                + "| |/ /   / \\  |  _ \\ \\ \\      / /   / \\  | |\n"
                + "| ' /   / _ \\ | |_) | \\ \\ /\\ / /   / _ \\ | |\n"
                + "| . \\  / ___ \\|  __/   \\ V  V /   / ___ \\|_|\n"
                + "|_|\\_\\/_/   \\_\\_|       \\_/\\_/   /_/   \\_(_)\n";

        System.out.println("Hello from Kapwa\n" + logo +
                "Hello! I'm Kapwa\n" +
                "What can I do for you?\n" +
                DIVIDER_LINE);
    }
}
