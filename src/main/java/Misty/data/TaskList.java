package misty.data;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import misty.data.exception.CorruptedFileException;
import misty.data.exception.EmptyParameterException;
import misty.data.exception.IllegalListIndexException;
import misty.data.task.Deadline;
import misty.data.task.Event;
import misty.data.task.Task;
import misty.data.task.Todo;
import misty.storage.Storage;
import misty.ui.UserUi;

/**
 * Creates task list to store tasks.
 */
public class TaskList {
    private ArrayList<Task> taskList;
    private Storage storage;
    private UserUi userUi;

    /**
     * Constructs TaskList object.
     *
     * @param storage Storage object used to save data to hard disk.
     * @param userUi UserUi object used to interact with user.
     */
    public TaskList(Storage storage, UserUi userUi) {
        taskList = new ArrayList<>();
        this.storage = storage;
        this.userUi = userUi;
    }

    /**
     * Prints number of tasks in task list to the screen.
     */
    public void printTaskCount() {
        userUi.printTaskCount(taskList.size());
    }

    /**
     * Prints to screen that a task has been added to task list.
     *
     * @param newTask Task object that has been added to task list.
     */
    private void printAddTaskMessage(Task newTask) {
        userUi.printAddTaskMessage(newTask);
        printTaskCount();
    }

    /**
     * Adds task to task list.
     *
     * @param newTask Task object to be added to task list.
     */
    private void addTask(Task newTask) {
        taskList.add(newTask);
    }

    /**
     * Adds todo task to task list.
     *
     * @param description Name of todo task.
     * @throws EmptyParameterException If name is empty.
     */
    public void addTodo(String description) throws EmptyParameterException {
        if (description.isEmpty()) {
            userUi.printUsageUsageTodo();
            throw new EmptyParameterException();
        }

        Todo newTask = new Todo(description);
        addTask(newTask);

        try {
            storage.saveTodo(newTask);
        } catch (IOException e) {
            userUi.printErrorIO();
        }

        printAddTaskMessage(newTask);
    }

    /**
     * Adds deadline to task list.
     *
     * @param description Name of deadline.
     * @param by Due date of deadline.
     * @throws EmptyParameterException If name or due date is empty.
     */
    public void addDeadline(String description, String by) throws EmptyParameterException {
        if (description.isEmpty() || by.isEmpty()) {
            userUi.printUsageDeadline();
            throw new EmptyParameterException();
        }

        Deadline newTask = new Deadline(description, by);
        addTask(newTask);

        try {
            storage.saveDeadLine(newTask);
        } catch (IOException e) {
            userUi.printErrorIO();
        }

        printAddTaskMessage(newTask);
    }

    /**
     * Adds event to task list.
     *
     * @param description Name of event.
     * @param from Start date of event.
     * @param to End date of event.
     * @throws EmptyParameterException If name, start date or end date is empty.
     */
    public void addEvent(String description, String from, String to) throws EmptyParameterException {
        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            userUi.printUsageEvent();
            throw new EmptyParameterException();
        }

        Event newTask = new Event(description, from, to);
        addTask(newTask);

        try {
            storage.saveEvent(newTask);
        } catch (IOException e) {
            userUi.printErrorIO();
        }

