package quill.command;

import quill.storage.Save;
import quill.task.Task;
import quill.task.TaskList;
import quill.ui.TextUi;

import java.util.ArrayList;

public class ExitCommand extends Command {
    public ExitCommand(String commandWord, String parameter) {
        super(commandWord, parameter);
    }

    @Override
    public void execute(TaskList tasks, TextUi ui, Save save) {
        Save.writeToFile(tasks);
        TextUi.showGoodbyeMessage();
    }

    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }
}
