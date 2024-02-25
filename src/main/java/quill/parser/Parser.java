package quill.parser;

import quill.command.*;
import quill.exception.EmptyDateException;
import quill.exception.QuillException;
import quill.storage.Save;
import quill.task.Deadline;
import quill.task.Event;
import quill.task.Task;
import quill.task.Todo;
import quill.ui.TextUi;

public class Parser {

    public static Command parse(String input) {
        String command;
        int index = input.indexOf(" ");
        if (index != -1) {
            command = input.substring(0, index);
            input = input.substring(index + 1);
        } else {
            command = input;
            input = "";
        }
        Command c = null;
        switch(command) {
        case "bye":
            c = new ExitCommand(command, input);
            break;
        case "list":
            c = new ListCommand(command, input);
            break;
        case "mark":
        case "unmark":
            c = new MarkCommand(command, input);
            break;
        case "todo":
        case "deadline":
        case "event":
            c = new AddCommand(command, input);
            break;
        case "delete":
            c = new DeleteCommand(command, input);
            break;
        case "find":
            c = new FindCommand(command, input);
            break;
        default:
            c= new UnknownCommand(command, input);
            break;
        }
        return c;
    }
}
