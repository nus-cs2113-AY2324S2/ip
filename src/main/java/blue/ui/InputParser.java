package blue.ui;

import blue.command.Input;
import blue.command.InputCommand;
import blue.exception.IllegalInput;
import blue.task.Deadline;
import blue.task.Event;
import blue.task.Task;

/**
 * Encapsulates the logic and behaviour of a parser to Blue.
 */
public class InputParser {

    /**
     * Returns a valid request if user text is well-formed, throws an exception otherwise.
     *
     * @param line The string of raw text to parse.
     * @return A valid request for Blue parsed from line.
     * @throws IllegalInput If input is not well-formed.
     */
    public Input parse(String line) throws IllegalInput {
        try {
            InputCommand command = parseRequest(line);
            switch (command) {
            case list:
            case bye:
                return new Input(command);
            case find:
                String taskQuery = parseTaskQuery(line);
                return new Input(command, taskQuery);
            case mark:
            case delete:
                int taskIndex = parseIndex(line, command);
                return new Input(command, taskIndex);
            case todo:
            case deadline:
            case event:
                Task taskToAdd = parseTask(line, command);
                return new Input(command, taskToAdd);
            default:
                throw new IllegalInput();
            }
        } catch (IllegalInput e) {
            throw e;
        }
    }

    /**
     * Returns a request of type InputCommand understandable to Blue
     *
     * @param line The string of user input to parse.
     * @return An enum of InputCommand.
     * @throws IllegalInput If parsed request does not match an enum of InputCommand.
     */
    private InputCommand parseRequest(String line) throws IllegalInput {
        String parsedRequest = line.trim().split(" ")[0];
        switch (parsedRequest) {
        case "list":
            return InputCommand.list;
        case "mark":
            return InputCommand.mark;
        case "delete":
            return InputCommand.delete;
        case "todo":
            return InputCommand.todo;
        case "find":
            return InputCommand.find;
        case "deadline":
            return InputCommand.deadline;
        case "event":
            return InputCommand.event;
        case "bye":
            return InputCommand.bye;
        default:
            throw new IllegalInput();
        }
    }

    /**
     * Returns the index of a task to handle, -1 if no task to handle.
     *
     * @param line The string of user input to parse.
     * @param command User command.
     * @return Index of a task if command is appropriate, -1 otherwise.
     * @throws IllegalInput If parsed index is misformed i.e not an integer.
     */
    private int parseIndex(String line, InputCommand command) throws IllegalInput {
        int startIndex = 0;
        switch (command) {
        case mark:
            startIndex = 4;
            break;
        case delete:
            startIndex = 6;
            break;
        default:
            return -1;
        }
        try {
            int taskIndex = Integer.parseInt(line.substring(startIndex).trim()) - 1;
            return taskIndex;
        } catch (NumberFormatException e) {
            throw new IllegalInput(InputCommand.mark);
        }
    }

    /**
     * Returns a task if details provided by the user are well formed, throws an exception otherwise.
     *
     * @param line The string of user input to parse.
     * @param command User command.
     * @return Task of appropriate type and details parsed from line.
     * @throws IllegalInput If task details are misformed.
     */
    private Task parseTask(String line, InputCommand command) throws IllegalInput {
        String taskDescription;
        try {
            taskDescription = parseTaskDescription(line);
        } catch (IllegalInput e) {
            throw e;
        }
        switch (command) {
        case todo:
            return new Task(taskDescription);
        case deadline:
            try {
                return new Deadline(taskDescription, parseTaskEnd(line));
            } catch (IllegalInput e) {
                throw e;
            }
        case event:
            try {
                return new Event(taskDescription, parseTaskStart(line), parseTaskEnd(line));
            } catch (IllegalInput e) {
                throw e;
            }
        default:
            throw new IllegalInput();
        }
    }

    /**
     * Returns the description of a task if non-empty, throws an exception otherwise.
     *
     * @param line The string of user input to parse.
     * @return String description of task.
     * @throws IllegalInput If description is empty.
     */
    private String parseTaskDescription(String line) throws IllegalInput {
        String taskDescription = line.substring(line.indexOf(" "))
            .split("/by|/from|/to")[0]
            .trim();
        if (taskDescription.length() == 0) {
            throw new IllegalInput();
        }
        return taskDescription;
    }

    /**
     * Returns the deadline or end time of a deadline or event respectively.
     *
     * @param line The string of user input to parse.
     * @return String description of deadline or end time.
     * @throws IllegalInput If description is empty.
     */
    private String parseTaskEnd(String line) throws IllegalInput {
        String taskEnd = line.split("/by|/to")[1].trim();
        if (taskEnd.length() == 0) {
            throw new IllegalInput();
        }
        return taskEnd;
    }

    /**
     * Returns the start time of an event.
     *
     * @param line The string of user input to parse.
     * @return String description of start time.
     * @throws IllegalInput If description is empty.
     */
    private String parseTaskStart(String line) throws IllegalInput {
        String taskStart = line.split("/from|/to")[1].trim();
        if (taskStart.length() == 0) {
            throw new IllegalInput();
        }
        return taskStart;
    }

    /**
     * Returns a string query to tasks.
     *
     * @param line The string of user input to parse.
     * @return A string matching the query parsed from line.
     */
    private String parseTaskQuery(String line) {
        String taskQuery = line.substring(4);
        return taskQuery;
    }
}
