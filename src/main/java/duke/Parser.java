package duke;

import java.util.Arrays;
import java.util.List;

public class Parser {
    public static final String[] validCommands =
            {"list", "mark", "unmark", "todo", "deadline", "event", "bye", "delete", "find"};
    protected TaskList taskList;

    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    public List<Task> getList() {
        return taskList.getTaskList();
    }

    public void executeCommand(String command, String argument)
            throws MissingParamsException, DukeException.EndListException,
            DukeException.InvalidCommandException, DukeException.InvalidIntegerException,
            DukeException.IntegerOutOfBoundsException {
        if (command.equalsIgnoreCase("bye")) {
            throw new DukeException.EndListException();
        }

        if (Arrays.stream(validCommands).noneMatch(command::equals)){
            throw new DukeException.InvalidCommandException();
        }

        switch (command) {
        case "list":
            String message = "";
            taskList.printList(message);
            break;
        case "mark":
            taskList.markTask(argument, true);
            break;
        case "unmark":
            taskList.markTask(argument, false);
            break;
        case "todo":
            taskList.addToDo(argument);
            break;
        case "deadline":
            taskList.addDeadline(argument);
            break;
        case "event":
            taskList.addEvent(argument);
            break;
        case "delete":
            taskList.deleteTask(argument);
            break;
        case "find":
            taskList.findTask(argument);
            break;
        }
    }
}
