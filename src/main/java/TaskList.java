import java.util.ArrayList;
import java.util.Objects;

public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<Task>();
    private int listSize = 0;
    public static final String OUTPUT_INDENTATION = "    ";

    /**
     * Add a task to the list
     *
     * @param task The task to be added
     */
    public void addTask(Task task) {
        taskList.add(task);
        listSize++;
    }

    /**
     * Get the size of the list
     *
     * @return the size of the list
     */
    public int getListSize() {
        return listSize;
    }

    /**
     * Get a task from the list
     *
     * @param index The index of the task in the list
     * @return the task at the input index
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Load the task list from the file
     */
    public void printHelpMessage() {
        System.out.println("Here are the commands you can use:");
        System.out.println(OUTPUT_INDENTATION + "todo <description> - Add a todo task");
        System.out.println(OUTPUT_INDENTATION + "deadline <description> /by <date> - Add a deadline task");
        System.out.println(OUTPUT_INDENTATION + "event <description> /from <date> /to <date> - Add an event task");
        System.out.println(OUTPUT_INDENTATION + "list - List all the tasks");
        System.out.println(OUTPUT_INDENTATION + "mark <task number> - Mark a task as done");
        System.out.println(OUTPUT_INDENTATION + "unmark <task number> - Mark a task as not done");
        System.out.println(OUTPUT_INDENTATION + "delete <task number> - Delete a task");
        System.out.println(OUTPUT_INDENTATION + "find <keyword> - Find tasks with the keyword");
        System.out.println(OUTPUT_INDENTATION + "bye - Exit the program");
    }

    /**
     * Add a todo to the taskList
     *
     * @param description The description of the todo
     */
    public void addTodo(String description) {
        Todo taskTodo = new Todo(description);
        addTask(taskTodo);
        System.out.println(OUTPUT_INDENTATION + "Awesome! Something to do without deadline hehe\n"
                + OUTPUT_INDENTATION + "  " + taskTodo + "\n"
                + OUTPUT_INDENTATION + "You better not procrastinate... or maybe you should");
    }

    /**
     * Add a deadline to the taskList
     *
     * @param description The description of the deadline
     */
    public void addDeadline(String description) {
        int indexBy = description.indexOf("/by");
        String task = description.substring(0, indexBy).trim();
        String by = description.substring(indexBy + 4);
        Deadline taskDeadline = new Deadline(task, by);
        addTask(taskDeadline);
        System.out.println(OUTPUT_INDENTATION + "Oh wow... a deadline, how exciting :)\n"
                + OUTPUT_INDENTATION + "  " + taskDeadline+ "\n"
                + OUTPUT_INDENTATION + "A deadline a day keeps the sanity away.");
    }

    /**
     * Add an event to the taskList
     *
     * @param description The description of the event
     */
    public void addEvent(String description) {
        int indexFrom = description.indexOf("/from");
        int indexTo = description.indexOf("/to");
        String task = description.substring(0, indexFrom).trim();
        String from = description.substring(indexFrom + 6, indexTo).trim();
        String to = description.substring(indexTo + 4).trim();
        Event taskEvent = new Event(task, from, to);
        addTask(taskEvent);
        System.out.println(OUTPUT_INDENTATION + "Event... yeay.\n"
                + OUTPUT_INDENTATION + "  " + taskEvent + "\n"
                + OUTPUT_INDENTATION + "Can it BE any more fun?");
    }

    /**
     * Delete a task from the list
     *
     * @param task_number The index of the task to be deleted
     * @throws ChandlerException If the task number is invalid
     */
    public void deleteTask(int task_number) throws ChandlerException {
        if (task_number < 0) {
            throw new ChandlerException("Invalid task number");
        }
        if (task_number >= listSize){
            throw new ChandlerException("Task number exceeds the list size");
        }
        listSize--;
        System.out.println(OUTPUT_INDENTATION + "YES, less things to do! I've removed this task:");
        System.out.println(OUTPUT_INDENTATION + " " + taskList.get(task_number));
        System.out.println(OUTPUT_INDENTATION + "Now you have " + (listSize) + " tasks in the list.");
        taskList.remove(task_number);
    }

    /**
     * Mark a task as done
     *
     * @param task_number The index of the task to be marked as done
     * @throws ChandlerException If the task number is invalid
     */
    public void markTaskAsDone(int task_number) throws ChandlerException {
        if (task_number < 0) {
            throw new ChandlerException("Invalid task number");
        }
        if (task_number >= listSize) {
            throw new ChandlerException("Task number exceeds the list size");
        }
        if (taskList.get(task_number).isDone) {
            System.out.println(OUTPUT_INDENTATION + "But, you have already done the task.");
            System.out.println(OUTPUT_INDENTATION + "I can totally see why you would mark it again.");
        } else {
            System.out.println(OUTPUT_INDENTATION + "Nice! I've marked this task as done:");
            taskList.get(task_number).markAsDone();
            System.out.println(OUTPUT_INDENTATION + taskList.get(task_number));
        }
    }

    /**
     * Mark a task as not done
     *
     * @param task_number The index of the task to be marked as not done
     * @throws ChandlerException If the task number is invalid
     */
    public void markTaskAsUndone(int task_number) throws ChandlerException {
        if (task_number < 0) {
            throw new ChandlerException("Invalid task number");
        }
        if (task_number >= listSize) {
            throw new ChandlerException("Task number exceeds list size");
        }
        if (!taskList.get(task_number).isDone) {
            System.out.println(OUTPUT_INDENTATION + "You must be enjoying the task so much :)");
            System.out.println(OUTPUT_INDENTATION + "Because you are unmarking an unmarked task.");
        } else {
            System.out.println(OUTPUT_INDENTATION + "Ok, I've marked this task as not done yet:");
            taskList.get(task_number).markAsUndone();
            System.out.println(OUTPUT_INDENTATION + taskList.get(task_number));
        }
    }

    /**
     * Find tasks with the input keyword
     *
     * @param keyword The keyword to be searched among the tasks in the list
     */
    public void findMatchingTasks(String keyword) {
        System.out.println("I'm hopeless and awkward and desperate for love!");
        System.out.println("I mean... here are the matching tasks in your list.");
        int matchingTasks = 0;
        for (int index = 0; index < listSize; index++) {
            if (taskList.get(index).getDescription().contains(keyword)) {
                matchingTasks++;
                System.out.println(OUTPUT_INDENTATION + matchingTasks + "." + taskList.get(index));
            }
        }
        if (matchingTasks <= 0) {
            System.out.println("Oops, there is no task that contains the keyword: " + keyword);
        } else {
            System.out.println("You have " + matchingTasks + " matching tasks in the list.");
        }
    }

    /**
     * List all the tasks in the list
     */
    public void listTasks() {
        if (listSize <= 0) {
            System.out.println("You currently do not have any tasks.");
            System.out.println("Let's keep it that way shall we? :)");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int index = 0; index < listSize; index++) {
                System.out.println(OUTPUT_INDENTATION + (index + 1) + "." + taskList.get(index));
            }
        }
    }
}
