package ava.tasklist;

import ava.ui.Ui;
import ava.exception.EmptyTaskNameException;
import ava.parser.Parser;
import ava.task.Deadline;
import ava.task.Event;
import ava.task.Task;
import ava.task.ToDo;

import java.util.ArrayList;

/**
 * Contains the task list.
 * It has operations to add/delete/mark/find tasks in the list.
 */
public class TaskList extends ArrayList<Task> {
    private final Ui ui;

    public TaskList(Ui ui) {
        super();
        this.ui = ui;
    }

    /**
     * Adds a task to the TaskList based on the command entered.
     *
     * @param command Command entered. Format: "todo task" "deadline task /by deadline" or
     *                "event task /from startDate /to endDate"
     */
    public void addTask(String command, String type) throws EmptyTaskNameException {
        String task = command.replace(type, "");

        if (task.isEmpty()) {
            throw new EmptyTaskNameException();
        }

        String[] taskAndDate = task.split("/");
        taskAndDate[0] = taskAndDate[0].trim();

        switch (type) {
        case "todo":
            this.add(new ToDo(taskAndDate[0]));
            break;
        case "deadline":
            this.add(new Deadline(taskAndDate[0], taskAndDate[1]));
            break;
        case "event":
            this.add(new Event(taskAndDate[0], taskAndDate[1], taskAndDate[2]));
            break;
        }
    }

    /**
     * Deletes a task from the TaskList based on the command entered.
     *
     * @param command Command entered. Format: "delete int"
     */
    public void deleteTask(String command) {
        int taskNumberToBeDeleted;
        try {
            taskNumberToBeDeleted = Parser.extractTaskNumber("delete ", command);
            Task deletedTask = this.get(taskNumberToBeDeleted);
            this.remove(deletedTask);
            ui.printAfterDeletingTask(this, deletedTask);
        } catch (IndexOutOfBoundsException e) {
            ui.printTaskNotExistMessage("delete");
        } catch (NumberFormatException e) {
            ui.printEmptyTaskNameExceptionMessage("delete");
        }
    }

    /**
     * Marks or unmarks a task in the TaskList based on the command entered.
     * It catches NumberFormatException when a number is not followed by "mark" or "unmark".
     * It catches IndexOutOfBoundsException when user wants to mark a task which does not exist.
     *
     * @param command Command entered. Format: "mark int" or "unmark int"
     */
    public void markTask(String command) {
        boolean isMark = true;
        int taskChanged;
        if (command.startsWith("unmark")) {
            isMark = false;
        }

        try {
            if (isMark) {
                taskChanged = Parser.extractTaskNumber("mark ", command);
                this.get(taskChanged).markAsDone();
            } else {
                taskChanged = Parser.extractTaskNumber("unmark ", command);
                this.get(taskChanged).markAsNotDone();
            }
        } catch (IndexOutOfBoundsException e) {
            ui.printTaskNotExistMessage("mark");
            return;
        } catch (NumberFormatException e) {
            ui.printEmptyTaskNameExceptionMessage("mark");
            return;
        }

        ui.printAfterMarkingTask(this, isMark, taskChanged);
    }
}
