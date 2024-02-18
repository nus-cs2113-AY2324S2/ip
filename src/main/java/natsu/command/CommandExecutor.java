package natsu.command;

import natsu.exception.InvalidCommandException;

import static natsu.util.TaskManager.list;
import static natsu.util.TaskManager.taskCount;
import static natsu.util.Printer.printLine;
import static natsu.util.TaskSaver.saveTasksToFile;

public class CommandExecutor {
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
                new ListCommand(list, taskCount);
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
