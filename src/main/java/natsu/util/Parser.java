package natsu.util;

import natsu.command.AddDeadlineCommand;
import natsu.command.AddEventCommand;
import natsu.command.AddTodoCommand;
import natsu.command.ByeCommand;
import natsu.command.DeleteCommand;
import natsu.command.FindCommand;
import natsu.command.ListCommand;
import natsu.command.MarkCommand;
import natsu.command.UnmarkCommand;
import natsu.exception.InvalidCommandException;

import static natsu.util.Ui.printLine;
import static natsu.util.Storage.saveTasksToFile;

public class Parser {
    public static Boolean executeCommand(String userInput) {
        try {
            if (userInput.startsWith("bye")) {
                new ByeCommand();
                return false;
            } else if (userInput.startsWith("todo")) {
                new AddTodoCommand(userInput);
            } else if (userInput.startsWith("deadline")) {
                new AddDeadlineCommand(userInput);
            } else if (userInput.startsWith("event")) {
                new AddEventCommand(userInput);
            } else if (userInput.startsWith("mark")) {
                new MarkCommand(userInput);
            } else if (userInput.startsWith("unmark")) {
                new UnmarkCommand(userInput);
            } else if (userInput.startsWith("list")) {
                new ListCommand();
            } else if (userInput.startsWith("delete")) {
                new DeleteCommand(userInput);
            } else if (userInput.startsWith("find")) {
                new FindCommand(userInput);
            }
            else {
                throw new InvalidCommandException("     I'm terribly sorry, but I do not know what that means. Please try again!");
            }
        } catch (InvalidCommandException e) {
            printLine();
            System.out.println(e.getMessage());
            printLine();
        }
        saveTasksToFile("data/tasks.txt");
        return true;
    }
}
