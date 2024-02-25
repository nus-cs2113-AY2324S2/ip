package yuki.task;

import yuki.Constants;

import yuki.InputParser;
import yuki.Utils;
import yuki.exceptions.YukiExceptions;

import java.util.ArrayList;

import static yuki.Constants.TASK_INDEX;

/**
 * Represents list of tasks.
 * Supports operations on tasks: add, mark, unmark, delete, find
 */
public class TaskList {

    public ArrayList<Task> taskData;
    String[] data;
    String description;

    public TaskList(ArrayList<Task> taskData){
        this.taskData = taskData;
    }

    public ArrayList<Task> getTaskData() {
        return taskData;
    }

    /**
     * Reports the number of tasks currently stored.
     */
    public void reportNumberOfTasks() {
        System.out.println("Now you have " + taskData.size() + " tasks in the list.");
    }

    /**
     * Lists the tasks currently stored, together with the completion status, taskType, and description.
     */
    public void listTasks() {
        System.out.println("Wake up your idea and do these tasks:");
        int index = 1;
        for (Task item : taskData) {
            System.out.println((index) + ".[" + item.getStatusIcon() + "] "
                    + item.taskType + " " + item.description);
            index++;
        }
        reportNumberOfTasks();
    }

    /**
     * Marks a task as done or undone, depending on parameter cmd
     *
     * @param input input from user in the command line.
     * @param cmd either a "mark" or "unmark" string.
     * @throws ArrayIndexOutOfBoundsException if user does not input an index.
     * @throws yuki.exceptions.YukiExceptions.InvalidIndexException if user's inputted index is out of bounds.
     */
    public void markTask(String input, String cmd) throws YukiExceptions.InvalidIndexException,
            ArrayIndexOutOfBoundsException {
        if (input.split(" ").length < 2) {
            throw new ArrayIndexOutOfBoundsException("Index for marking not found.");
        }
        int indexTask = Integer.parseInt(input.split(" ")[TASK_INDEX]);
        if (indexTask <= 0 || indexTask > taskData.size()) {
            throw new YukiExceptions.InvalidIndexException("Invalid index for marking: " + indexTask);
        }
        if (cmd.equals(Constants.MARK_COMMAND)) {
            taskData.get(indexTask - 1).markAsDone();
        } else if (cmd.equals(Constants.UNMARK_COMMAND)) {
            taskData.get(indexTask - 1).markAsUndone();
        } else {
            System.out.println("invalid command in mark method");
        }
    }

    /**
     * Deletes the task at index given by user.
     *
     * @param input input from user in the command line.
     * @throws ArrayIndexOutOfBoundsException if user does not input an index.
     * @throws yuki.exceptions.YukiExceptions.InvalidIndexException if user's inputted index is out of bounds.
     */
    public void deleteTask(String input) throws YukiExceptions.InvalidIndexException,
            ArrayIndexOutOfBoundsException {
        if (input.split(" ").length < 2) {
            throw new ArrayIndexOutOfBoundsException("Index for marking not found.");
        }
        int indexTask = Integer.parseInt(input.split(" ")[TASK_INDEX]);
        if (indexTask <= 0 || indexTask > taskData.size()) {
            throw new YukiExceptions.InvalidIndexException("Invalid index for marking: " + indexTask);
        }
        Task toDelete = taskData.get(indexTask - 1);
        taskData.remove(indexTask - 1);
        System.out.println("Deleted task number " + (indexTask) + ": " + toDelete.description);
    }

    public void findTask(String line) {
        String keyword = InputParser.parseDescription(line);
        System.out.println("Tasks found:");
        taskData.stream()
                .filter(task -> task.description.contains(keyword))
                .forEach(System.out::println);
    }

    /**
     * Adds todo Task to list of tasks.
     *
     * @param input input from user in the command line.
     * @throws yuki.exceptions.YukiExceptions.InvalidDescriptionException if user's description is empty.
     */
    public void addTodo(String input) throws YukiExceptions.InvalidDescriptionException {
        data = InputParser.parseInput(input.substring(Constants.LENGTH_TODO_COMMAND));
        if (data[0].isEmpty()) {
            throw new YukiExceptions.InvalidDescriptionException("Invalid description");
        }
        description = data[0];
        Task t = new Todo(description, false);
        taskData.add(t);
        Utils.printTaskAddedMessage();
        System.out.println(t);
        reportNumberOfTasks();
    }