        printAddTaskMessage(newTask);
    }

    /**
     * Marks task, setting it as done.
     *
     * @param index Index of task in task list.
     * @throws IllegalListIndexException If index <= 0 or index > size of task list.
     */
    public void markTask(int index) throws IllegalListIndexException {
        if (index <= 0 || index > taskList.size()) {
            userUi.printUsageMark();
            throw new IllegalListIndexException();
        }

        taskList.get(index - 1).setTaskAsDone();

        try {
            storage.refreshSave(taskList);
        } catch (IOException e) {
            userUi.printErrorIO();
        }

        userUi.printTaskMarkAsDone(taskList.get(index-1));
    }

    /**
     * Unmarks task, setting it as not done.
     *
     * @param index Index of task in task list.
     * @throws IllegalListIndexException If index <= 0 or index > size of task list.
     */
    public void unmarkTask(int index) throws IllegalListIndexException {
        if (index <= 0 || index > taskList.size()) {
            userUi.printUsageUnmark();
            throw new IllegalListIndexException();
        }

        taskList.get(index - 1).setTaskAsNotDone();

        try {
            storage.refreshSave(taskList);
        } catch (IOException e) {
            userUi.printErrorIO();
        }

        userUi.printTaskUnmarkAsNotDone(taskList.get(index - 1));
    }

    /**
     * Deletes task from task list.
     *
     * @param index Index of task in task list.
     * @throws IllegalListIndexException If index <= 0 or index > size of task list.
     */
    public void deleteTask(int index) throws IllegalListIndexException {
        if (index <= 0 || index > taskList.size()) {
            userUi.printUsageDelete();
            throw new IllegalListIndexException();
        }

        Task temp = taskList.get(index - 1);
        taskList.remove(index - 1);

        try {
            storage.refreshSave(taskList);
        } catch (IOException e) {
            userUi.printErrorIO();
        }

        userUi.printDeleteTask(temp);
        printTaskCount();
    }

    /**
     * Prints all tasks in task list to screen.
     */
    public void listAll() {
        userUi.printList(taskList);
    }

    /**
     * Checks which tasks are occurring on a specific date and prints them to screen.
     *
     * @param localDate Date to be compared to tasks.
     */
    public void check(LocalDate localDate) {
        int index = 1;
        boolean isValid = false;
        userUi.printCheckMessage(localDate);
        for (Task task : taskList) {
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                isValid = deadline.isSameDate(localDate);
            } else if (task instanceof Event) {
                Event event = (Event) task;
                isValid = event.isBetweenDate(localDate);
            }

            if (isValid) {
                System.out.println(String.format("\t%d.%s", index, task));
                index++;
            }
        }
    }

    /**
     * Loads todo task from save file.
     *
     * @param description Name of todo task.
     * @throws CorruptedFileException If todo task format is incorrect.
     */
    public void loadTodo(String description) throws CorruptedFileException {
        if (description.isEmpty()) {
            throw new CorruptedFileException();
        }

        Todo newTask = new Todo(description);
        addTask(newTask);
    }

    /**
     * Loads deadline from save file.
     *
     * @param description Name of deadline.
     * @param by Due date of deadline.
     * @throws CorruptedFileException If deadline format is incorrect.
     */
    public void loadDeadline(String description, String by) throws CorruptedFileException {
        if (description.isEmpty() || by.isEmpty()) {
            throw new CorruptedFileException();
        }

        Deadline newTask = new Deadline(description, by);
        addTask(newTask);
    }

    /**
     * Loads event from save file.
     *
     * @param description Name of event.
     * @param from Start date of event.
     * @param to End date of event.
     * @throws CorruptedFileException If event format is incorrect.
     */
    public void loadEvent(String description, String from, String to) throws CorruptedFileException {
        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new CorruptedFileException();
        }

        Event newTask = new Event(description, from, to);
        addTask(newTask);
    }

    /**
     * Marks tasks if it's also marked in save file.
     *
     * @param index Index of task to be marked.
     * @throws CorruptedFileException If index <= 0 or index > size of task list.
     */
    public void loadMark(int index) throws CorruptedFileException {
        if (index <= 0 || index > taskList.size()) {
            throw new CorruptedFileException();
        }

        taskList.get(index - 1).setTaskAsDone();
    }

    /**
     * Finds tasks that matches keyword passed as a parameter.
     *
     * @param keyword String to be compared to tasks.
     * @throws EmptyParameterException If keyword is empty.
     */
    public void find(String keyword) throws EmptyParameterException {
        if (keyword.isEmpty()) {
            userUi.printUsageFind();
            throw new EmptyParameterException();
        }

        userUi.printFindTask();
        String taskName;
        int index = 1;

        for (Task task : taskList) {
            taskName = task.getTaskName();
            if (taskName.contains(keyword)) {
                System.out.println(String.format("\t%d.%s", index, task));
                index++;
            }
        }
    }
}