package natsu.util;

import natsu.command.AddDeadlineCommand;
import natsu.command.AddEventCommand;
import natsu.command.AddTodoCommand;
import natsu.command.ByeCommand;
import natsu.command.DeleteCommand;
import natsu.command.ListCommand;
import natsu.command.MarkCommand;
import natsu.command.UnmarkCommand;
import natsu.exception.InvalidCommandException;

import static natsu.util.Ui.printLine;
import static natsu.util.Storage.saveTasksToFile;

/**
 * The {@code Parser} class is responsible for parsing user input and executing the corresponding command.
 * It supports a range of commands including adding tasks (todos, deadlines, events), marking tasks as done or not done,
 * listing all tasks, deleting tasks, and exiting the application.
 */
public class Parser {

    /**
     * Parses the given user input and executes the corresponding command.
     * This method identifies the command type from the user input and creates an instance
     * of the relevant command class to perform the action. It also handles any exceptions
     * thrown by invalid commands and saves the current state of tasks to a file.
     *
     * @param userInput The full command input by the user.
     * @return A {@code Boolean} indicating whether the application should continue running.
     * Returns {@code false} if the 'bye' command is executed, signalling the application to terminate;
     * otherwise returns {@code true}.
     */
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
            } else {
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
