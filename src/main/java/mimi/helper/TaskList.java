package mimi.helper;

import mimi.classes.Deadline;
import mimi.classes.Event;
import mimi.classes.Task;
import mimi.classes.ToDo;
import mimi.exceptions.MimiException;

import java.util.ArrayList;

/**
 * TaskList class is responsible for handling the task list of the application.
 * It contains methods to add, delete, mark and unmark tasks.
 * It also contains methods to find tasks.
 * It also contains methods to append tasks into the task list.
 * It also contains methods to check the validity of the index
 * <p>
 * TaskList contains a static ArrayList of Task objects called taskList.
 * This is to ensure that the taskList is shared across all instances of TaskList.
 * the TaskList object must be initiated in the main class.
 *
 * @author Justin
 * @version 0.2
 * @since 0.2
 */

public class TaskList {

    private static ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public static ArrayList<Task> getTaskList() {
        return taskList;
    }

    public static void setTaskList(ArrayList<Task> taskList) {
        TaskList.taskList = taskList;
    }

    /**
     * Used to add a new todo into the task list.
     *
     * @param inputs An array containing the task name in the format {taskName}.
     *               The taskName is a String representing the name of the task.
     */
    public void addToDo(String[] inputs) {
        try {
            ToDo toDo = Parser.processsToDoFromInput(inputs);
            appendIntoTaskList(toDo);
            Ui.printSuccessMessage(toDo, taskList);
        } catch (MimiException.InsufficientParameters | MimiException.IncorrectFormat e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Used to add a new deadline into the task list.
     *
     * @param inputs An array containing the task name and date in the format {taskName, date}.
     *               *              The taskName is a String representing the name of the task.
     *               The date is a String representing the deadline date of the task.
     */
    public void addDeadline(String[] inputs) {
        try {
            Deadline deadline = Parser.processDeadlineFromInput(inputs);
            appendIntoTaskList(deadline);
            Ui.printSuccessMessage(deadline, taskList);
        } catch (MimiException.InsufficientParameters | MimiException.IncorrectFormat e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Used to add a new event into the task list.
     *
     * @param inputs An array containing the event name, starting date, ending date
     *               In the format {taskName, startDate, endDate}.
     *               The taskName is a String representing the name of the task.
     *               The startDate is a String representing the starting date of the task.
     *               The endDate is a String representing the ending date of the task.
     */

    public void addEvent(String[] inputs) {
        try {
            Event event = Parser.processEventFromInput(inputs);
            appendIntoTaskList(event);
            Ui.printSuccessMessage(event, taskList);
        } catch (MimiException.InsufficientParameters | MimiException.IncorrectFormat e) {
            System.out.println(e.getMessage());
        }

    }


    /**
     * Used to unmark a task as undone.
     *
     * @param inputs An array containing the index of the task to be marked as undone.
     *               In the format {index}.
     *               The index is an integer representing the index of the task to be marked as undone.
     *               The index must be a valid index in the task list else it will throw a TaskNotFoundException.
     */
    public void unmarkTasks(String[] inputs) {
        try {
            int index = checkValidityOfIndex(inputs);
            taskList.get(index).markAsUndone();
            Ui.printUnmarkTask(taskList, index);
        } catch (MimiException.TaskNotFound |
                 MimiException.InsufficientParameters |
                 MimiException.IncorrectFormat e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Used to mark a task as done.
     *
     * @param inputs An array containing the index of the task to be marked as done.
     *               In the format {index}.
     *               The index is an integer representing the index of the task to be marked as done.
     *               The index must be a valid index in the task list else it will throw a TaskNotFoundException.
     */
    public void markTask(String[] inputs) {
        try {
            int index = checkValidityOfIndex(inputs);
            taskList.get(index).markAsDone();
            Ui.printMarkTask(taskList, index);
        } catch (MimiException.TaskNotFound | MimiException.InsufficientParameters | MimiException.IncorrectFormat e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Used to delete a task from the task list.
     *
     * @param inputs An array containing the index of the task to be deleted.
     *               In the format {index}.
     *               The index is an integer representing the index of the task to be deleted.
     *               The index must be a valid index in the task list else it will throw a TaskNotFoundException.
     */
    public void deleteTask(String[] inputs) {
        try {
            int index = checkValidityOfIndex(inputs);
            Task removedTask = taskList.get(index);
            taskList.remove(index);
            Ui.printDeleteMessage(removedTask, taskList);

        } catch (MimiException.TaskNotFound | MimiException.InsufficientParameters | MimiException.IncorrectFormat e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Used to find a task from the task list.
     * The method will check if any of the task contains the given keyword and print the task found.
     * If no task is found, it will print a message to inform the user that no task is found.
     * If the keyword is not given, it will throw an InsufficientParametersException.
     * e.g. 'find ' will result in an error
     * e.g. 'find book' will search for all tasks containing the word 'book'
     *
     * @param inputs An array containing the keyword to search for.
     *               In the format {keyword}.
     *               The keyword is a String representing the keyword to search for in the task list.
     */
    public void findTask(String[] inputs)  {
        ArrayList<Task> taskFound = new ArrayList<>();
        try {

            if (inputs.length < 2) {
                throw new MimiException.InsufficientParameters(MimiException.INSUFFICIENT_FIND_PARAMETERS_MSG);
            }

            if (inputs[1].isBlank()){
                throw new MimiException.InsufficientParameters(MimiException.INSUFFICIENT_FIND_PARAMETERS_MSG);
            }

            String keyword = inputs[1];
            for (Task t : taskList) {
                if (t.getName().contains(keyword)) {
                    taskFound.add(t);
                }
            }
            Ui.printTaskFound(taskFound);

        } catch (MimiException.InsufficientParameters e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * Used to append a new task into the task list.
     * Used by the addDeadline, addEvent and addToDo methods and the storage class.
     *
     * @param newTask A Task object representing the new task to be added into the task list.
     */
    public static void appendIntoTaskList(Task newTask) {
        taskList.add(newTask);
    }

    /**
     * Used to check the validity of the index.
     * The method will check if the index is a valid index in the task list.
     *
     * @param inputs An array containing the index to be checked.
     *               In the format {index}.
     *               The index is an integer representing the index to be checked.
     *               The index must be a valid index in the task list else it will throw a TaskNotFoundException.
     *               The index must be a valid integer else it will throw an IncorrectFormatException.
     *               The index must be a valid integer else it will throw an InsufficientParametersException.
     * @return index   An integer value of the index.
     */
    private static int checkValidityOfIndex(String[] inputs) throws MimiException.TaskNotFound,
            MimiException.InsufficientParameters, MimiException.IncorrectFormat {
        if (inputs.length != 2) {
            // Throws an error if parameters is incomplete
            throw new MimiException.InsufficientParameters(MimiException.INSUFFICIENT_INDEX_PARAMETERS_MSG);
        }

        try {
            int index = Integer.parseInt(inputs[1]) - 1;
            if (index < 0 || index >= taskList.size()) {
                // Throws an error if task is not found
                throw new MimiException.TaskNotFound(MimiException.TASK_NOT_FOUND_MSG);
            }
            return index;
        } catch (NumberFormatException e) {
            // Throws an error if the format is incorrect
            throw new MimiException.IncorrectFormat(MimiException.INCORRECT_INDEX_FORMAT_MSG);
        }
    }

}
