import java.util.Scanner;

public class GOne {
    public static void main(String[] args) {
        GOne gOne = new GOne();
        gOne.start();
    }

    public void start() {
        System.out.println("  ________     ________                 \n" +
                " /  _____/     \\_____  \\   ____   ____  \n" +
                "/   \\  ___      /   |   \\ /    \\_/ __ \\ \n" +
                "\\    \\_\\  \\    /    |    \\   |  \\  ___/ \n" +
                " \\______  / /\\ \\_______  /___|  /\\___  >\n" +
                "        \\/  \\/         \\/     \\/     \\/ \n\n");
        System.out.println("Hello! I'm G.one");
        System.out.println("--------------------------------------");

        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;
        TaskManager taskManager = new TaskManager();
        CommandProcessor commandProcessor = new CommandProcessor();

        while (isRunning) {
            System.out.print("Whats up? ");
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("bye")) {
                isRunning = false;
            } else {
                commandProcessor.processUserInput(userInput);
                System.out.print("\n");
            }
        }

        System.out.println("Goodbye!");
        scanner.close();
    }
}
