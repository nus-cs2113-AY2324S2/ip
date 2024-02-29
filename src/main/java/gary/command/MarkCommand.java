package gary.command;

import gary.storage.Storage;
import gary.task.Task;
import gary.task.TaskList;

import java.io.File;
import java.util.ArrayList;

/**
 * MarkCommand class is to handle users' command to mark their task as done.
 */
public class MarkCommand extends Command {
    private File file;
    private ArrayList<Task> todos;
    private String[] lineWords;

    /**
     * Constructor of MarkCommand class, which takes in file storing all the tasks that
     * need to be updated, todos, and lineWords as parameter.
     *
     * @param file txt file to store the tasks.
     * @param todos array list that stores and manages the task while programme is running.
     * @param lineWords user input to the terminal, that has been split into array.
     */
    public MarkCommand(File file, ArrayList<Task> todos, String[] lineWords) {
        this.file = file;
        this.todos = todos;
        this.lineWords = lineWords;
    }

    /**
     * Handles users' command to mark task as done. It handles errors in users' input if
     * users try to mark task that does not exist or users' input is in a wrong format.
     */
    @Override
    public void handleCommand() {
        try {
            int todosCount = this.todos.size();
            TaskList.processMark(this.todos, this.lineWords);
            Storage.writeTaskToTxt(this.file, todosCount, this.todos);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("OOPS!!! You don't have that much task");
        } catch (NumberFormatException e) {
            System.out.println("OOPS!!! input after 'mark' must be a number");
        }
    }
}
