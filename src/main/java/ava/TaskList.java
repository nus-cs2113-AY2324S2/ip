package ava;

import ava.task.Deadline;
import ava.task.Event;
import ava.task.Task;
import ava.task.ToDo;

import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {
    private final Ui ui;

    public TaskList(Ui ui) {
        super();
        this.ui = ui;
    }

    public void addTask(String task, String type) throws EmptyTaskNameException {
        task = task.replace(type, "");

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
