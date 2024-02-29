package magus;

import magus.console.Command;
import magus.console.CommandManager;
import magus.console.Console;
import magus.console.Parser;
import magus.exception.CommandNotFoundException;
import magus.exception.UnknownArgumentException;
import magus.task.TaskManager;

public class Magus {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Console.printWelcomeMessage();
        boolean isExitProgram = false;

        while (!isExitProgram) {
            String input = Console.getUserInput();
            Parser parser = new Parser(input);
            try {
                CommandManager.processInput(parser, taskManager);
            } catch (CommandNotFoundException | UnknownArgumentException e) {
                String errorScope = "MAGUS";
                Console.printError(errorScope, e);
            }

            // Exit when bye command issued
            isExitProgram = parser.getCommand() == Command.BYE;
        }
    }
}
