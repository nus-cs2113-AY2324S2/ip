package Blue;

public class InputParser extends Blue {
    private Input parsedInput;

    public Input getParsedInput() {
        return parsedInput;
    }

    public void parse(String line) {
        InputCommand command = InputCommand.undefined;
        int taskIndex = -1;
        Task taskToAdd = new Task();
        try {
            command = parseRequest(line);
            taskIndex = parseIndex(line, command);
            taskToAdd = parseTask(line, command);
        } catch (IllegalInput e) {
            command = InputCommand.undefined;
            talk(String.valueOf(e));
        } finally {
            parsedInput = new Input(command, taskIndex, taskToAdd);
        }
    }

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
}
