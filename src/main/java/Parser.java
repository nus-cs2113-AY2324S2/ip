import java.util.ArrayList;

public class Parser {
    public static void parseUserCommand(String userCommand, TaskList taskList, Ui ui) {
        String[] parts = userCommand.split(" ", 2);
        String commandWord = parts[0].toLowerCase();

        try {
            switch (commandWord) {
                case "bye":
                    ui.showByeMessage();
                    break;
                case "list":
                    ui.showTaskList(taskList.getTasks());
                    break;
                case "todo":
                    parseTodoCommand(parts, taskList, ui);
                    break;
                case "deadline":
                    parseDeadlineCommand(parts, taskList, ui);
                    break;
                case "event":
                    parseEventCommand(parts, taskList, ui);
                    break;
                case "mark":
                    parseMarkCommand(parts, taskList, ui);
                    break;
                case "unmark":
                    parseUnmarkCommand(parts, taskList, ui);
                    break;
                case "delete":
                    parseDeleteCommand(parts, taskList, ui);
                    break;
                case "find":
                    parseFindCommand(parts, taskList, ui);
                    break;
                default:
                    ui.showError("Invalid command. Please enter a valid command.");
            }
        } catch (JoeyException e) {
            ui.showError(e.getMessage());
        }
    }

    private static void parseTodoCommand(String[] parts, TaskList taskList, Ui ui) throws JoeyException {
        if (parts.length < 2) {
            throw new JoeyException("The description of a todo cannot be empty.");
        }
        String description = parts[1].trim();
        taskList.addTask(new Todo(description, false));
        ui.showTaskAdded(taskList.getTasks().get(taskList.getTasks().size() - 1), taskList.getTasks().size());
    }

    private static void parseDeadlineCommand(String[] parts, TaskList taskList, Ui ui) throws JoeyException {
        if (parts.length < 2) {
            throw new JoeyException("Please provide both description and deadline for the deadline task.");
        }

        String[] deadlineParts = parts[1].split("/by", 2);
        if (deadlineParts.length < 2) {
            throw new JoeyException("Invalid deadline format.");
        }

        String description = deadlineParts[0].trim();
        String by = deadlineParts[1].trim();
        taskList.addTask(new Deadline(description, by, false));
        ui.showTaskAdded(taskList.getTasks().get(taskList.getTasks().size() - 1), taskList.getTasks().size());
    }

    private static void parseEventCommand(String[] parts, TaskList taskList, Ui ui) throws JoeyException {
        if (parts.length < 2) {
            throw new JoeyException("Please provide both description, start time, and end time for the event task.");
        }

        String[] eventParts = parts[1].split("/from", 2);
        if (eventParts.length < 2) {
            throw new JoeyException("Invalid event format.");
        }

        String description = eventParts[0].trim();
        String[] timingParts = eventParts[1].split("/to", 2);

        if (timingParts.length < 2) {
            throw new JoeyException("Invalid event format.");
        }

        String from = timingParts[0].trim();
        String to = timingParts[1].trim();
        taskList.addTask(new Event(description, from, to, false));
        ui.showTaskAdded(taskList.getTasks().get(taskList.getTasks().size() - 1), taskList.getTasks().size());
    }

    private static void parseMarkCommand(String[] parts, TaskList taskList, Ui ui) throws JoeyException {
        if (parts.length < 2) {
            throw new JoeyException("Please provide a valid task number to mark as done.");
        }

        try {
            int taskIndex = Integer.parseInt(parts[1]) - 1;
            taskList.markTaskDone(taskIndex);
            ui.showTaskList(taskList.getTasks());
        } catch (NumberFormatException e) {
            throw new JoeyException("Please enter a valid task number to mark as done.");
        }
    }

    private static void parseUnmarkCommand(String[] parts, TaskList taskList, Ui ui) throws JoeyException {
        if (parts.length < 2) {
            throw new JoeyException("Please provide a valid task number to mark as not done.");
        }
        try {
            int taskIndex = Integer.parseInt(parts[1]) - 1;
            taskList.markTaskNotDone(taskIndex);
            ui.showTaskList(taskList.getTasks());
        } catch (NumberFormatException e) {
            throw new JoeyException("Please enter a valid task number to mark as not done.");
        }
    }

    private static void parseDeleteCommand(String[] parts, TaskList taskList, Ui ui) throws JoeyException {
        if (parts.length < 2) {
            throw new JoeyException("Please provide a valid task number to delete.");
        }

        try {
            int taskIndex = Integer.parseInt(parts[1]) - 1;
            taskList.removeTask(taskIndex);
            ui.showTaskList(taskList.getTasks());
        } catch (NumberFormatException e) {
            throw new JoeyException("Please enter a valid task number to delete.");
        }
    }

    private static void parseFindCommand(String[] parts, TaskList taskList, Ui ui) throws JoeyException {
        if (parts.length < 2) {
            throw new JoeyException("Please provide a keyword to search for.");
        }

        String keyword = parts[1].toLowerCase();
        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (Task task : taskList.getTasks()) {
            if (task.getDescription().toLowerCase().contains(keyword)) {
                matchingTasks.add(task);
            }
        }

        ui.showMatchingTasks(matchingTasks);
    }


}
