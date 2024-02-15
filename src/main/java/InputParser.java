public class InputParser extends Blue {
    private InputCommand command;
    private Task taskToAdd;
    private int taskIndex;

    public InputCommand getCommand() {
        return command;
    }

    public Task getTaskToAdd() {
        return taskToAdd;
    }

    public int getTaskIndex() {
        return taskIndex;
    }

    private boolean isInvalidRequest(String[] requestDetails) {
        String request = requestDetails[0];
        switch (request) {
        case "mark":
        case "todo":
        case "deadline":
        case "event":
        }
        return false;
    }
    public String[] parseLine(String line) throws IllegalInput {
        line = line.trim();
        String request = line.split(" ")[0];
        String[] arguments = new String[4];
        arguments[0] = request;
        switch (request) {
        case "list":
            command = InputCommand.list;
            break;
        case "mark":
            command = InputCommand.mark;
            String taskIndex = line.substring(5).trim();
            arguments[1] = taskIndex;
            break;
        case "todo":
            command = InputCommand.todo;
            String description = line.substring(4).trim();
            arguments[1] = description;
            break;
        case "deadline":
            command = InputCommand.deadline;
            String[] TaskDetails = line.substring(8).split("/by");
            description = TaskDetails[0].trim();
            String by = TaskDetails[1].trim();
            arguments[1] = description;
            arguments[2] = by;
            break;
        case "event":
            command = InputCommand.event;
            String[] eventDetails = line.substring(5).split("/from|/to");
            description = eventDetails[0].trim();
            by = eventDetails[2].trim();
            String from = eventDetails[1].trim();
            arguments[1] = description;
            arguments[2] = by;
            arguments[3] = from;
            break;
        default:
            command = InputCommand.undefined;
            throw new IllegalInput();
        }
        if (isInvalidRequest(arguments)) {
            throw new IllegalInput();
        }
        return arguments;
    }
}