package dukeRobot.Commands;
import dukeRobot.Commands.Command;
import dukeRobot.Tools.*;
import dukeRobot.Tasks.*;

import java.io.IOException;

/**
 * represent the command of deletion from the tasklist, A <code>DeleteCommand</code>
 * corresponds to the deletion from the tasklist.
 */
public class DeleteCommand extends Command{
    int index;
    public DeleteCommand(Parser parser) {
        super(parser);
        this.index = parser.getTaskIndex();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            ui.showRemoveMessage(tasks, this.index);
            this.index = this.index - 1;
            Task.numOfTask -= 1;
            try {
                tasks.remove(tasks.get(index));
            } catch (IndexOutOfBoundsException e) {
                ui.indexOutOfBoundError();
            }
            //Add storage
            //delete all texts
            try {
                storage.FileClearer();
            } catch (IOException e) {
                ui.showClearFileError();
            }
            //rewrite the whole file
            for (Task task: tasks) {
                try {
                    storage.FileUpdater(task.toString() + "\n");
                } catch (IOException e) {
                    ui.showWriteFileError();
                }
            }
        } catch (IndexOutOfBoundsException e) {
            ui.indexOutOfBoundError();
        }
    }
}
