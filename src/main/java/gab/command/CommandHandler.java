package gab.command;

import gab.exception.GabException;
import gab.parser.Parser;
import gab.task.TaskList;

/**
 * Class to check each task action and calls its respective methods
 */
public class CommandHandler {

    /**
     * Parse task action from user input
     * Search through cases and implement respective methods
     *
     * @param task task input containing task action
     * @param taskList arraylist of task
     * @return respective methods based on the task action
     * @throws GabException thrown when command is not valid
     */
    public static Command checkCommand(String task, TaskList taskList) throws GabException {
        String[] taskAction = task.trim().split(" ");
        String action = taskAction[0];

        switch (action) {
        case "bye": {
            return Parser.exitBot(task);
        }
        case "list": {
            return Parser.listTask(task);
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

