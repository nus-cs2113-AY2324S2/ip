package gab.command;

import gab.exception.GabException;
import gab.parser.Parser;
import gab.task.TaskList;

public class CommandHandler {
    public static Command checkCommand(String task, TaskList taskList) throws GabException {
        String[] taskAction = task.trim().split(" ");
        String action = taskAction[0];

        switch (action) {
        case "bye": {
            return new ByeCommand();
        }
        case "list": {
            return new ListCommand();
        }
        case "todo": {
            return Parser.parseToDo(task);
        }
        case "deadline": {
            return Parser.parseDeadline(task);
        }
        case "event": {
            return Parser.parseEvent(task);
        }
        case "mark": {
            return Parser.markTask(task, taskList);
        }
        case "unmark": {
            return Parser.UnmarkTask(task, taskList);
        }
        case "delete": {
            return Parser.DeleteTask(task, taskList);
        }
        case "find": {
            return Parser.FindTask(task);
        }
        default:
            throw new GabException("Not a valid command");
        }
    }
}

