package hachi.data;

import hachi.data.task.Task;
import hachi.data.task.Event;
import hachi.data.task.Deadline;
import hachi.data.task.TaskType;
import hachi.data.task.Todo;
import hachi.parser.Parser;
import hachi.ui.Ui;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Represents the TaskList for the chatbot Hachi.
 * Contains methods for handling tasks.
 *
 * @author clarencepohh
 * @version 08/03/2024
 */

public class TaskList {
    private final ArrayList<Task> tasksArrayList;
    private final Ui ui;

    /**
     * The standard constructor for TaskList.
     * Initializes the required ArrayList and Ui classes.
     *
     */

    public TaskList () {
        tasksArrayList = new ArrayList<>();
        ui = new Ui();
    }

    /**
     * The constructor for TaskList that takes in an existing ArrayList.
     * Initializes the Arraylist with an existing Arraylist and a new Ui classes.
     *
     * @param tasksArrayList The existing ArrayList that the TaskList takes in.
     */

    public TaskList (ArrayList<Task> tasksArrayList) {
        this.tasksArrayList = tasksArrayList;
        ui = new Ui();
    }

    /**
     * Getter for the TaskList.
     *
     * @return Returns the Task ArrayList.
     */

    public ArrayList<Task> getTasksArrayList() {
        return tasksArrayList;
    }

    /**
     * Getter for the size of the TaskList.
     *
     * @return Returns the size of the Task ArrayList.
     */

    public int getSize () {
        return tasksArrayList.size();
    }

    /**
     * Getter for a task of specific index in the Task ArrayList.
     *
     * @param index Index of the task to be returned.
     * @return Returns the specified task.
     */

    public Task getSpecifiedTask (int index) {
        return tasksArrayList.get(index);
    }

    /**
     * Retrieves the current list of tasks and prints it to
     * the console for the user to see.
     *
     * @throws HachiException If the current list is empty.
     */

    public void retrieveTaskList() throws HachiException{
        int numTasks = Task.getTotalNumTasks();

        HachiException.checkEmptyList(numTasks);
        ui.spacerInsert();
        ui.printTaskList(tasksArrayList);
    }

    /**
     * Given a task's name and the list of tasks, add a new task into the list.
     * Depending on the user's input, can create subclass of tasks: Todos, Deadlines and Events.
     * Prints to the console after a successful addition to the Task ArrayList.
     *
     * @param taskType Type of task to be added. (Todo, Event, Deadline)
     * @param line The line of text given by the user.
     * @param cleanInput The cleaned line of text that will be used to determine the instruction.
     * @throws HachiException If there is an error creating the tasks.
     */

    public void addTask(TaskType taskType, String line, String cleanInput) throws HachiException {
        Task toAdd;
        HachiException.checkValidDescription(line);

        if (taskType == TaskType.TODO) {
            int indexOfTodo = cleanInput.indexOf("TODO") + 5;
            String name = line.substring(indexOfTodo).trim();
            toAdd = new Todo(name);
        } else if (taskType == TaskType.DEADLINE) {
            // parse deadline here
            int indexOfName = cleanInput.indexOf("DEADLINE") + 9;
            int indexOfBy = cleanInput.indexOf("/BY") + 3;
            HachiException.checkDeadlineByDate(indexOfBy, line);

            String name = line.substring(indexOfName, indexOfBy - 3).trim();
            String byDate = line.substring(indexOfBy).trim();
            toAdd = new Deadline(name, byDate);
        } else {
            // parse to and from dates here
            int indexOfName = cleanInput.indexOf("EVENT") + 6;
            int indexOfStart = cleanInput.indexOf("/FROM") + 5;
            int indexOfEnd = cleanInput.indexOf("/TO") + 3;
            HachiException.checkEventDates(indexOfStart, indexOfEnd, line);

            String name = line.substring(indexOfName, indexOfStart - 5);
            String fromDate = line.substring(indexOfStart, indexOfEnd - 3).trim();
            String toDate = line.substring(indexOfEnd).trim();
            toAdd = new Event(name, fromDate, toDate);
        }

        tasksArrayList.add(toAdd);
        ui.printAddTaskMessage(toAdd);
    }

    /**
     * Given a task index, deletes it from the Task ArrayList.
     *
     * @param cleanedInput The String containing the cleaned whole user input.
     * @throws HachiException If the list is empty or the specified task index is out of bounds.
     */

    public void deleteTask(String cleanedInput) throws HachiException {
        int numTasks = Task.getTotalNumTasks();
        HachiException.checkEmptyList(numTasks);
        int taskNumber = Parser.getDeleteTaskNumber(cleanedInput);
        HachiException.checkOutOfBounds(taskNumber);

        Task taskToDelete = tasksArrayList.get(taskNumber - 1);
        String taskType = taskToDelete.getTaskType();
        String statusIcon = taskToDelete.getStatusIcon();
        Task.decrementTotalNumTasks();

        ui.printTaskDeleteMessage(taskType, statusIcon, taskToDelete);
        tasksArrayList.remove(taskNumber - 1);
    }

    /**
     * Given a task's index and the list of tasks,
     * mark that task as complete or incomplete.
     *
     * @param index Index of the task to be marked.
     * @param isMark True if task is to be marked as complete, false otherwise
     */

    public void markOrUnmarkTask(int index, boolean isMark) {
        tasksArrayList.get(index).setCompleteness(isMark);
        ui.printAfterMarkOrUnmark(tasksArrayList, index);
    }

    /**
     * Function that cleans the user input for mark or unmark requests
     * and completes the function call as required.
     *
     * @param cleanedInput Cleaned input string from user.
     */

    public void markOrUnmarkHandler(String cleanedInput) throws HachiException{
        int numTasks = Task.getTotalNumTasks();
        HachiException.checkEmptyList(numTasks);
        int taskNumber = Parser.getMarkTaskNumber(cleanedInput);

        HachiException.checkOutOfBounds(taskNumber);
        markOrUnmarkTask(taskNumber - 1, !cleanedInput.contains("UNMARK"));
    }

    /**
     * Function that adds a task to the task list.
     *
     * @param toAdd The task to be added to the task list.
     */

    public void add (Task toAdd) {
        tasksArrayList.add(toAdd);
    }

    /**
     * Given a specified substring, finds all tasks that contains that substring and
     * returns it to the caller as an ArrayList<Task>.
     *
     * @param cleanedInput The String that contains the cleaned whole user input.
     * @return An ArrayList<Task> that contains all tasks that contains the user specified substring.
     * @throws HachiException If the user input for the 'find' command is invalid.
     */

    public ArrayList<Task> findTask (String cleanedInput) throws HachiException{
        int indexOfFind = cleanedInput.indexOf("FIND") + 5;
        HachiException.checkFindTaskDescription(indexOfFind, cleanedInput);

        String substringToFind = cleanedInput.substring(indexOfFind);

        return (ArrayList<Task>) tasksArrayList.stream()
                .filter((task -> task.getName().toUpperCase().contains(substringToFind)))
                .collect(Collectors.toList());
    }
}
