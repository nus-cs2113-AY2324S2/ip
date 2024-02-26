import java.util.Scanner;

public class Duke {
    public static TaskList tasks;

    public static void main(String[] args) {
        new Duke().run();
    }

    private void run() {
        tasks = new TaskList();

        Ui.showWelcomeMessage();

        // Create a Scanner object to read user input
        Scanner scanner = new Scanner(System.in);
        String userInput;

        // Continue reading user input until "bye" is entered
        while (true) {
            userInput = scanner.nextLine();

            Ui.printLine();
            if (userInput.equalsIgnoreCase("bye")) {
                Storage.saveTasksToFile();
                Ui.showGoodbyeMessage();
                Ui.printLine();
                break;
            } else {
                try {
                    Parser.parseCommand(userInput, tasks);
                } catch (DukeException ignored) {
                }
            }
            Ui.printLine();
        }

        // Close the Scanner
        scanner.close();
    }
}