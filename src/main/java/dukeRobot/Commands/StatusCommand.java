package dukeRobot.Commands;
import dukeRobot.Tasks.*;
import dukeRobot.Tools.*;

import java.io.IOException;

/** represent for command LIST, MARK,UNMARK
 * A <code>StatusCommand</code> corresponds to performing LIST, MARK,UNMARK operations.
 */
public class StatusCommand extends Command{
    int index;
    String wordToFind;
    public StatusCommand(Parser parser) {
        super(parser);
        this.index = parser.getTaskIndex();
        this.wordToFind = parser.getWord();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        try {
            switch (keyWord) {
                case "help":
                    ui.help();
                    break;
                case "list":
                    ui.showTasks(tasks);
                    break;
                case "mark":
                    try {
                        tasks.get(index - 1).markAsDone();
                    } catch (IndexOutOfBoundsException e) {
                        ui.indexOutOfBoundError();
                    }
                    break;
                case "unmark":
                    try {
                        tasks.get(index - 1).markAsUndone();
                    } catch (IndexOutOfBoundsException e) {
                        ui.indexOutOfBoundError();
                    }
                    break;
                case "find":
                    TaskList findTasks = new TaskList();
                    for (Task task: tasks) {
                        if (task.toString().contains(wordToFind)) {
                            findTasks.add(task);
                        }
                    }
                    ui.showFind(findTasks);
                    break;
                default:
                    throw new NoSuchMethodException();
            }
            //Add Storage
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

        } catch (NoSuchMethodException e) {
            ui.notUnderstandError();
        }
    }
}
