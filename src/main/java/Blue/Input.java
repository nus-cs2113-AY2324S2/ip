package Blue;

/**
 * Defines an encapsulation of commands and command arguments well understood by Blue.
 */
public class Input {
    private InputCommand command;
    private int taskIndex;
    private String taskQuery;
    private Task taskToAdd;
    private String errorMessage;

    /**
     * Class level constructor for ill-defined inputs.
     *
     * @param errorMessage Error message pertaining to how input is ill-defined.
     */
    public Input(String errorMessage) {
        command = InputCommand.undefined;
        this.errorMessage = errorMessage;
    }

    /**
     * Class level constructor for well-defined inputs.
     *
     * @param command User command.
     * @param taskIndex Task index of task to handle.
     * @param taskToAdd Task to add.
     * @param taskQuery String query for task.
     */
    public Input(InputCommand command, int taskIndex, Task taskToAdd, String taskQuery) {
        this.command = command;
        this.taskIndex = taskIndex;
        this.taskToAdd = taskToAdd;
        this.taskQuery = taskQuery;
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
    public boolean isExit() {
        return command == InputCommand.bye;
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
