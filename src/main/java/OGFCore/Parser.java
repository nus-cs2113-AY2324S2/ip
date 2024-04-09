package OGFCore;
import OGFCommand.*;
import OGFTask.*;
/**
 * Class that holds the parse method
 */
public class Parser {
    /**
     * @param input String input that user entered into the CLI
     * @return OGFCommand.Command to be executed later
     * @throws OGFException Exception to be handled in main body
     */
    public static Command parse(String input) throws OGFException {
        try {
            switch (input.split(" ")[0]) {
            case ("bye"):
                return new ByeCommand();
            case ("list"):
                return new ListCommand();
            case ("delete"):
                return handleDelete(input);
            case ("mark"):
                return handleMark(input);
            case ("unmark"):
                return handleUnmark(input);
            case ("find"):
                return handleFind(input);
            case("todo"):
            case("deadline"):
            case("event"):
                return handleTask(input);
            default:
                throw new OGFException("I don't recognise this input, try again!", false);
            }
        } catch (NumberFormatException error) {
            throw new OGFException("Looks like the input you entered was not an integer: " + input, false);
        }
    }

    /**
     * Creates Delete command from string input
     * @param input Input from user
     * @return OGFCommand.DeleteCommand, with task to delete
     * @throws OGFException For all errors
     */
    private static DeleteCommand handleDelete(String input) throws OGFException {
        if (!input.contains(" ")) {
            throw new OGFException("Did not indicate task to delete", false);
        }
        int taskToDelete = Integer.parseInt(input.split(" ")[1]) - 1;
        return new DeleteCommand(taskToDelete);
    }
    /**
     * Creates Mark command from string input
     * @param input Input from user
     * @return OGFCommand.UpdateCommand, with task to mark
     * @throws OGFException For all errors
     */
    private static UpdateCommand handleMark(String input) throws OGFException {
        if (!input.contains(" ")) {
            throw new OGFException("Did not indicate task to mark", false);
        }
        int taskToMark = Integer.parseInt(input.split(" ")[1]) - 1;
        return new UpdateCommand(taskToMark, true);
    }
    /**
     * Creates Unmark command from string input
     * @param input Input from user
     * @return OGFCommand.UpdateCommand, with task to unmark
     * @throws OGFException For all errors
     */
    private static UpdateCommand handleUnmark(String input) throws OGFException {
        if (!input.contains(" ")) {
            throw new OGFException("Did not indicate task to unmark", false);
        }
        int taskToUnmark = Integer.parseInt(input.split(" ")[1]) - 1;
        return new UpdateCommand(taskToUnmark, false);
    }
    /**
     * Creates Find command from string input
     * @param input Input from user
     * @return OGFCommand.FindCommand, with search term
     * @throws OGFException For all errors
     */
    private static FindCommand handleFind(String input) throws OGFException {
        if (!input.contains(" ")) {
            throw new OGFException("Did not indicate task to search", false);
        }
        return new FindCommand(input.split(" ")[1]);
    }
    /**
     * Creates Add command from string input
     * @param input Input from user
     * @return OGFCommand.AddCommand, with task to add to the list
     * @throws OGFException For all errors
     */
    private static AddCommand handleTask(String input) throws OGFException {
        switch (input.split(" ")[0]) {
        case ("todo"):
            if (!input.contains(" ") || input.indexOf(" ") == input.length() - 1) {
                throw new OGFException("Did not write anything after \"todo\", dont waste my time and yours pls", false);
            }
            String newTodoDesc = input.substring(input.indexOf(" ") + 1);
            return new AddCommand(new Todo(newTodoDesc));
        case ("deadline"):
            if (!input.contains(" ")) {
                throw new OGFException("Did not write anything after \"deadline\", dont waste my time and yours pls", false);
            }
            if (!input.contains(" /by ")) {
                throw new OGFException("Did not enter deadline, use /by, followed by your deadline to add a deadline to your deadline", false);
            }
            String newDeadlineDesc = input.substring(input.indexOf(" ") + 1, input.indexOf(" /by"));
            String newDeadlineTime = input.substring(input.indexOf("/by") + 4);
            return new AddCommand(new Deadline(newDeadlineDesc, newDeadlineTime));
        case ("event"):
            if (!input.contains(" ")) {
                throw new OGFException("Did not write anything after \"event\", dont waste my time and yours pls", false);
            }
            if (!input.contains(" /from ") || !input.contains(" /to ")) {
                throw new OGFException("Did not enter event start and end, use /from and /to to enter start and end time respectively", false);
            }
            String newEventDesc = input.substring(input.indexOf(" ") + 1, input.indexOf(" /from"));
            String newEventStart = input.substring(input.indexOf("/from") + 6, input.indexOf(" /to"));
            String newEventEnd = input.substring(input.indexOf("/to") + 4);
            return new AddCommand(new Event(newEventDesc, newEventStart, newEventEnd));
        default:
            throw new OGFException("Something horribly wrong has happened", false);
        }
    }
}

