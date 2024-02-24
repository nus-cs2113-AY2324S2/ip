package quill.command;

import quill.storage.Save;
import quill.task.Task;
import quill.task.TaskList;
import quill.ui.TextUi;

import java.util.ArrayList;

public class UnknownCommand extends Command{
    public UnknownCommand(String commandWord, String parameter) {
        super(commandWord, parameter);
    }

    @Override
    public void execute(TaskList tasks, TextUi ui, Save save) {
        System.out.println("Enough with the gibberish. Stick to the commands I understand:");
        System.out.println("bye, list, todo, deadline, event, mark, unmark, delete. Got it? Next!");
    }
}
