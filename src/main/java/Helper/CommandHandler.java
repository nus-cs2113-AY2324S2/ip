package Helper;

import Exceptions.InvalidDateTimeFormatException;
import Exceptions.InvalidDeadlineFormatException;
import Exceptions.InvalidEventFormatException;
import Exceptions.InvalidTodoFormatException;

public class CommandHandler {
    public static final String BYE = "bye";
    public static final String MARK = "mark";
    public static final String UNMARK = "unmark";
    public static final String LIST = "list";
    public static final String DELETE = "delete";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";
    public static final String TODO = "todo";
    private TaskManager taskManager;
    private UserInterface userInterface;

    public CommandHandler(TaskManager taskManager, UserInterface userInterface) {
        this.taskManager = taskManager;
        this.userInterface = userInterface;
    }

    public static final int MARK_BEGIN_INDEX = 5;
    public static final int UNMARK_BEGIN_INDEX = 7;
    public static final int DELETE_BEGIN_INDEX = 7;
    public static final int COMMAND_START_INDEX = 0;
    public static final int INDEX_OFFSET = 1;

    public void handleCommand(String input) {
        String inputType = input.split(" ")[COMMAND_START_INDEX];
        switch (inputType.toLowerCase()) {
        case BYE:
            userInterface.sayGoodbye();
            System.exit(0);
            break;
        case MARK:
            markTask(input);
            break;
        case UNMARK:
            unmarkTask(input);
            break;
        case LIST:
            taskManager.printTaskList();
            break;
        case DELETE:
            deleteTask(input);
            break;
        default:
            addTask(input);
            break;
        }
        taskManager.saveTasksToFile();
    }

    private void addTask(String taskDescription) {
        String taskType = taskDescription.split(" ")[COMMAND_START_INDEX];
        try {
            switch (taskType) {
            case DEADLINE:
                taskManager.addDeadlineTask(taskDescription);
                break;
            case EVENT:
                taskManager.addEventTask(taskDescription);
                break;
            case TODO:
                taskManager.addTodoTask(taskDescription);
                break;
            default:
                throw new NoSuchMethodException();
            }
        } catch (NoSuchMethodException e) {
            userInterface.printInvalidTaskType(taskDescription);
        } catch (InvalidDeadlineFormatException e) {
            userInterface.printInvalidDeadlineFormat(e);
        } catch (InvalidTodoFormatException e) {
            userInterface.printInvalidTodoFormat(e);
        } catch (InvalidEventFormatException e) {
            userInterface.printInvalidEventFormat(e);
        } catch (InvalidDateTimeFormatException e) {
            userInterface.printInvalidDateTimeFormat(e);
        }
    }

    private void deleteTask(String input) {
        try {
            int taskIndex = Integer.parseInt(input.substring(DELETE_BEGIN_INDEX).trim()) - INDEX_OFFSET;
            taskManager.deleteTask(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            userInterface.printInvalidTaskIndex(e);
        } catch (NumberFormatException e) {
            userInterface.printInvalidInputIndex(e);
        }
    }

    private void markTask(String input) {
        try {
            int taskIndex = Integer.parseInt(input.substring(MARK_BEGIN_INDEX).trim()) - INDEX_OFFSET;
            taskManager.markTask(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            userInterface.printInvalidTaskIndex(e);
        } catch (NumberFormatException e) {
            userInterface.printInvalidInputIndex(e);
        }
    }

    private void unmarkTask(String input) {
        try {
            int taskIndex = Integer.parseInt(input.substring(UNMARK_BEGIN_INDEX).trim()) - INDEX_OFFSET;
            taskManager.unmarkTask(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            userInterface.printInvalidTaskIndex(e);
        } catch (NumberFormatException e) {
            userInterface.printInvalidInputIndex(e);
        }
    }
}
