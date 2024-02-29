import carrot.command.Command;
import carrot.parser.Parser;
import carrot.ui.Ui;

import java.util.Scanner;
import java.util.NoSuchElementException;

public class Carrot {

    /**
     * The entry point of the Carrot (Chatbot) application.
     * <p>
     * This method initializes the application, greets the user, and provides initial instructions.
     * <p>
     * It then enters a loop to continuously process user commands until the application is terminated.
     * @param args the command-line arguments (not used in this application)
     */
    public static void main(String[] args) {
        Ui.greetUser();
        Ui.printHelpCommand();

        while (true) {
            try {
                String userInput = Ui.readUserInput();

                Command command = Parser.getCommandType(userInput);
                command.execute(userInput);
            } catch (NoSuchElementException e) {
                Ui.printScannerInputError();
                Ui.getScanner().close();
                break;
            }
        }
    }
}
