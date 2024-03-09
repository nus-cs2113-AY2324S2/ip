import java.util.Scanner;

public class Hailey {
    private static final String LINE_SEPARATOR = "____________________________________________________________";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n"
                + "What can I help you with？";

        System.out.println("Hello from\n" + logo);
        printLine();

        String userInput;
        do {
            System.out.println("Hello! I'm Hailey\nWhat can I do for you?");
            printLine();

            userInput = scanner.nextLine();
            printLine();

            if (!"bye".equalsIgnoreCase(userInput)) {
                System.out.println(userInput);
                printLine();
            }
        } while (!"bye".equalsIgnoreCase(userInput));

        System.out.println("Bye. Hope to see you again soon!");
        printLine();

        scanner.close();
    }

    private static void printLine() {
        System.out.println(LINE_SEPARATOR);
    }
}

