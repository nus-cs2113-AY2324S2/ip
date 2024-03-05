package dukeRobot.Commands;
import dukeRobot.Commands.Command;
import dukeRobot.Tools.*;
import dukeRobot.Tasks.*;

import java.io.IOException;

public class DeleteCommand extends Command{
    int index;
    public DeleteCommand(Parser parser) {
        super(parser);
        this.index = parser.getTaskIndex();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            this.index = this.index - 1;
            Task.numOfTask -= 1;
            tasks.remove(tasks.get(index));
            ui.showRemoveMessage(tasks, this.index);
            //Add storage
            //delete all texts
            try {
                storage.FileClearer();
            } catch (IOException e) {
                ui.showIOException();
            }
            //rewrite the whole file
            for (Task task: tasks) {
                try {
                    storage.FileUpdater(task.toString() + "\n");
                } catch (IOException e) {
                    ui.showIOException();
                }
            }
        } catch (IndexOutOfBoundsException e) {
            ui.indexOutOfBoundError();
        }
    }
}
