package dukeRobot.Commands;
import dukeRobot.Commands.Command;
import dukeRobot.Tools.*;
import dukeRobot.Tasks.Task;

public class ExitCommand extends Command{
    public ExitCommand(Parser parser) {
        super(parser);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showByeMessage();
        //Add storage;
        //no need
    }
}
