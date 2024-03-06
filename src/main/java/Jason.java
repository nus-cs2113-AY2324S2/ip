
import java.util.Scanner;

import jason.errorhandling.JasonException;


public class Jason {
    private static final TaskList taskList = new TaskList();


    public static void main(String[] args) {

        try {
            Storage.load(taskList); // Use Storage to load tasks


        } catch (JasonException e) {
            Ui.showError("Failed to load tasks: " + e.getMessage());
        }

        Ui.showWelcome();

        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            input = Ui.readCommand();

            if (input.equalsIgnoreCase("bye")) {
                try {
                    Storage.save(taskList.getTasks());
                } catch (JasonException e) {
                    Ui.showError("Failed to save tasks: " + e.getMessage());
                }
                Ui.showGoodbye();
                break;
            }

            Parser.executeCommand(input, taskList);
        }

        scanner.close();
    }
}
