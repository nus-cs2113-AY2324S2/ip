import java.util.Scanner;

public class Kapwa {
    private static final String DIVIDER_LINE = "____________________________________________________________";
    private static TaskManager taskManager = new TaskManager(100);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        displayWelcomeMessage();
        String inputLine;

        while (true) {
            inputLine = scanner.nextLine().trim();
            try {
                System.out.println(DIVIDER_LINE);
                if ("bye".equals(inputLine)) {
                    break;
                } else if ("list".equals(inputLine)) {
                    taskManager.displayTaskList();
                } else if (inputLine.startsWith("mark ")) {
                    int taskNumber = Integer.parseInt(inputLine.replaceAll("\\D+", ""));
                    taskManager.markTask(taskNumber, true);
                } else if (inputLine.startsWith("unmark ")) {
                    int taskNumber = Integer.parseInt(inputLine.replaceAll("\\D+", ""));
                    taskManager.markTask(taskNumber, false);
                } else {
                    taskManager.addTask(inputLine);
                }
            } catch (KapwaException e) {
                System.out.println("An error occurred: " + e.getMessage());
            } finally {
                System.out.println(DIVIDER_LINE);
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
        scanner.close();
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
