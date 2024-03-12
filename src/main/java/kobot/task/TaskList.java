package kobot.task;

import kobot.ui.Ui;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>();
    
    private static final String EMPTY_STRING = "";
    private static final String LINE_BREAK = "\n";
    private static final String FILE_DELIMITER = ";";
    private static final String TODO_SYMBOL = "T";
    private static final String DEADLINE_SYMBOL = "D";
    private static final String EVENT_SYMBOL = "E";
    
    private static final int TASK_TYPE_INDEX = 0;
    private static final int TASK_IS_DONE_INDEX = 1;
    private static final int TASK_DESCRIPTION_INDEX = 2;
    private static final int DEADLINE_BY_INDEX = 3;
    private static final int EVENT_FROM_INDEX = 3;
    private static final int EVENT_TO_INDEX = 4;

    /**
     * Adds a to-do item to the task list.
     *
     * @param description Description of the task.
     */
    public void addToDo(String description) {
        ToDo newTodo = new ToDo(description);
        taskList.add(newTodo);
        Ui.printAddTaskSuccessMessage("Task", newTodo);
    }

    /**
     * Adds a task that needs to be done by a specific date/time to the task list.
     *
     * @param description Description of the task.
     * @param by Date/Time that the task has to be completed by.
     */
    public void addDeadline(String description, String by) {
        Deadline newDeadline = new Deadline(description, by);
        taskList.add(newDeadline);
        Ui.printAddTaskSuccessMessage("Deadline", newDeadline);
    }

    /**
     * Adds a task that starts and ends at a specific date/time.
     *
     * @param description Description of the task.
     * @param from Date/Time that the task starts.
     * @param to Date/Time that the task ends.
     */
    public void addEvent(String description, String from, String to) {
        Event newEvent = new Event(description, from, to);
        taskList.add(newEvent);
        Ui.printAddTaskSuccessMessage("Event", newEvent);
    }

    /**
     * Prints the entire task list.
     */
    public void printTaskList() {
        System.out.println("Your list:");
        
        int index = 1;
        for (Task task : taskList) {
            System.out.print(index + ". ");
            System.out.println(task);
            index++;
        }
    }
    
    /**
     * Marks a specified task as done.
     *
     * @param index Index of the task to mark as done.
     */
    public void markTask(int index) throws IndexOutOfBoundsException {
        Task task = taskList.get(index);
        task.markAsDone();
        System.out.println("Nice! I've marked this task as done: " + task.getDescription());
    }

    /**
     * Marks a specified task as not done.
     *
     * @param index Index of the task to mark as not done.
     */
    public void unmarkTask(int index) throws IndexOutOfBoundsException {
        Task task = taskList.get(index);
        task.markAsNotDone();
        System.out.println("Okay, I've marked this task as not done yet: " + task.getDescription());
    }

    /**
     * Deletes a specified task from task list.
     *
     * @param index Index of the task to delete.
     */
    public void deleteTask(int index) throws IndexOutOfBoundsException {
        Task task = taskList.get(index);
        taskList.remove(index);
        System.out.println("Item has been deleted: " + task.getDescription());
    }

    /**
     * Parses a line from storage file into the
     * appropriate task object and append to
     * task list.
     * 
     * @param line Stored task data to be added to the task list.
     */
    public void loadTask(String line) {
        String[] data = line.split(FILE_DELIMITER);

        try {
            Task newTask = null;
            switch (data[TASK_TYPE_INDEX]) {
            case TODO_SYMBOL:
                newTask = new ToDo(data[TASK_DESCRIPTION_INDEX]
                        , Boolean.parseBoolean(data[TASK_IS_DONE_INDEX]));
                break;
            case DEADLINE_SYMBOL:
                newTask = new Deadline(data[TASK_DESCRIPTION_INDEX], data[DEADLINE_BY_INDEX]
                        , Boolean.parseBoolean(data[TASK_IS_DONE_INDEX]));
                break;
            case EVENT_SYMBOL:
                newTask = new Event(data[TASK_DESCRIPTION_INDEX], data[EVENT_FROM_INDEX]
                        , data[EVENT_TO_INDEX], Boolean.parseBoolean(data[TASK_IS_DONE_INDEX]));
                break;
            default:
                break;
            }
            taskList.add(newTask);
        } catch (IndexOutOfBoundsException | NullPointerException exception) {
            Ui.printInvalidStorageEntryErrorMessage();
        }
    }

    /**
     * Converts the task list to format used
     * for local storage.
     * 
     * @return List of tasks in storage format.
     */
    public String toStorageFormat() {
        StringBuilder storage = new StringBuilder(EMPTY_STRING);
        
        for (Task task:taskList) {
            storage.append(task.toStorageFormat());
            storage.append(LINE_BREAK);
        }
        
        return String.valueOf(storage);
    }

    /**
     * Prints out list of tasks that contains the keyword.
     *
     * @param keyword Keyword to search for.
     */
    public void findTasks(String keyword) {
        System.out.println("Tasks containing \"" + keyword + "\":");
        
        int index = 1;
        for (Task task:taskList) {
            if (task.getDescription().contains(keyword)) {
                System.out.print(index + ". ");
                System.out.println(task);
                index++;
            }
        }
    }
}
