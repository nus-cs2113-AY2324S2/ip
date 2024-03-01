package Blue;

/**
 * Encapsulates the logic and behaviour of a parser to Blue.
 */
public class InputParser {
    private Input parsedInput;

    public Input getParsedInput() {
        return parsedInput;
    }

    /**
     * Parses a well-formed input regardless of whether user input is well-formed.
     *
     * @param line The string of user input to parse.
     */
    public void parse(String line) {
        InputCommand command = InputCommand.undefined;
        try {
            command = parseRequest(line);
            int taskIndex = parseIndex(line, command);
            Task taskToAdd = parseTask(line, command);
            String taskQuery = parseTaskQuery(line, command);
            parsedInput = new Input(command, taskIndex, taskToAdd, taskQuery);
        } catch (IllegalInput e) {
            command = InputCommand.undefined;
            String errorMessage = e.getMessage();
            parsedInput = new Input(errorMessage);
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
        switch (command) {
        case mark:
            try {
                int taskIndex = Integer.parseInt(line.substring(4).trim()) - 1;
                return taskIndex;
            } catch (NumberFormatException e) {
                throw new IllegalInput(InputCommand.mark);
            }
        case delete:
            try {
                int taskIndex = Integer.parseInt(line.substring(6).trim()) - 1;
                return taskIndex;
            } catch (NumberFormatException e) {
                throw new IllegalInput(InputCommand.mark);
            }
        default:
            return -1;
        }
    }

    /**
     * Returns a task with details provided by the user.
     *
     * @param line The string of user input to parse.
     * @param command User command.
     * @return Task of appropriate type and details parsed from line.
     * @throws IllegalInput If task is misformed.
     */
    private Task parseTask(String line, InputCommand command) throws IllegalInput {
        String taskDescription;
        String taskDeadline;
        String taskStart;
        switch (command) {
        case todo:
            taskDescription = line.substring(4).trim();
            return new Task(taskDescription);
        case deadline:
            String[] TaskDetails = line.substring(8).split("/by");
            taskDescription = TaskDetails[0].trim();
            taskDeadline = TaskDetails[1].trim();
            return new Deadline(taskDescription, taskDeadline);
        case event:
            String[] eventDetails = line.substring(5).split("/from|/to");
            taskDescription = eventDetails[0].trim();
            taskStart = eventDetails[1].trim();
            taskDeadline = eventDetails[2].trim();
            return new Event(taskDescription, taskStart, taskDeadline);
        default:
            return new Task();
        }
    }

    /**
     * Returns a string query to tasks.
     *
     * @param line The string of user input to parse.
     * @param command User command.
     * @return A string matching the query parsed from line.
     */
    private String parseTaskQuery(String line, InputCommand command) {
        String taskQuery = "";
        switch (command) {
            case find:
                taskQuery = line.substring(4).trim();
                return taskQuery;
            default:
                return taskQuery;
        }
    }
}
