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

public class AddCommand extends Command {

    private String line;
    private File file;
    private ArrayList<Task> todos;
    private String command;

    public AddCommand(String line, File file, ArrayList<Task> todos, String command) {
        this.line = line;
        this.file = file;
        this.todos = todos;
        this.command = command;
    }

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
