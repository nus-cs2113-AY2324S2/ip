package quill.command;

import quill.storage.Save;
import quill.task.Task;
import quill.ui.TextUi;

import java.util.ArrayList;

public abstract class Command {
    protected String commandWord;
    protected String parameter;

    public Command(String commandWord, String parameter) {
        this.commandWord = commandWord;
        this.parameter = parameter;
    }

    public void execute (ArrayList<Task> tasks, TextUi ui, Save save) {
    }
}
