package command;
import exception.AdamException;
import task.TaskList;

public interface Command {
    boolean execute(TaskList tasks) throws AdamException;
}
