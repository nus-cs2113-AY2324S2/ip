package quill.command;

import quill.storage.Save;
import quill.task.Task;
import quill.task.TaskList;
import quill.ui.TextUi;

import java.util.ArrayList;

public class ListCommand extends Command{
    public ListCommand(String commandWord, String parameter) {
        super(commandWord, parameter);
    }

    public void execute (TaskList tasks, TextUi ui, Save save) {
        TextUi.showList(tasks);
    }
}
