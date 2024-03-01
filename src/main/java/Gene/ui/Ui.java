package Gene.ui;

import Gene.GeneException;
import Gene.command.*;
import Gene.parser.Parser;
import Gene.task.TaskList;

import java.util.Scanner;

public class Ui {
    private static final String BOT_NAME = "Gene";
    private static final String SEPARATION = "____________________________________________________________";
    private final Scanner scanner = new Scanner(System.in);
    private final TaskList taskList = new TaskList();

    public static void printLineSeparation() {
        System.out.println(SEPARATION);
    }

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
