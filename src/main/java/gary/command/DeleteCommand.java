package gary.command;

import gary.storage.Storage;
import gary.task.Task;
import gary.task.TaskList;

import java.io.File;
import java.util.ArrayList;

public class DeleteCommand extends Command {
    private File file;
    private ArrayList<Task> todos;
    private String[] lineWords;

    public DeleteCommand(File file, ArrayList<Task> todos, String[] lineWords) {
        this.file = file;
        this.todos = todos;
        this.lineWords = lineWords;
    }

    @Override
    public void handleCommand() {
        try {
            int todosCount = this.todos.size();
            TaskList.processDelete(this.todos, this.lineWords, todosCount);
            todosCount -= 1;
            Storage.writeTaskToTxt(this.file, todosCount, this.todos);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("OOPS!!! You don't have that much task");
        } catch (NumberFormatException e) {
            System.out.println("OOPS!!! input after 'delete' must be a number");
        }
    }
}
