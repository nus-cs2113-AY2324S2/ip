import carrot.command.Command;
import carrot.parser.Parser;
import carrot.ui.Ui;

import java.util.Scanner;
import java.util.NoSuchElementException;

public class Carrot {
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
