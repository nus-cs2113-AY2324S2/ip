package gary.command;

import gary.storage.Storage;
import gary.task.Task;
import gary.task.TaskList;

import java.io.File;
import java.util.ArrayList;

/**
 * UnmarkCommand class is to handle users' command to unmark their task as done, that is,
 * to mark their task as not done.
 */
public class UnmarkCommand extends Command {
    private File file;
    private ArrayList<Task> tasks;
    private String[] lineWords;

    /**
     * Constructor of UnmarkCommand class, which takes in file storing all the tasks that
     * need to be updated, todos, and lineWords as parameter.
     *
     * @param file txt file to store the tasks.
     * @param tasks array list that stores and manages the task while programme is running.
     * @param lineWords user input to the terminal, that has been split into array.
     */
    public UnmarkCommand(File file, ArrayList<Task> tasks, String[] lineWords) {
        this.file = file;
        this.tasks = tasks;
        this.lineWords = lineWords;
    }

    /**
     * Handles users' command to unmark task as done. It handles errors in users' input if
     * users try to unmark task that does not exist or users' input is in a wrong format.
     */
    @Override
    public void handleCommand() {
        try {
            int tasksCount = this.tasks.size();
            TaskList.processUnmark(this.tasks, this.lineWords);
            Storage.writeTaskToTxt(this.file, tasksCount, this.tasks);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("OOPS!!! You don't have that much task");
        } catch (NumberFormatException e) {
            System.out.println("OOPS!!! input after 'unmark' must be a number");
        }
    }
}
