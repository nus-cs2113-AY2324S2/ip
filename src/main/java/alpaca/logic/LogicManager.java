package alpaca.logic;

import alpaca.UI.ResponseManager;
import alpaca.tasks.*;
import alpaca.exceptions.InvalidCommandException;
import alpaca.exceptions.InvalidIndexException;

public class LogicManager {
    private TaskList taskList;
    private ResponseManager responseManager;

    public LogicManager(ResponseManager responseManager) {
        this.taskList = new TaskList();
        this.responseManager = responseManager;
    }

    public void executeCommand (String command, String details) throws InvalidCommandException{
        switch (command) {
        case "list":
            responseManager.printTaskList(taskList);
            break;
        case "mark":
        case "unmark":
            markUnmarkTask(details, command.equals("mark"));
            break;
        case "bye":
            responseManager.printGoodbye();
            System.exit(0);
            break;
        default:
            // Handle other commands or ignore unknown commands
            throw new InvalidCommandException();
        }
    }

    public void addTodo(String details){
        addTask(new Todo(details));
    }

    public void addDeadline(String title, String by) {
        addTask(new Deadline(title, by));
    }

    public void addEvent(String title, String from, String to) {
        addTask(new Event(title, from, to));
    }

    private void addTask(Task task) {
        taskList.addTask(task);
        responseManager.printAddTask(task, taskList.getTotalTaskNumber());
    }

    private void markUnmarkTask(String details, boolean isMark) {
        int taskIndex = Integer.parseInt(details) - 1;
        try {
            if (!taskList.isCountValid(taskIndex)) {
                throw new InvalidIndexException();
            }
            if (isMark) {
                taskList.markTask(taskIndex);
            } else {
                taskList.unmarkTask(taskIndex);
            }
            responseManager.printLine();
        } catch (InvalidIndexException e) {
            ResponseManager.printErrorMessage(e.toString());
        }
    }
}