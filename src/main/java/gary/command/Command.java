package gary.command;

import gary.exception.MissingDeadlineByException;
import gary.exception.UnknownCommandException;
import gary.exception.MissingTodoDescriptionException;
import gary.exception.MissingDeadlineDescriptionException;
import gary.exception.MissingEventFromException;
import gary.exception.MissingEventToException;
import gary.exception.MissingEventDescriptionException;
import gary.storage.Storage;
import gary.task.Task;
import gary.task.TaskList;

import java.io.File;
import java.util.ArrayList;

public class Command {
    public static void runAdd(String line, File file, ArrayList<Task> todos, String command, int todosCount) {
        try {
            TaskList.processAddTask(command, todos, line);
            todosCount += 1;
            todos.get(todosCount - 1).printAdd(todosCount);
            Storage.writeTaskToTxt(file, todosCount, todos);
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

    public static void runDelete(File file, ArrayList<Task> todos, String[] lineWords, int todosCount) {
        try {
            TaskList.processDelete(todos, lineWords, todosCount);
            todosCount -= 1;
            Storage.writeTaskToTxt(file, todosCount, todos);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("OOPS!!! You don't have that much task");
        } catch (NumberFormatException e) {
            System.out.println("OOPS!!! input after 'delete' must be a number");
        }
    }

    public static void runUnmark(File file, ArrayList<Task> todos, String[] lineWords, int todosCount) {
        try {
            TaskList.processUnmark(todos, lineWords);
            Storage.writeTaskToTxt(file, todosCount, todos);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("OOPS!!! You don't have that much task");
        } catch (NumberFormatException e) {
            System.out.println("OOPS!!! input after 'unmark' must be a number");
        }
    }

    public static void runMark(File file, ArrayList<Task> todos, String[] lineWords, int todosCount) {
        try {
            TaskList.processMark(todos, lineWords);
            Storage.writeTaskToTxt(file, todosCount, todos);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("OOPS!!! You don't have that much task");
        } catch (NumberFormatException e) {
            System.out.println("OOPS!!! input after 'mark' must be a number");
        }
    }

    public static void runList(ArrayList<Task> todos, int todosCount) {
        TaskList.processList(todosCount, todos);
    }
}
