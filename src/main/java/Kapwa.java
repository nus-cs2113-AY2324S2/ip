import java.util.Scanner;

public class Kapwa {
    private static final String DIVIDER_LINE = "____________________________________________________________";
    private static TaskManager taskManager = new TaskManager(100);

    public static void main(String[] args) {
        displayWelcomeMessage();

        Scanner scanner = new Scanner(System.in);
        String inputLine;

        do {
            inputLine = scanner.nextLine().trim();
            System.out.println(DIVIDER_LINE);
            if ("bye".equals(inputLine)) {
                break;
            } else if ("list".equals(inputLine)) {
                taskManager.displayTaskList();
            } else if (inputLine.startsWith("mark ") || inputLine.startsWith("unmark ")) {
                int taskNumber = Integer.parseInt(inputLine.replaceAll("\\D", ""));
                taskManager.markTask(taskNumber, inputLine.startsWith("mark "));
            } else {
                taskManager.addTask(inputLine);
            }
            System.out.println(DIVIDER_LINE);
        } while (true);

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
