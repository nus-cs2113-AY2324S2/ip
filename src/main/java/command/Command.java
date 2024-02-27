package command;

import task.TaskList;

public abstract class Command {
    private boolean ifNoError;
    protected String content;

    public Command() {

    }

    public void setIfNoError(boolean ifNoError) {
        this.ifNoError = ifNoError;
    }

    public abstract void execute(TaskList taskList);

    public boolean getIfNoError() {
        return ifNoError;
    }

}
