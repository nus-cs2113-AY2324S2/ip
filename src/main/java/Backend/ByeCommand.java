package Backend;
import tasks.Task;
import java.util.List;

/**
 * This class represents the Bye command that is used to exit the chatbot.
 * Derived from the Command Super Class.
 */
public class ByeCommand extends Command {
    /**
     * Sets the static variable done to true which will exit the program.
     *
     * @param tasks Given a List of Task Objects.
     */
    @Override
    public void execute(List<Task> tasks) {
        setIsDone(true);
        Ui.Goodbye();
    }
}
