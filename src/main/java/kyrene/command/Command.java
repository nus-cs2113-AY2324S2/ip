package kyrene.command;

import kyrene.exception.KyreneInvalidCommandException;
import kyrene.exception.KyreneMissingTaskException;
import kyrene.exception.KyreneMissingTimeException;
import kyrene.exception.KyreneTaskNotFoundException;
import kyrene.task.Deadline;
import kyrene.task.Event;
import kyrene.task.Todo;
import kyrene.taskList.TaskList;
import kyrene.ui.Ui;
import kyrene.storage.Storage;

import java.io.IOException;

public class Command {

    private Commands command;
    private String description;
    private int taskNumber;
    private boolean isExit;

    public Command() {
        this.command = Commands.INVALID;
        this.taskNumber = -1;
        this.description = null;
        this.isExit = true;
    }

    public Command(Commands command) {
        this.command = command;
        this.taskNumber = -1;
        this.description = null;
        this.isExit = (command == Commands.BYE) || (command == Commands.INVALID);
    }
    public Command(Commands command, int taskNumber) {
        this.command = command;
        this.taskNumber = taskNumber;
        this.description = null;
        this.isExit = (command == Commands.BYE) || (command == Commands.INVALID);
    }

    public Command(Commands command, String description) {
        this.command = command;
        this.taskNumber = -1;
        this.description = description;
        this.isExit = (command == Commands.BYE) || (command == Commands.INVALID);
    }

    public boolean isExit() {
        return isExit;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        switch (command) {
        case BYE:
            handleBye(ui);
            break;
        case LIST:
            handleList(tasks, ui);
            break;
        case ADD:
            try {
                handleAdd(tasks, ui, storage);
            } catch (KyreneMissingTaskException e) {
                ui.showErrorMissingTask();
            } catch (KyreneInvalidCommandException e) {
                ui.showErrorInvalidCommand();
            }
            break;
        case DELETE:
            try {
                handleDelete(tasks, ui, storage);
            } catch (NumberFormatException | KyreneTaskNotFoundException e) {
                int taskCount = tasks.size();
                ui.showErrorTaskNotExist(taskCount);
            }  catch (IndexOutOfBoundsException e) {
                ui.showErrorInvalidCommand();
            }
            break;
        case MARK:
            try {
                handleMark(tasks, ui, storage, true);
            } catch (NumberFormatException | KyreneTaskNotFoundException e) {
                int taskCount = tasks.size();
                ui.showErrorTaskNotExist(taskCount);
            } catch (IndexOutOfBoundsException e) {
                ui.showErrorInvalidCommand();
            }
            break;
        case UNMARK:
            try {
                handleMark(tasks, ui, storage, false);
            } catch (NumberFormatException | KyreneTaskNotFoundException e) {
                int taskCount = tasks.size();
                ui.showErrorTaskNotExist(taskCount);
            } catch (IndexOutOfBoundsException e) {
                ui.showErrorInvalidCommand();
            }
            break;
        default:
            handleError(ui);
            break;
        }
    }

    private void handleError(Ui ui) {
        ui.showErrorInvalidCommand();
    }

    private void handleBye(Ui ui) {
        ui.showBye();
    }

    private void handleList(TaskList tasks, Ui ui) {
        ui.showTasks(tasks);
    }

    private void handleMark(TaskList tasks, Ui ui, Storage storage, boolean isDone) throws KyreneTaskNotFoundException {
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new KyreneTaskNotFoundException();
        }
        tasks.get(taskNumber - 1).setDone(isDone);

        try {
            storage.write(tasks);
        } catch (IOException e) {
            ui.showErrorWriteToFileFailed();
        }

        ui.showSuccessMarkingTask(taskNumber, isDone);
    }

    private void handleAdd(TaskList tasks, Ui ui, Storage storage) throws KyreneInvalidCommandException, KyreneMissingTaskException {
        assert description != null;
        String[] words = description.split(" ");
        String classType = words[0];

        switch (classType) {
        case "todo":
            try {
                tasks.add(new Todo(description.substring("todo".length() + 1)));
            } catch (StringIndexOutOfBoundsException e) {
                throw new KyreneMissingTaskException();
            }
            break;
        case "deadline":
            try {
                tasks.add(new Deadline(description.substring("deadline".length() + 1)));
            } catch (StringIndexOutOfBoundsException e) {
                throw new KyreneMissingTaskException();
            } catch (KyreneMissingTimeException e) {
                ui.showErrorMissingTime();
                return;
            }
            break;
        case "event":
            try {
                tasks.add(new Event(description.substring("event".length() + 1)));
            } catch (StringIndexOutOfBoundsException e) {
                throw new KyreneMissingTaskException();
            } catch (KyreneMissingTimeException e) {
                ui.showErrorMissingTime();
                return;
            }
            break;
        default:
            throw new KyreneInvalidCommandException();
        }

        try {
            storage.write(tasks);
        } catch (IOException e) {
            ui.showErrorWriteToFileFailed();
        }

        int taskCount = tasks.size();
        String taskAdded = tasks.get(taskCount - 1).toString();
        ui.showSuccessAddingTask(taskAdded, taskCount);
    }

    private void handleDelete(TaskList tasks, Ui ui, Storage storage) throws KyreneTaskNotFoundException {
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new KyreneTaskNotFoundException();
        }
        String taskDeleted = tasks.get(taskNumber - 1).toString();
        tasks.remove(taskNumber - 1);

        try {
            storage.write(tasks);
        } catch (IOException e) {
            ui.showErrorWriteToFileFailed();
        } finally {
            int taskCount = tasks.size();
            ui.showSuccessDeletingTask(taskDeleted, taskCount);
        }
    }

}
