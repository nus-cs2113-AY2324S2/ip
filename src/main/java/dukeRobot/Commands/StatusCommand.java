package dukeRobot.Commands;
import dukeRobot.Tasks.*;
import dukeRobot.Tools.*;

import java.io.IOException;

//This class is for function LIST, MARK,UNMARK,DELETE
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
                case "list":
                    ui.showTasks(tasks);
                    break;
                case "mark":
                    tasks.get(index-1).markAsDone();
                    break;
                case "unmark":
                    tasks.get(index-1).markAsUndone();
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

        } catch (NoSuchMethodException e) {
            ui.notUnderstandError();
        }
    }
}
