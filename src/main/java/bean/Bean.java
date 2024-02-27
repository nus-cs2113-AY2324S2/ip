package bean;

import bean.command.Parser;
import bean.command.exception.NoValueException;
import bean.storage.Storage;
import bean.task.Task;
import bean.task.TaskList;
import bean.ui.Ui;

/**
 * Class for the Bean chatbot. Its methods only consist of those to do with processing commands.
 */
public class Bean {
    private static final String FILE_PATH = "tasks.txt";

    /**
     * Edits the listOfTasks or prints required information to the UI
     * according to the command passed in by the user.
     *
     * @param line String representing user's input
     * @param listOfTasks TaskList to be edited
     * @param isForLoading Silently processes commands to add tasks if set to true.
     */
    public static void processAndExecute(String line, TaskList listOfTasks, boolean isForLoading) {
        Parser userLine = new Parser(line);
        switch (userLine.getCommand()) {
        case "list": {
            Ui.printTaskList(listOfTasks);
            break;
        }
        case "mark": {
            processMarkCommand(listOfTasks, userLine);
            break;
        }
        case "unmark": {
            processUnmarkCommand(listOfTasks, userLine);
            break;
        }
        case "todo": {
            processTodoCommand(listOfTasks, isForLoading, userLine);
            break;
        }
        case "deadline": {
            processDeadlineCommand(listOfTasks, isForLoading, userLine);
            break;
        }
        case "event": {
            processEventCommand(listOfTasks, isForLoading, userLine);
            break;
        }
        case "find": {
            processFindCommand(listOfTasks, userLine);
            break;
        }
        case "delete": {
            processDeleteCommand(listOfTasks, userLine);
            break;
        }
        default:
            if (!isForLoading) {
                Ui.printNoSuchCommand();
            }
            break;
        }
    }

    private static void processFindCommand(TaskList listOfTasks, Parser userLine) {
        try {
            String query = userLine.getArgument();
            TaskList listOfMatches = listOfTasks.findTask(query);
            Ui.printFoundTasks(listOfMatches);
        } catch (NoValueException e) {
            Ui.printNoValueForFields();
        }
    }

    /**
     * Deletes the required task from listOfTasks.
     * Prints error message if the task number is invalid/does not exist.
     *
     * @param listOfTasks TaskList for task to be deleted from
     */
    private static void processDeleteCommand(TaskList listOfTasks, Parser userLine) {
        try {
            int taskIndex = Integer.parseInt(userLine.getArgument()) - 1;
            Task deletedTask = listOfTasks.removeTask(taskIndex);
            Ui.printTaskDeleted(deletedTask);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            Ui.printInvalidTaskNo();
        } catch (NoValueException e) {
            Ui.printNoValueForFields();
        }
        return;
    }

    /**
     * Adds an Event task to the listOfTasks.
     *
     * @param userLine Parser representing user's input
     * @param listOfTasks TaskList for Event to be added to
     * @param isForLoading Silently adds Event to listOfTasks if set to true
     */
    private static void processEventCommand(TaskList listOfTasks, boolean isForLoading, Parser userLine) {
        boolean taskIsDone;
        try {
            taskIsDone = userLine.getValue("isDone").equals("true");
        } catch (NoValueException e) {
            taskIsDone = false;
        }

        try {
            String description = userLine.getArgument();
            String start = userLine.getValue("start");
            String end = userLine.getValue("end");
            Task newTask = listOfTasks.addTask(description, start, end);
            listOfTasks.markTask(listOfTasks.getNumTasks() - 1, taskIsDone);
            if (!isForLoading) {
                Ui.printTaskAdded(newTask, listOfTasks.getNumTasks());
            }
        } catch (NoValueException e) {
            if (!isForLoading) {
                Ui.printNoValueForFields();
            }
        }
    }

    /**
     * Adds a Deadline task to the listOfTasks.
     *
     * @param userLine Parser representing user's input
     * @param listOfTasks TaskList for Deadline to be added to
     * @param isForLoading Silently adds Deadline to listOfTasks if set to true
     */
    private static void processDeadlineCommand(TaskList listOfTasks, boolean isForLoading, Parser userLine) {
        boolean taskIsDone;
        try {
            taskIsDone = userLine.getValue("isDone").equals("true");
        } catch (NoValueException e) {
            taskIsDone = false;
        }

        try {
            String description = userLine.getArgument();
            String by = userLine.getValue("by");
            Task newTask = listOfTasks.addTask(description, by);
            listOfTasks.markTask(listOfTasks.getNumTasks() - 1, taskIsDone);
            if (!isForLoading) {
                Ui.printTaskAdded(newTask, listOfTasks.getNumTasks());
            }
        } catch (NoValueException e) {
            if (!isForLoading) {
                Ui.printNoValueForFields();
            }
        }
    }

    /**
     * Adds a Todo task to the listOfTasks.
     *
     * @param userLine Parser representing user's input
     * @param listOfTasks TaskList for Todo to be added to
     * @param isForLoading Silently adds Todo to listOfTasks if set to true
     */
    private static void processTodoCommand(TaskList listOfTasks, boolean isForLoading, Parser userLine) {
        boolean taskIsDone;
        try {
            taskIsDone = userLine.getValue("isDone").equals("true");
        } catch (NoValueException e) {
            taskIsDone = false;
        }

        try {
            String description = userLine.getArgument();
            Task newTask = listOfTasks.addTask(description);
            listOfTasks.markTask(listOfTasks.getNumTasks() - 1, taskIsDone);
            if (!isForLoading) {
                Ui.printTaskAdded(newTask, listOfTasks.getNumTasks());
            }
        } catch (NoValueException e) {
            if (!isForLoading) {
                Ui.printNoValueForFields();
            }
        }
    }

    /**
     * Unmarks a particular task from the listOfTasks.
     *
     * @param userLine Parser representing user's input
     * @param listOfTasks TaskList containing task to be unmarked
     */
    private static void processUnmarkCommand(TaskList listOfTasks, Parser userLine) {
        try {
            int taskIndex = Integer.parseInt(userLine.getArgument()) - 1;
            Task unmarkedTask = listOfTasks.markTask(taskIndex, false);
            Ui.printTaskUndone(unmarkedTask);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            Ui.printInvalidTaskNo();
        } catch (NoValueException e) {
            Ui.printNoValueForFields();
        }
    }

    /**
     * Marks a particular task from the listOfTasks.
     *
     * @param userLine Parser representing user's input
     * @param listOfTasks TaskList containing task to be marked
     */
    private static void processMarkCommand(TaskList listOfTasks, Parser userLine) {
        try {
            int taskIndex = Integer.parseInt(userLine.getArgument()) - 1;
            Task markedTask = listOfTasks.markTask(taskIndex, true);
            Ui.printTaskDone(markedTask);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            Ui.printInvalidTaskNo();
        } catch (NoValueException e) {
            Ui.printNoValueForFields();
        }
    }

    /**
     * The main method running the chatbot
     */
    public static void main(String[] args) {
        Storage storage = new Storage(FILE_PATH);
        TaskList listOfTasks = storage.loadTaskList();
        Ui.printWelcomeMessage();

        String line = Ui.getUserInput();
        while (!line.equals("bye")) {
            processAndExecute(line, listOfTasks, false);
            line = Ui.getUserInput();
        }

        storage.saveTaskList(listOfTasks);
        Ui.printGoodbyeMessage();
    }
}
