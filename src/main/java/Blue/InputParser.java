package Blue;

public class InputParser extends Blue {
    private InputCommand command;
    private int taskIndex;
    private Task taskToAdd;

    public InputCommand getCommand() {
        return command;
    }
    public int getTaskIndex() {
        return taskIndex;
    }
    public Task getTaskToAdd() {
        return taskToAdd;
    }

    InputParser(String line) {
        try {
            parseRequest(line);
            parseTask(line);
        } catch (IllegalInput e) {
            talk(String.valueOf(e));
            command = InputCommand.undefined;
        }
    }

    private void parseRequest(String line) throws IllegalInput {
        String parsedRequest = line.trim().split(" ")[0];
        switch (parsedRequest) {
        case "list":
            command = InputCommand.list;
            break;
        case "mark":
            command = InputCommand.mark;
            try {
                taskIndex = Integer.parseInt(line.substring(4).trim()) - 1;
            } catch (NumberFormatException e) {
                throw new IllegalInput(InputCommand.mark);
            }
            break;
        case "todo":
            command = InputCommand.todo;
            break;
        case "deadline":
            command = InputCommand.deadline;
            break;
        case "event":
            command = InputCommand.event;
            break;
        default:
            throw new IllegalInput();
        }
    }

    private void parseTask(String line) throws IllegalInput {
        String taskDescription;
        String taskDeadline;
        String taskStart;
        switch (command) {
        case todo:
            taskDescription = line.substring(4).trim();
            taskToAdd = new Task(taskDescription);
            break;
        case deadline:
            String[] TaskDetails = line.substring(8).split("/by");
            taskDescription = TaskDetails[0].trim();
            taskDeadline = TaskDetails[1].trim();
            taskToAdd = new Deadline(taskDescription, taskDeadline);
            break;
        case event:
            String[] eventDetails = line.substring(5).split("/from|/to");
            taskDescription = eventDetails[0].trim();
            taskStart = eventDetails[1].trim();
            taskDeadline = eventDetails[2].trim();
            taskToAdd = new Event(taskDescription, taskStart, taskDeadline);
            break;
        }
    }
}