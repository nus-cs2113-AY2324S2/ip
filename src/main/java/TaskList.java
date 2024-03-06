import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<Task>();
    private int listSize = 0;
    public static final String OUTPUT_INDENTATION = "    ";

    // Add a task to the list
    public void addTask(Task task) {
        taskList.add(task);
        listSize++;
    }

    // Get size of the list
    public int getListSize() {
        return listSize;
    }

    // Get the task list
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    // Get the task
    public Task getTask(int index) {
        return taskList.get(index);
    }

    // Add a string type task to the list
    public void addTask (String taskType, String description) throws ChandlerException{
        // Error when no task has been specified
        if (description == "") {
            throw new ChandlerException("You need to specify if it's a todo, deadline or event.");
        }

        switch (taskType.toUpperCase()) {
            case "T":
                addTodo(description);
                break;
            case "D":
                addDeadline(description);
                break;
            case "E":
                addEvent(description);
                break;
            default:
                System.err.println("Invalid task type");
        }
    }
    // Add a todo to the taskList
    public void addTodo (String description) {
        Todo taskTodo = new Todo(description);
        addTask(taskTodo);
        System.out.println(
                OUTPUT_INDENTATION + "Awesome! Something to do without deadline hehe\n" +
                        OUTPUT_INDENTATION + "  " + taskTodo + "\n" +
                        OUTPUT_INDENTATION + "You better not procrastinate... or maybe you should");
    }

    // Add a deadline to the taskList
    public void addDeadline (String description) {
        int indexBy = description.indexOf("/by");
        String task = description.substring(0, indexBy);
        String by = description.substring(indexBy + 4);
        Deadline taskDeadline = new Deadline(task, by);
        addTask(taskDeadline);
        System.out.println(
                OUTPUT_INDENTATION + "Oh wow... a deadline, how exciting :)\n" +
                        OUTPUT_INDENTATION + "  " + taskDeadline+ "\n" +
                        OUTPUT_INDENTATION + "A deadline a day keeps the sanity away.");
    }

    // Add an event to the taskList
    public void addEvent (String description) {
        int indexFrom = description.indexOf("/from");
        int indexTo = description.indexOf("/to");
        String task = description.substring(0, indexFrom);
        String from = description.substring(indexFrom + 6, indexTo);
        String to = description.substring(indexTo + 4);
        Event taskEvent = new Event(task, from, to);
        addTask(taskEvent);
        System.out.println(
                OUTPUT_INDENTATION + "Event... yeay.\n" +
                        OUTPUT_INDENTATION + "  " + taskEvent + "\n" +
                        OUTPUT_INDENTATION + "Can it BE any more fun?");
    }

    // Delete a task from the list
    public void deleteTask(int task_number) throws ChandlerException {
        if (task_number < 0 || task_number >= listSize) {
            throw new ChandlerException("Invalid task number");
        }
        listSize--;
        System.out.println(OUTPUT_INDENTATION + "YES, less things to do! I've removed this task:");
        System.out.println(OUTPUT_INDENTATION + " " + taskList.get(task_number));
        System.out.println(OUTPUT_INDENTATION + "Now you have " + (listSize) + " tasks in the list.");
        taskList.remove(task_number);
    }

    // Mark a task as done
    public void markTaskAsDone(int task_number) throws ChandlerException {
        if (task_number < 0 || task_number >= listSize) {
            throw new ChandlerException("Invalid task number");
        }
        System.out.println(OUTPUT_INDENTATION + "Nice! I've marked this task as done:");
        taskList.get(task_number).markAsDone();
        System.out.println(OUTPUT_INDENTATION + taskList.get(task_number));
    }

    // Mark a task as undone
    public void markTaskAsUndone(int task_number) throws ChandlerException {
        if (task_number < 0 || task_number >= listSize) {
            throw new ChandlerException("Invalid task number");
        }
        System.out.println(OUTPUT_INDENTATION + "Ok, I've marked this task as not done yet:");
        taskList.get(task_number).markAsUndone();
        System.out.println(OUTPUT_INDENTATION + taskList.get(task_number));
    }

    // List all the tasks in the list
    public void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int index = 0; index < listSize; index++) {
            System.out.println(OUTPUT_INDENTATION + (index+1) + "." + taskList.get(index));
        }
    }

}
