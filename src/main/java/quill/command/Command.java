package quill.command;

import quill.storage.Save;
import quill.task.Task;
import quill.task.TaskList;
import quill.ui.TextUi;

import java.util.ArrayList;

public abstract class Command {
    protected String commandWord;
    protected String parameter;

    public Command(String commandWord, String parameter) {
        this.commandWord = commandWord;
        this.parameter = parameter;
    }

    public void execute (TaskList tasks, TextUi ui, Save save) {
    }
}
