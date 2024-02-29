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

    private void handleError() {
        Ui.showErrorInvalidCommand();
    }

    private void handleBye() {
        Ui.showBye();
    }

    private void handleList(TaskList tasks) {
        Ui.showTasks(tasks);
    }

    private void handleMark(TaskList tasks, Storage storage, boolean isDone) throws KyreneTaskNotFoundException {
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

    private void handleAdd(TaskList tasks, Storage storage) throws KyreneInvalidCommandException, KyreneMissingTaskException {
        assert description != null;
        String[] words = description.split(" ");
        String classType = words[0];

        switch (classType) {
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

    private void handleDelete(TaskList tasks, Storage storage) throws KyreneTaskNotFoundException {
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
