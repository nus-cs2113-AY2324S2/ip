package duke.parser;

import duke.DukeException;
import duke.command.*;

public class Parser {

    public static Command parse(String input) throws DukeException {
        String[] words = input.split(" ", 2);
        String command = words[0];
        String description = (words.length == 2) ? words[1] : "";
        switch (command) {
        case "bye":
            //exit
            return new ByeCommand();
        case "list":
            //list tasks
            return new ListCommand();
        case "mark":
            //mark task as done
            return new MarkCommand(description);
        case "unmark":
            //mark task as undone
            return new UnmarkCommand(description);
        case "delete":
            //delete task
            return new DeleteCommand(description);
        case "todo":
            //add todo
            return new TodoCommand(description);
        case "deadline":
            //add deadline
            return new DeadlineCommand(description);
        case "event":
            //add event
            return new EventCommand(description);
        case "find":
            //find tasks
            return new FindCommand(description);
        default:
            throw new DukeException("ERROR.... \n\t A thousand apologies, but I don't know what that means.");
        }
    }
}
