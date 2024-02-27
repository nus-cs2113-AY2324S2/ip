package Blue;

public class Input {
    private InputCommand command;
    private int taskIndex;
    private String taskQuery;
    private Task taskToAdd;
    private String errorMessage;

    Input(InputCommand command) {
        this.command = command;
    }

    Input(String errorMessage) {
        command = InputCommand.undefined;
        this.errorMessage = errorMessage;
    }

    Input(InputCommand command, int taskIndex, Task taskToAdd, String taskQuery) {
        this.command = command;
        this.taskIndex = taskIndex;
        this.taskToAdd = taskToAdd;
        this.taskQuery = taskQuery;
    }
    
    public InputCommand getCommand() {
        return command;
    }
    public int getTaskIndex() {
        return taskIndex;
    }
    public Task getTaskToAdd() {
        return taskToAdd;
    }
    public String getTaskQuery() {
        return taskQuery;
    }
    public String getErrorMessage() {
        return errorMessage;
    }

    public boolean isExit() {
        return command == InputCommand.bye;
    }

    public boolean isUndefined() {
        return command == InputCommand.undefined;
    }
}
