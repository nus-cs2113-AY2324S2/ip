package Backend;
import tasks.Task;
import java.util.List;

public class Command {

    private static boolean isDone = false;

    public Command() {}

    public boolean isDone() {
        return isDone;
    }
    public void setIsDone(boolean b) {
        Command.isDone = b;
    }

    public void execute(List<Task> tasks) {
    }

}
