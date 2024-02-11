package command;
import task.TaskList;

public interface Command {
    boolean execute(TaskList tasks);
}
