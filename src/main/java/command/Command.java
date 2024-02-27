package command;

import task.TaskList;

/**
 * Abstract level of all Command class
 */
public abstract class Command {
    private boolean ifNoError;
    /**
     * The part of the Command that is the content of the task
     */
    protected String content;

    public Command() {

    }

    /**
     * Setter for variable ifNoError
     *
     * @param ifNoError If there is an error when executing the command
     */
    public void setIfNoError(boolean ifNoError) {
        this.ifNoError = ifNoError;
    }

    /**
     * Abstract Level of execute method
     *
     * @param taskList Instance of Class <code>TaskList</code>.
     * @see TaskList
     */
    public abstract void execute(TaskList taskList);

    /**
     * Getter for variable IfNoError
     *
     * @return The value of variable IfNoError
     */
    public boolean getIfNoError() {
        return ifNoError;
    }
}
