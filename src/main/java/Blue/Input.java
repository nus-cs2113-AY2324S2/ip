package Blue;

public class Input {
    private InputCommand command;
    private int taskIndex;
    private Task taskToAdd;

    Input(InputCommand command) {
        this.command = command;
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
}
