import java.util.ArrayList;

public abstract class Command {
    public abstract ArrayList<Task> execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
    public abstract boolean isExit();
}
