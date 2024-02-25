package alpaca.logic;

import alpaca.ui.Ui;
import alpaca.storage.Storage;
import alpaca.tasks.*;
import alpaca.exceptions.InvalidCommandException;
import alpaca.exceptions.InvalidIndexException;


public class LogicManager {
    private TaskList taskList;
    private Ui ui;

    public LogicManager(Ui ui) {
        this.taskList = TaskLoader.loadTask();
        this.ui = ui;
    }

    public void executeCommand (String command, String details) throws InvalidCommandException{
        switch (command) {
        case "list":
            ui.printTaskList(taskList);
            break;
        case "mark":
        case "unmark":
            markUnmarkTask(details, command.equals("mark"));
            Storage.startFileWriter(taskList.saveTask());
            break;
        case "delete":
            deleteTask(details);
            Storage.startFileWriter(taskList.saveTask());
            break;
        case "bye":
            ui.printGoodbye();
            System.exit(0);
            break;
        default:
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
        ui.printAddTask(task, taskList.getTotalTaskNumber());
        Storage.startFileWriter(taskList.saveTask());
    }

    private void deleteTask(String details) {
        int taskIndex = Integer.parseInt(details) - 1;
        try {
            if (!taskList.isCountValid(taskIndex)) {
                throw new InvalidIndexException();
            }
            taskList.deleteTask(taskIndex);
            ui.printLine();
        } catch (InvalidIndexException e) {
            ui.printErrorMessage(e.toString());
        }
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
            ui.printLine();
        } catch (InvalidIndexException e) {
            ui.printErrorMessage(e.toString());
        }
    }
}