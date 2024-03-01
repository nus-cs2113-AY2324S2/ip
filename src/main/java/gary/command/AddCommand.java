package gary.command;

import gary.exception.UnknownCommandException;
import gary.exception.MissingEventToException;
import gary.exception.MissingEventFromException;
import gary.exception.MissingEventDescriptionException;
import gary.exception.MissingDeadlineByException;
import gary.exception.MissingDeadlineDescriptionException;
import gary.exception.MissingTodoDescriptionException;
import gary.storage.Storage;
import gary.task.Task;
import gary.task.TaskList;

import java.io.File;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * AddCommand class is used to handle user commands to add Tasks. Tasks can
 * be Todo, Deadline, or Event
 */
public class AddCommand extends Command {

    private String line;
    private File file;
    private ArrayList<Task> tasks;
    private String command;

    /**
     * Constructor for AddCommand class, taking in line, file, todos, and command as parameter.
     *
     * @param line user input to the terminal.
     * @param file txt file to store the tasks.
     * @param tasks array list that stores and manages the task while programme is running.
     * @param command type of task user wants to add.
     */
    public AddCommand(String line, File file, ArrayList<Task> tasks, String command) {
        this.line = line;
        this.file = file;
        this.tasks = tasks;
        this.command = command;
    }

    /**
     * Handles users' command to add task. If user command is unknown or missing details,
     * error is handled and message is printed out.
     */
    @Override
    public void handleCommand() {
        try {
            int tasksCount = this.tasks.size();
            TaskList.processAddTask(this.command, this.tasks, this.line);
            tasksCount += 1;
            this.tasks.get(tasksCount - 1).printAdd(tasksCount);
            Storage.writeTaskToTxt(file, tasksCount, this.tasks);
        } catch (UnknownCommandException e) {
            System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
        } catch (MissingTodoDescriptionException e) {
            System.out.println("OOPS!!! The description of a todo cannot be empty");
        } catch (MissingDeadlineByException e) {
            System.out.println("OOPS!!! Deadline must contain '/by' and its date");
        } catch (MissingDeadlineDescriptionException e) {
            System.out.println("OOPS!!! The description of a deadline cannot be empty");
        } catch (MissingEventFromException e) {
            System.out.println("OOPS!!! Event must contain '/from' and its description");
        } catch (MissingEventToException e) {
            System.out.println("OOPS!!! Event must contain '/to' and its description");
        } catch (MissingEventDescriptionException e) {
            System.out.println("OOPS!!! The description of an event cannot be empty");
        } catch (DateTimeParseException e) {
            System.out.println("OOPS!!! Deadline '/by' date must be in yyyy-mm-dd format");
        }
    }
}
