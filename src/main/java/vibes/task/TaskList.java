package vibes.task;

import vibes.exception.CommandNotFoundException;
import vibes.exception.InvalidArgumentException;
import vibes.parser.Parser;
import vibes.storage.Storage;
import vibes.task.type.Deadline;
import vibes.task.type.Event;
import vibes.task.type.Task;
import vibes.task.type.Todo;
import vibes.ui.TextUi;
import java.util.ArrayList;

public class TaskList {
    public static ArrayList<Task> tasks = new ArrayList<>();

    public void executeCommand(String commandToExecute, String userInput) throws CommandNotFoundException, InvalidArgumentException {
        switch (commandToExecute) {
        case "list":
            TextUi.listTasks();
            break;
        case "mark":
            setAsDone(Parser.parseTaskNumber(userInput));
            break;
        case "unmark":
            setAsNotDone(Parser.parseTaskNumber(userInput));
            break;
        case "todo":
            addTodo(Parser.parseTodo(userInput));
            TextUi.showTaskAddedMessage();
            break;
        case "event":
            addEvent(Parser.parseEvent(userInput));
            TextUi.showTaskAddedMessage();
            break;
        case "deadline":
            addDeadline(Parser.parseDeadline(userInput));
            TextUi.showTaskAddedMessage();
            break;
        case "delete":
            deleteTask(Parser.parseTaskNumber(userInput));
            break;
        default:
            throw new CommandNotFoundException();
        }
        Storage.saveTask(this);
    }

    public void addEvent(String[] parsedInput) {
        tasks.add(new Event(parsedInput[0], parsedInput[1], parsedInput[2]));
    }

    public void addDeadline(String[] parsedInput) {
        tasks.add(new Deadline(parsedInput[0], parsedInput[1]));
    }

    public void addTodo(String description) {
        tasks.add(new Todo(description));
    }

    public void setAsDone(int taskNumber) {
        tasks.get(taskNumber).setDone(true);
        TextUi.showMarkedMessage(taskNumber);
    }

    public void setAsNotDone(int taskNumber) {
        tasks.get(taskNumber).setDone(false);
        TextUi.showUnmarkedMessage(taskNumber);
    }

    public void deleteTask(int taskNumber) {
        Task taskToDelete = tasks.get(taskNumber);
        tasks.remove(taskNumber);
        TextUi.showDeletedMessage(taskToDelete);
    }
}
