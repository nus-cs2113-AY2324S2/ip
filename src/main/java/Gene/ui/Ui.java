package Gene.ui;

import Gene.GeneException;
import Gene.command.*;
import Gene.parser.Parser;
import Gene.task.TaskList;

import java.util.Scanner;


/**
 * Represents the user interface related functionalities such as prompting for user input
 */
public class Ui {
    private static final String BOT_NAME = "Gene";
    private static final String SEPARATION = "____________________________________________________________";
    private final Scanner scanner = new Scanner(System.in);
    private final TaskList taskList = new TaskList();

    public static void printLineSeparation() {
        System.out.println(SEPARATION);
    }

    /**
     * Prints the greeting message when the user first starts the Gene Bot.
     * Continuously prompt for user input to be parsed as a command.
     */
    public void startChat() {
        printLineSeparation();
        System.out.println("Hello! I'm " + BOT_NAME);
        System.out.println("What can I do for you?");
        System.out.println(System.lineSeparator() + "Tip: type <help> to see a list of commands!");
        printLineSeparation();

        // Read user input in a loop
        while (true) {
            System.out.print("User Input: ");
            String userInput = scanner.nextLine();

            // End chat if user types "bye"
            if (userInput.equalsIgnoreCase("bye")) {
                break;
            }

            try {
                Parser.processCommand(taskList, userInput);
            } catch (GeneException e) {
                System.out.println("ERROR: " + e.getMessage());
                printLineSeparation();
            }
        }
    }


    public void endChat() {
        printLineSeparation();
        System.out.println("Bye. Hope to see you again soon!");
        printLineSeparation();
    }
}
