package Blue;

public class Input {
    private InputCommand command;
    private int taskIndex;
    private Task taskToAdd;
    private String errorMessage;

    Input(InputCommand command) {
        this.command = command;
    }

    Input(InputCommand command, String errorMessage) {
        this.command = command;
        this.errorMessage = errorMessage;
    }

    Input(InputCommand command, int taskIndex, Task taskToAdd) {
        this.command = command;
        this.taskIndex = taskIndex;
        this.taskToAdd = taskToAdd;
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
