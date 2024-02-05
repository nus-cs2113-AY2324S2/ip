public class Command {
    private Boolean isExit;
    private String command;
    private String arguments;
    private String description;
    private String by;
    private String from;
    private String to;

    public Command() {
        this.isExit = false;
    }

    public void exit() {
        this.isExit = true;
    }

    public Boolean getIsExit() {
        return this.isExit;
    }

    /**
     * Splits user input into command and arguments.
     *
     * @param input User input.
     */
    public void parseCommand(String input) {
        String[] parts = input.split(" ", 2);
        this.command = parts[0].toUpperCase();
        if (parts.length > 1) {
            this.arguments = parts[1];
        }
    }

    /**
     * Parses arguments (description) for ToDo.
     */
    public void parseToDoArguments() {
        this.description = this.arguments.trim();
    }

    /**
     * Parses arguments (description, by) for Deadline.
     */
    public void parseDeadlineArguments() {
        String[] argList = this.arguments.split("/by");
        this.description = argList[0].trim();
        this.by = argList[1].trim();
    }

    /**
     * Parses arguments (description, from, to) for Event.
     */
    public void parseEventArguments() {
        String[] argList = this.arguments.split("/from");
        this.description = argList[0].trim();
        String[] dateList = argList[1].split("/to");
        this.from = dateList[0].trim();
        this.to = dateList[1].trim();
    }

    /**
     * Executes the user command.
     *
     * @param taskList List of current tasks.
     */
    public void executeCommand(TaskList taskList) {
        switch (this.command) {
        case "EXIT":
            // Fallthrough
        case "BYE":
            exit();
            break;

        case "LIST":
            taskList.printTaskList();
            break;

        case "TODO":
            parseToDoArguments();
            taskList.addToDo(this.description);
            break;

        case "DEADLINE":
            parseDeadlineArguments();
            taskList.addDeadline(this.description, this.by);
            break;

        case "EVENT":
            parseEventArguments();
            taskList.addEvent(this.description, this.from, this.to);
            break;

        case "MARK":
            taskList.markTask(Integer.parseInt(this.arguments) - 1);
            break;

        case "UNMARK":
            taskList.unmarkTask(Integer.parseInt(this.arguments) - 1);
            break;

        default:
            System.out.println("Invalid command. Please try again.");
            break;
        }
    }
}
