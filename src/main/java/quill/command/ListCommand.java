package quill.command;

import quill.storage.Save;
import quill.task.Task;
import quill.ui.TextUi;

import java.util.ArrayList;

public class ListCommand extends Command{
    public ListCommand(String commandWord, String parameter) {
        super(commandWord, parameter);
    }

    public void execute (ArrayList<Task> tasks, TextUi ui, Save save) {
        if (Task.isEmpty()) {
            TextUi.showDivider();
            System.out.println("Zero tasks. Add something already.");
        } else {
            TextUi.showDivider();
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < Task.getTotalTasks(); i++) {
                System.out.println(i + 1 + "." + tasks.get(i).toString());
            }
        }
        TextUi.showDivider();
    }
}
