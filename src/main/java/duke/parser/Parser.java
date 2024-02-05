package duke.parser;

import duke.DukeException;
import duke.command.ListCommand;
import duke.command.Command;
import duke.command.ByeCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.command.TodoCommand;
import duke.command.DeadlineCommand;
import duke.command.EventCommand;

public class Parser {

    public static Command parse(String input) throws DukeException {
        String[] words = input.split(" ", 2);
        String command = words[0];
        String description = (words.length == 2) ? words[1] : "";
        switch (command) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(description);
        case "unmark":
            return new UnmarkCommand(description);
        case "todo":
            //add todo
            return new TodoCommand(description);
        case "deadline":
            //add deadline
            return new DeadlineCommand(description);
        case "event":
            //add event
            return new EventCommand(description);
        default:
            throw new DukeException("ERROR.... \n\t A thousand apologies, but I don't know what that means.");
        }
    }
}
