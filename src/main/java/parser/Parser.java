package parser;

import command.*;

import commandexceptions.JingHaoExceptions;
import tasktype.TaskList;

public class Parser {

    public static Command parse(String userInput) throws JingHaoExceptions {

        String[] words = userInput.split(" ", 2);
        String command = words[0].toLowerCase();
        String description = (words.length == 2) ? words[1] : "";
        switch (command) {
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(description);
        case "unmark":
            return new UnmarkCommand(description);
        case "todo":
            return new TodoCommand(description);
        case "deadline":
            return new DeadlineCommand(description);
        case "event":
            return new EventCommand(description);
        case "delete":
            return new DeleteCommand(description);
        case "Bye":
            return new ByeCommand();
        default:
            throw new JingHaoExceptions("Unknown command encountered! Please try again.");
        }
    }

}
