package duke.parser;

import duke.DukeException;
import duke.command.*;

/**
 * Represents the input parser of the Duke chatbot.
 * Parses user input into command and details of the command.
 * Generates the respective Command objects based on the parsed command keyword.
 */
public class Parser {
    /**
     * Parses user input into command and details, and creates the corresponding Command object.
     *
     * @param input User input to be parsed.
     * @return Command object to be executed.
     * @throws DukeException If the user input is not part of the command list.
     */
    public static Command parse(String input) throws DukeException {
        String[] words = input.split(" ", 2);
        String command = words[0];
        String details = (words.length == 2) ? words[1] : "";
        switch (command) {
        case "bye":
            //exit
            return new ByeCommand();
        case "list":
            //list tasks
            return new ListCommand();
        case "mark":
            //mark task as done
            return new MarkCommand(details);
        case "unmark":
            //mark task as undone
            return new UnmarkCommand(details);
        case "delete":
            //delete task
            return new DeleteCommand(details);
        case "todo":
            //add todo
            return new TodoCommand(details);
        case "deadline":
            //add deadline
            return new DeadlineCommand(details);
        case "event":
            //add event
            return new EventCommand(details);
        default:
            throw new DukeException("ERROR.... \n\t A thousand apologies, but I don't know what that means.");
        }
    }
}
