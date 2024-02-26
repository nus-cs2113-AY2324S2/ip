package command;

import exception.InputException;
import task.TaskList;

public interface Command {
    public void run(TaskList tasks) throws InputException;
    public boolean isExit();
}
