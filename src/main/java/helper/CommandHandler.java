package helper;

import exceptions.InvalidDateTimeFormatException;
import exceptions.InvalidDeadlineFormatException;
import exceptions.InvalidEventFormatException;
import exceptions.InvalidTodoFormatException;

/**
 * The CommandHandler class is responsible for processing user
 * input and executing corresponding commands.
 * It handles user input and invokes appropriate command objects based on the input.
 * */

public class CommandHandler {
    public static final String BYE = "bye";
    public static final String MARK = "mark";
    public static final String UNMARK = "unmark";
    public static final String LIST = "list";
    public static final String DELETE = "delete";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";
    public static final String TODO = "todo";
    public static final int KEYWORD_BEGIN_INDEX = 5;
    public static final String FIND = "find";

    private final TaskManager taskManager;

    /**
     * Constructs a CommandHandler object with the given TaskManager and UserInterface.
     *
     * @param taskManager   The TaskManager object to handle tasks.
     *
     */

    public CommandHandler(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    public static final int MARK_BEGIN_INDEX = 5;
    public static final int UNMARK_BEGIN_INDEX = 7;
    public static final int DELETE_BEGIN_INDEX = 7;
    public static final int COMMAND_START_INDEX = 0;
    public static final int INDEX_OFFSET = 1;

    /**
     * Handles the user command based on the input provided.
     *
     * @param input The user input command.
     */

    public void handleCommand(String input) {
        String inputType = input.split(" ")[COMMAND_START_INDEX];
        switch (inputType.toLowerCase()) {
        case BYE:
            UserInterface.sayGoodbye();
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
        case FIND:
            findTasks(input);
            break;
        default:
            addTask(input);
            break;
        }
        taskManager.saveTasksToFile();
    }

    /**
     * Adds a task based on the task description provided by the user.
     *
     * @param taskDescription The description of the task provided by the user.
     */

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
            UserInterface.printInvalidTaskType(taskDescription);
        } catch (InvalidDeadlineFormatException e) {
            UserInterface.printInvalidDeadlineFormat(e);
        } catch (InvalidTodoFormatException e) {
            UserInterface.printInvalidTodoFormat(e);
        } catch (InvalidEventFormatException e) {
            UserInterface.printInvalidEventFormat(e);
        } catch (InvalidDateTimeFormatException e) {
            UserInterface.printInvalidDateTimeFormat(e);
        }
    }

    /**
     * Deletes a task specified by the user input.
     * Calls appropriate function to alert the user if the
     * index given by the user is wrong
     *
     * @param input The user input specifying the task to be deleted.
     */

    private void deleteTask(String input) {
        try {
            int taskIndex = Integer.parseInt(input.substring(DELETE_BEGIN_INDEX).trim()) - INDEX_OFFSET;
            taskManager.deleteTask(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            UserInterface.printInvalidTaskIndex(e);
        } catch (NumberFormatException e) {
            UserInterface.printInvalidInputIndex(e);
        }
    }

    /**
     * Marks a task as completed based on the user input.
     * Calls appropriate function to alert the user if the
     * index given by the user is wrong
     *
     * @param input The user input specifying the task to be marked.
     */

    private void markTask(String input) {
        try {
            int taskIndex = Integer.parseInt(input.substring(MARK_BEGIN_INDEX).trim()) - INDEX_OFFSET;
            taskManager.markTask(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            UserInterface.printInvalidTaskIndex(e);
        } catch (NumberFormatException e) {
            UserInterface.printInvalidInputIndex(e);
        }
    }

    /**
     * Marks a task as incomplete based on the user input.
     * Calls appropriate function to alert the user if the
     * index given by the user is wrong
     *
     * @param input The user input specifying the task to be unmarked.
     */

    private void unmarkTask(String input) {
        try {
            int taskIndex = Integer.parseInt(input.substring(UNMARK_BEGIN_INDEX).trim()) - INDEX_OFFSET;
            taskManager.unmarkTask(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            UserInterface.printInvalidTaskIndex(e);
        } catch (NumberFormatException e) {
            UserInterface.printInvalidInputIndex(e);
        }
    }

    /**
     * Finds a task from the lists of task based on the keyword
     * Calls appropriate function to alert the user if the
     * keyword is missing
     *
     * @param input The user input for keyword that needs to be searched for.
     */
    private void findTasks(String input) {
        try {
            String keyword = input.substring(KEYWORD_BEGIN_INDEX).trim();
            taskManager.findTasksByKeyword(keyword);
        } catch (StringIndexOutOfBoundsException e){
            UserInterface.printInvalidKeywordFormat(e);
        }
    }
}
