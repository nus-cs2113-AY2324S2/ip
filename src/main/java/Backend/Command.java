package Backend;
import tasks.Task;
import java.util.List;

/**
 * This Super class represents a command that is interpertatble by the chatbot.
 * Command Class objects can be executed and set as done.
 */
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
