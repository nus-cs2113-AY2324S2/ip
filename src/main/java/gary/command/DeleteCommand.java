package gary.command;

import gary.storage.Storage;
import gary.task.Task;
import gary.task.TaskList;

import java.io.File;
import java.util.ArrayList;

/**
 * DeleteCommand class is used to handle users' command to delete a task previously added.
 */
public class DeleteCommand extends Command {
    private File file;
    private ArrayList<Task> tasks;
    private String[] lineWords;

    /**
     * Constructor for DeleteCommand, taking in file, todos array list, and lineWords as parameter.
     * Parameters will be used to handle users' command.
     *
     * @param file txt file to store the tasks.
     * @param tasks array list that stores and manages the task while programme is running.
     * @param lineWords user input to the terminal, that has been split into array.
     */
    public DeleteCommand(File file, ArrayList<Task> tasks, String[] lineWords) {
        this.file = file;
        this.tasks = tasks;
        this.lineWords = lineWords;
    }

    /**
     * Handles users' command to delete task. task deleted is taken from users' input in the
     * form of number. If users try to delete task that is not in the list or use a wrong format,
     * the programme handles the error.
     */
    @Override
    public void handleCommand() {
        try {
            int todosCount = this.tasks.size();
            TaskList.processDelete(this.tasks, this.lineWords, todosCount);
            todosCount -= 1;
            Storage.writeTaskToTxt(this.file, todosCount, this.tasks);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("OOPS!!! You don't have that much task");
        } catch (NumberFormatException e) {
            System.out.println("OOPS!!! input after 'delete' must be a number");
        }
    }
}
