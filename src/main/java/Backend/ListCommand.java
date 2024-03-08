package Backend;
import tasks.Task;
import java.util.List;

/**
 * This class represents the List command that is used to display your todolist.
 * Derived from the Command Super Class.
 */
public class ListCommand extends Command {
    /**
     * Performs the action of listing out all existing tasks one by one.
     *
     * @param tasks Given a List of Task Objects.
     */
    @Override
    public void execute(List<Task> tasks) {
        for (int i=0; i<tasks.size(); i++){
            System.out.println(Integer.toString(i+1) + ". " + tasks.get(i));
        }
    }
}
