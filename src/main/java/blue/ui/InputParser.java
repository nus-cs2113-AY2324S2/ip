package blue.ui;

import blue.command.Input;
import blue.command.InputCommand;
import blue.exception.IllegalInputException;
import blue.exception.IllegalIndexException;
import blue.exception.IllegalTaskException;
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
     * @throws IllegalInputException If input is not well-formed.
     */
    public Input parse(String line) throws IllegalInputException {
        try {
            InputCommand command = parseRequest(line);
            switch (command) {
            case bye:
                return new Input(command);
            case list:
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
                throw new IllegalInputException();
            }
        } catch (IllegalInputException e) {
            throw e;
        }
    }

    /**
     * Returns a request of type InputCommand understandable to Blue
     *
     * @param line The string of user input to parse.
     * @return An enum of InputCommand.
     * @throws IllegalInputException If parsed request does not match an enum of InputCommand.
     */
    private InputCommand parseRequest(String line) throws IllegalInputException {
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
            throw new IllegalInputException();
        }
    }

    /**
     * Returns the index of a task if found, throws an exception otherwise.
     *
     * @param line The string of user input to parse.
     * @param command User command.
     * @return Index of a task if found.
     * @throws IllegalIndexException If parsed index is not an integer or not found.
     */
    private int parseIndex(String line, InputCommand command) throws IllegalIndexException {
        try {
            int taskIndex = Integer.parseInt(line.substring(line.indexOf(" ")).trim()) - 1;
            return taskIndex;
        } catch (NumberFormatException e) {
            throw new IllegalIndexException();
        }
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

    /**
     * Returns a task if details provided by the user are well formed, throws an exception otherwise.
     *
     * @param line The string of user input to parse.
     * @param command User command, in effect task type.
     * @return Task of appropriate type and details parsed from line.
     * @throws IllegalTaskException If task details are misformed.
     */
    private Task parseTask(String line, InputCommand command) throws IllegalTaskException {
        try {
            String taskDescription = parseTaskDescription(line);
            if (command == InputCommand.todo) {
                return new Task(taskDescription);
            }
            String taskEnd = parseTaskEnd(line);
            if (command == InputCommand.deadline) {
                return new Deadline(taskDescription, taskEnd);
            }
            String taskStart = parseTaskStart(line);
            return new Event(taskDescription, taskStart, taskEnd);
        } catch (IllegalTaskException e) {
            throw e;
        }
    }

    /**
     * Returns the description of a task if non-empty, throws an exception otherwise.
     *
     * @param line The string of user input to parse.
     * @return String description of task.
     * @throws IllegalTaskException If description is empty.
     */
    private String parseTaskDescription(String line) throws IllegalTaskException {
        String taskDescription = line.substring(line.indexOf(" "))
            .split("/by|/from|/to")[0]
            .trim();
        if (taskDescription.length() == 0) {
            throw new IllegalTaskException("Description cannot be empty.");
        }
        return taskDescription;
    }

    /**
     * Returns the deadline or end time of a deadline or event respectively.
     *
     * @param line The string of user input to parse.
     * @return String description of deadline or end time.
     * @throws IllegalTaskException If description is empty.
     */
    private String parseTaskEnd(String line) throws IllegalTaskException {
        String taskEnd = line.split("/by|/to")[1].trim();
        if (taskEnd.length() == 0) {
            throw new IllegalTaskException("No deadline/ end time specified.");
        }
        return taskEnd;
    }

    /**
     * Returns the start time of an event.
     *
     * @param line The string of user input to parse.
     * @return String description of start time.
     * @throws IllegalTaskException If description is empty.
     */
    private String parseTaskStart(String line) throws IllegalTaskException {
        String taskStart = line.split("/from|/to")[1].trim();
        if (taskStart.length() == 0) {
            throw new IllegalTaskException("No start time specified.");
        }
        return taskStart;
    }
}
