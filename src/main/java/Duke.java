import java.util.Scanner;

public class Duke {
    public static TaskList tasks;

    public static void main(String[] args) {
        new Duke().run();
    }

    private void run() {
        tasks = new TaskList();

        Ui.showWelcomeMessage();

        Scanner scanner = new Scanner(System.in);
        String userInput;

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

        scanner.close();
    }
}