package Backend;
import tasks.Task;
import java.util.List;

public class ByeCommand extends Command {
    @Override
    public void execute(List<Task> tasks) {
        setIsDone(true);
        Ui.Goodbye();
    }
}
