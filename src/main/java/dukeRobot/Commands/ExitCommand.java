package dukeRobot.Commands;
import dukeRobot.Commands.Command;
import dukeRobot.Tools.*;
import dukeRobot.Tasks.Task;

/**
 * represents the exit command from the Duke robot A <code>ExitCommand</code>
 * corresponds to finishing the commands and leaving.
 */
public class ExitCommand extends Command{
    public ExitCommand(Parser parser) {
        super(parser);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showByeMessage();
    }
}
