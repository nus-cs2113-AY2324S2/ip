package kyrene.command;

import kyrene.exception.KyreneInvalidCommandException;
import kyrene.exception.KyreneMissingTaskException;
import kyrene.exception.KyreneMissingTimeException;
import kyrene.exception.KyreneTaskNotFoundException;
import kyrene.parser.Parser;
import kyrene.task.Deadline;
import kyrene.task.Event;
import kyrene.task.Task;
import kyrene.task.Todo;
import kyrene.taskList.TaskList;
import kyrene.ui.Ui;
import kyrene.storage.Storage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Represents a command that consists a main command word,
 * possibly a description or task number to describe the target of the command,
 * and an exit flag to indicate whether to exit the programme or not.
 */
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

    /**
     * Construct a command of BYE or LIST, otherwise INVALID command.
     */
    public Command(Commands command) {
        this.command = command;
        this.taskNumber = -1;
        this.description = null;
        this.isExit = (command == Commands.BYE) || (command == Commands.INVALID);
    }

    /**
     * Construct a command of MARK, UNMARK, or DELETE, otherwise INVALID command.
     */
    public Command(Commands command, int taskNumber) {
        this.command = command;
        this.taskNumber = taskNumber;
        this.description = null;
        this.isExit = (command == Commands.BYE) || (command == Commands.INVALID);
    }

    /**
     * Construct a command of ADD, AT, DUE ,or FIND, otherwise INVALID command.
     */
    public Command(Commands command, String description) {
        this.command = command;
        this.taskNumber = -1;
        this.description = description;
        this.isExit = (command == Commands.BYE) || (command == Commands.INVALID);
    }

    /**
     * Returns the exit flag indicating whether to exit the programme.
     *
     * @return Whether to exit the programme.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Identify and execute the command.
     *
     * @param tasks A task list containing all existing tasks.
     * @param storage A storage object to read and write to specific file.
     */
    public void execute(TaskList tasks, Storage storage) {
        switch (command) {
        case BYE:
            handleBye();
            break;
        case LIST:
            handleList(tasks);
            break;
        case ADD:
            try {
                handleAdd(tasks, storage);
            } catch (KyreneMissingTaskException e) {
                Ui.showErrorMissingTask();
            } catch (KyreneInvalidCommandException e) {
                Ui.showErrorInvalidCommand();
            }
            break;
        case DELETE:
            try {
                handleDelete(tasks, storage);
            } catch (NumberFormatException | KyreneTaskNotFoundException e) {
                int taskCount = tasks.size();
                Ui.showErrorTaskNotExist(taskCount);
            }  catch (IndexOutOfBoundsException e) {
                Ui.showErrorInvalidCommand();
            }
            break;
        case MARK:
            try {
                handleMark(tasks, storage, true);
            } catch (NumberFormatException | KyreneTaskNotFoundException e) {
                int taskCount = tasks.size();
                Ui.showErrorTaskNotExist(taskCount);
            } catch (IndexOutOfBoundsException e) {
                Ui.showErrorInvalidCommand();
            }
            break;
        case UNMARK:
            try {
                handleMark(tasks, storage, false);
            } catch (NumberFormatException | KyreneTaskNotFoundException e) {
                int taskCount = tasks.size();
                Ui.showErrorTaskNotExist(taskCount);
            } catch (IndexOutOfBoundsException e) {
                Ui.showErrorInvalidCommand();
            }
            break;
        case AT:
            handleAT(tasks);
            break;
        case DUE:
            handleDue(tasks);
            break;
        case FIND:
            handleFind(tasks);
            break;
        default:
            handleError();
            break;
        }
    }

    /**
     * Handle the invalid command error by displaying the error message.
     */
    private void handleError() {
        Ui.showErrorInvalidCommand();
    }

    /**
     * Handle the BYE command by displaying exit message.
     */
    private void handleBye() {
        Ui.showBye();
    }

    /**
     * Handle LIST command by listing all tasks within the task list.
     *
     * @param tasks A task list containing all existing tasks.
     */
    private void handleList(TaskList tasks) {
        Ui.showTasks(tasks);
    }

    /**
     * Handle MARK command by marking the specified task in the task list
     * as done or not done depending on the input.
     *
     * @param tasks A task list containing all existing tasks.
     * @param storage A storage object to read and write to specific file.
     * @param isDone True for marking as done, otherwise marking as not done.
     */
    private void handleMark(TaskList tasks, Storage storage, boolean isDone)
            throws KyreneTaskNotFoundException {
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new KyreneTaskNotFoundException();
        }
        tasks.get(taskNumber - 1).setDone(isDone);

        try {
            storage.write(tasks);
        } catch (IOException e) {
            Ui.showErrorWriteToFileFailed();
        }

        Ui.showSuccessMarkingTask(taskNumber, isDone);
    }

    /**
     * Handle ADD command by adding the input task into the task list.
     *
     * @param tasks A task list containing all existing tasks.
     * @param storage A storage object to read and write to specific file.
     */
    private void handleAdd(TaskList tasks, Storage storage)
            throws KyreneInvalidCommandException, KyreneMissingTaskException {
        String taskType = Parser.parseTaskType(description);

        switch (taskType) {
        case "todo":
            try {
                tasks.add(new Todo(description.substring("todo ".length())));
            } catch (StringIndexOutOfBoundsException e) {
                throw new KyreneMissingTaskException();
            }
            break;
        case "deadline":
            try {
                tasks.add(new Deadline(description.substring("deadline ".length())));
            } catch (StringIndexOutOfBoundsException e) {
                throw new KyreneMissingTaskException();
            } catch (KyreneMissingTimeException e) {
                Ui.showErrorMissingTime();
                return;
            }
            break;
        case "event":
            try {
                tasks.add(new Event(description.substring("event ".length())));
            } catch (StringIndexOutOfBoundsException e) {
                throw new KyreneMissingTaskException();
            } catch (KyreneMissingTimeException e) {
                Ui.showErrorMissingTime();
                return;
            }
            break;
        default:
            throw new KyreneInvalidCommandException();
        }

        try {
            storage.write(tasks);
        } catch (IOException e) {
            Ui.showErrorWriteToFileFailed();
        }

        int taskCount = tasks.size();
        String taskAdded = tasks.get(taskCount - 1).toString();
        Ui.showSuccessAddingTask(taskAdded, taskCount);
    }

    /**
     * Handle DELETE command by deleting the specified task in the task list.
     *
     * @param tasks A task list containing all existing tasks.
     * @param storage A storage object to read and write to specific file.
     */
    private void handleDelete(TaskList tasks, Storage storage)
            throws KyreneTaskNotFoundException {
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new KyreneTaskNotFoundException();
        }
        String taskDeleted = tasks.get(taskNumber - 1).toString();
        tasks.remove(taskNumber - 1);

        try {
            storage.write(tasks);
        } catch (IOException e) {
            Ui.showErrorWriteToFileFailed();
        } finally {
            int taskCount = tasks.size();
            Ui.showSuccessDeletingTask(taskDeleted, taskCount);
        }
    }

    /**
     * Handle AT command by searching and listing out all events in the task list
     * that happen at the specified time.
     *
     * @param tasks A task list containing all existing tasks.
     */
    private void handleAT(TaskList tasks) {
        LocalDate date = Parser.parseDate(description);
        TaskList events = new TaskList();
        for (Task task : tasks) {
            if (task.getTaskType().equals("E") && ((Event) task).isAt(date)){
                events.add(task);
            }
        }
        Ui.showEvents(events);
    }

    /**
     * Handle DUE command by searching and listing out all deadlines in the task list
     * that due before the specified time.
     *
     * @param tasks A task list containing all existing tasks.
     */
    private void handleDue(TaskList tasks) {
        LocalDateTime time = Parser.parseTime(description);
        TaskList deadlines = new TaskList();
        for (Task task : tasks) {
            if (task.getTaskType().equals("D") && ((Deadline) task).isBefore(time)){
                deadlines.add(task);
            }
        }
        Ui.showDeadlines(deadlines);
    }

    /**
     * Handle FIND command by searching and listing out all tasks in the task list
     * that match with the key word.
     *
     * @param tasks A task list containing all existing tasks.
     */
    private void handleFind(TaskList tasks) {
        TaskList matches = new TaskList();
        for (Task task : tasks) {
            if (task.getTaskName().contains(description)) {
                matches.add(task);
            }
        }
        Ui.showMatches(matches);
    }

}
