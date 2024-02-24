package quill.command;

import quill.storage.Save;
import quill.task.Task;
import quill.ui.TextUi;

import java.util.ArrayList;

public class DeleteCommand extends Command{
    public DeleteCommand(String commandWord, String parameter) {
        super(commandWord, parameter);
    }

    @Override
    public void execute(ArrayList<Task> tasks, TextUi ui, Save save) {
        try {
            int taskNumber = Integer.parseInt(parameter) - 1;
            TextUi.showDeleteTask(tasks.get(taskNumber));
            tasks.remove(taskNumber);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Hey, wake up! That task? Non-existent. Try something real.");
        } catch (NumberFormatException e) {
            System.out.println("Listen up! Numbers only, got it? Don't bother with anything else");
        }
    }
}