    /**
     * Adds deadline Task to list of tasks.
     *
     * @param input input from user in the command line.
     * @throws yuki.exceptions.YukiExceptions.InvalidDescriptionException if user does not input a date correctly.
     */
    public void addDeadline(String input) throws YukiExceptions.InvalidDescriptionException {
        data = InputParser.parseInput(input.substring(Constants.LENGTH_DEADLINE_COMMAND));
        if (data.length < 2) {
            throw new YukiExceptions.InvalidDescriptionException("Invalid description");
        }
        description = data[0] + " (by:" + data[1] + ")";
        Task t = new Deadline(description, false);
        taskData.add(t);
        Utils.printTaskAddedMessage();
        System.out.println(t);
        reportNumberOfTasks();
    }

    /**
     * Adds event Task to list of tasks.
     *
     * @param input input from user in the command line.
     * @throws yuki.exceptions.YukiExceptions.InvalidDescriptionException if user does not input two dates correctly.
     */
    public void addEvent(String input) throws YukiExceptions.InvalidDescriptionException {
        data = InputParser.parseInput(input.substring(Constants.LENGTH_EVENT_COMMAND));
        if (data.length < 3) {
            throw new YukiExceptions.InvalidDescriptionException("Invalid description");
        }
        description = data[0] + " (from: " + data[1] + " to: " + data[2] + ")";
        Task t = new Event(description, false);
        taskData.add(t);
        Utils.printTaskAddedMessage();
        System.out.println(t);
        reportNumberOfTasks();
    }

    /**
     * Runs command entered by user
     *
     * @param command command that the user inputs. E.g., "list", "mark", etc.
     * @param userInput whole input from user in the command line.
     */
    public void executeCommand(String command, String userInput) {
        Utils.printLine();
        switch (command) {
        case Constants.LIST_COMMAND:
            listTasks();
            break;
        case Constants.MARK_COMMAND:
            try {
                markTask(userInput, Constants.MARK_COMMAND);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(e.getMessage() + "\nPlease enter an index");
            } catch (YukiExceptions.InvalidIndexException e) {
                System.out.println(e.getMessage() + "\nPlease enter a valid index\n");
                listTasks();
            }
            break;
        case Constants.UNMARK_COMMAND:
            try {
                markTask(userInput, Constants.UNMARK_COMMAND);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(e.getMessage() + "\nPlease enter an index");
            } catch (YukiExceptions.InvalidIndexException e) {
                System.out.println(e.getMessage() + "\nPlease enter a valid index\n");
                listTasks();
            }
            break;
        case Constants.DELETE_COMMAND:
            try {
                deleteTask(userInput);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(e.getMessage() + "\nPlease enter an index");
            } catch (YukiExceptions.InvalidIndexException e) {
                System.out.println(e.getMessage() + "\nPlease enter a valid index\n");
                listTasks();
            }
            break;
        case Constants.FIND_COMMAND:
            findTask(userInput);
            break;
        case Constants.TODO_COMMAND:
            try {
                addTodo(userInput);
            } catch (YukiExceptions.InvalidDescriptionException e) {
                System.out.println(e.getMessage() + "\nPlease enter a valid description\n");
                Utils.printInstructions();
            }
            break;
        case Constants.DEADLINE_COMMAND:
            try {
                addDeadline(userInput);
            } catch (YukiExceptions.InvalidDescriptionException e) {
                System.out.println(e.getMessage() + "\nPlease enter a valid description\n");
                Utils.printInstructions();
            }
            break;
        case Constants.EVENT_COMMAND:
            try {
                addEvent(userInput);
            } catch (YukiExceptions.InvalidDescriptionException e) {
                System.out.println(e.getMessage() + "\nPlease enter a valid description\n");
                Utils.printInstructions();
            }
            break;
        case Constants.HELP_COMMAND:
            Utils.printInstructions();
            break;
        default:
            Utils.printInvalidCommandWarning();
            Utils.printInstructions();
            break;
        }
        Utils.printLine();
    }
}
