package blue.command;

import blue.task.Task;

/**
 * Defines an encapsulation of a command and command arguments to be understood by Blue.
 */
public class Input {
    private InputCommand command;
    private int taskIndex;
    private String taskQuery;
    private Task taskToAdd;

    /**
     * Constructor for inputs without an argument.
     *
     * @param command User command.
     */
    public Input(InputCommand command) {
        this.command = command;
    }

    /**
     * Constructor for task index based inputs.
     *
     * @param command User command.
     * @param taskIndex Index of task to manage.
     */
    public Input(InputCommand command, int taskIndex) {
        this(command);
        this.taskIndex = taskIndex;
    }

    /**
     * Constructor for task query inputs.
     *
     * @param command User command.
     * @param taskQuery String query for task.
     */
    public Input(InputCommand command, String taskQuery) {
        this(command);
        this.taskQuery = taskQuery;
    }

    /**
     * Constructor for task adding inputs.
     *
     * @param command User command.
     * @param taskToAdd Task to add.
     */
    public Input(InputCommand command, Task taskToAdd) {
        this(command);
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

    public String getTaskQuery() {
        return taskQuery;
    }

    /**
     * Returns true if command is not an exit request, false otherwise
     *
     * @return True if command is not the enum bye, false otherwise.
     */
    public boolean isNotExit() {
        return command != InputCommand.bye;
    }
}
