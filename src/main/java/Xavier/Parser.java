package Xavier;

import Exceptions.InvalidInputException;
import Exceptions.NoInputException;
import Tasks.Task;

import java.util.ArrayList;

/**
 * Parses user input.
 */
public class Parser {

    /**
     * Parses the input and executes the corresponding command.
     *
     * @param command input command from the user
     * @param taskList the list of task and list related operations
     * @param ui the Ui object which handles printing to the user interface
     * @param storage the Storage object which handles saving and reading the file
     * @throws InvalidInputException If user inputs invalid command
     * @throws NoInputException If user inputs improper command format
     */
    public void parse(String command, TaskList taskList, Ui ui, Storage storage) throws InvalidInputException, NoInputException {
        if (command.equals("list")) {
            ui.list(taskList);
        } else if (command.startsWith("mark") || command.startsWith(("unmark"))) {
            handleMarkAsDone(command, taskList, ui, storage);
        } else if (command.startsWith("todo") || command.startsWith("deadline") || command.startsWith("event")) {
            handleAddTask(command, taskList, ui, storage);
        } else if (command.startsWith("delete")) {
            handleDeleteTask(command, taskList, ui, storage);
        } else if (command.startsWith("find")) {
            ui.printSearchResult(handleFindTask(command, taskList, ui));
        } else if (command.equals("bye")) {
            ui.exit();
        } else {
            throw new InvalidInputException();
        }
    }

    /**
     * Marks or unmarks the specified task as done
     *
     * @param command input command from the user
     * @param taskList the list of task and list related operations
     * @param ui the Ui object which handles printing to the user interface
     * @param storage the storage object which handles saving and reading the file
     */
    public static void handleMarkAsDone(String command, TaskList taskList, Ui ui, Storage storage) {
        String[] stringArray = command.split(" ");
        int index = Integer.parseInt(stringArray[1]) - 1;
        if (command.startsWith("mark")) {
            taskList.markAsDone(index);
            ui.printMarkTaskMessage(taskList, index);
        } else if (command.startsWith("unmark")) {
            taskList.unmarkAsDone(index);
            ui.printUnmarkTaskMessage(taskList, index);
        }
        storage.saveFile(taskList);
    }

    /**
     * Adds task to the todo list
     *
     * @param command input command from the user
     * @param taskList the list of task and list related operations
     * @param ui the Ui object which handles printing to the user interface
     * @param storage the storage object which handles saving and reading the file
     * @throws InvalidInputException If user inputs invalid command
     * @throws NoInputException If user inputs improper command format
     */
    public static void handleAddTask(String command, TaskList taskList, Ui ui, Storage storage) throws InvalidInputException, NoInputException {
        String[] stringArray = command.split(" ");
        if (stringArray.length == 1) {
            throw new NoInputException();
        }
        if (command.startsWith("todo")){
            int toDoStartIndex = 4;
            String toDoString = command.substring(toDoStartIndex);
            taskList.addTodoTask(toDoString);
        }
        else if (command.startsWith("deadline")){
            if (!command.contains("/by")) {
                throw new InvalidInputException();
            }
            int deadlineIndex = command.indexOf("/by");
            String description = command.substring(8, deadlineIndex);
            String by = command.substring(deadlineIndex + 4);
            taskList.addDeadline(description, by);
        }
        else if (command.startsWith("event")){
            if (!command.contains("/from") || !command.contains("/to")) {
                throw new InvalidInputException();
            }
            int startIndex = command.indexOf("/from");
            int endIndex = command.indexOf("/to");
            String description = command.substring(5, startIndex);
            String from = command.substring(startIndex + 6, endIndex);
            String to = command.substring(endIndex + 4);
            taskList.addEvent(description, from, to);
        }
        ui.printAddTaskMessage(taskList);
        storage.saveFile(taskList);
    }

    /**
     * Deletes task to the todo list
     *
     * @param command input command from the user
     * @param taskList the list of task and list related operations
     * @param ui the Ui object which handles printing to the user interface
     * @param storage the storage object which handles saving and reading the file
     */
    public static void handleDeleteTask(String command, TaskList taskList, Ui ui, Storage storage) {
        String[] stringArray = command.split(" ");
        int index = Integer.parseInt(stringArray[1]) - 1;
        ui.printDeleteTaskMessage(taskList, index);
        taskList.deleteTask(index);
        storage.saveFile(taskList);
    }

    public static ArrayList<Task> handleFindTask(String command, TaskList taskList, Ui ui) {
        String[] stringArray = command.split(" ");
        String keyword = stringArray[1];
        ArrayList<Task> newTaskList = new ArrayList<>();
        for (Task task : taskList.getItemList()) {
            if (task.getDescription().contains(keyword)) {
                newTaskList.add(task);
            }
        }
        return newTaskList;
    }

}
