package Blue;

/**
 * Defines an encapsulation of a command and command arguments to be understood by Blue.
 */
public class Input {
    private InputCommand command;
    private int taskIndex;
    private String taskQuery;
    private Task taskToAdd;
    private String errorMessage;

    /**
     * Class level constructor for inputs without an argument.
     *
     * @param command User command.
     */
    public Input(InputCommand command) {
        this.command = command;
    }

    /**
     * Class level constructor for task index based inputs.
     *
     * @param command User command.
     * @param taskIndex Index of task to manage.
     */
    public Input(InputCommand command, int taskIndex) {
        this.command = command;
        this.taskIndex = taskIndex;
    }

    /**
     * Class level constructor for task query inputs.
     *
     * @param command User command.
     * @param taskQuery String query for task.
     */
    public Input(InputCommand command, String taskQuery) {
        this.command = command;
        this.taskQuery = taskQuery;
    }

    /**
     * Class level constructor for task adding inputs.
     *
     * @param command User command.
     * @param taskToAdd Task to add.
     */
    public Input(InputCommand command, Task taskToAdd) {
        this.command = command;
        this.taskToAdd = taskToAdd;
    }

    /**
     * Class level getter of command.
     *
     * @return command.
     */
    public InputCommand getCommand() {
        return command;
    }

    /**
     * Class level getter of taskIndex.
     *
     * @return taskIndex.
     */
    public int getTaskIndex() {
        return taskIndex;
    }

    /**
     * Class level getter of taskToAdd.
     *
     * @return taskToAdd.
     */
    public Task getTaskToAdd() {
        return taskToAdd;
    }

    /**
     * Class level getter of taskQuery.
     *
     * @return taskQuery.
     */
    public String getTaskQuery() {
        return taskQuery;
    }

    /**
     * Class level getter of errorMessage.
     *
     * @return errorMessage.
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Returns whether the input command is an exit request.
     *
     * @return True if command is an enum bye, false otherwise.
     */
    public boolean isNotExit() {
        return command != InputCommand.bye;
    }

    /**
     * Returns whether the input command is an ill-defined request.
     *
     * @return True if command is an enum undefined, false otherwise.
     */
    public boolean isUndefined() {
        return command == InputCommand.undefined;
    }
}
