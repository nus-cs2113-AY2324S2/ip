package gary.command;

import gary.exception.*;
import gary.storage.Storage;
import gary.task.Task;
import gary.task.TaskList;

import java.io.File;
import java.util.ArrayList;

/**
 * AddCommand class is used to handle user commands to add Tasks. Tasks can
 * be Todo, Deadline, or Event
 */
public class AddCommand extends Command {

    private String line;
    private File file;
    private ArrayList<Task> todos;
    private String command;

    /**
     * Constructor for AddCommand class, taking in line, file, todos, and command as parameter.
     *
     * @param line user input to the terminal.
     * @param file txt file to store the tasks.
     * @param todos array list that stores and manages the task while programme is running.
     * @param command type of task user wants to add.
     */
    public AddCommand(String line, File file, ArrayList<Task> todos, String command) {
        this.line = line;
        this.file = file;
        this.todos = todos;
        this.command = command;
    }

    /**
     * Handles users' command to add task. If user command is unknown or missing details,
     * error is handled and message is printed out.
     */
    @Override
    public void handleCommand() {
        try {
            int todosCount = this.todos.size();
            TaskList.processAddTask(this.command, this.todos, this.line);
            todosCount += 1;
            this.todos.get(todosCount - 1).printAdd(todosCount);
            Storage.writeTaskToTxt(file, todosCount, this.todos);
        } catch (UnknownCommandException e) {
            System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
        } catch (MissingTodoDescriptionException e) {
            System.out.println("OOPS!!! The description of a todo cannot be empty");
        } catch (MissingDeadlineByException e) {
            System.out.println("OOPS!!! Deadline must contain '/by' and its description");
        } catch (MissingDeadlineDescriptionException e) {
            System.out.println("OOPS!!! The description of a deadline cannot be empty");
        } catch (MissingEventFromException e) {
            System.out.println("OOPS!!! Event must contain '/from' and its description");
        } catch (MissingEventToException e) {
            System.out.println("OOPS!!! Event must contain '/to' and its description");
        } catch (MissingEventDescriptionException e) {
            System.out.println("OOPS!!! The description of an event cannot be empty");
        }
    }
}
