import java.util.ArrayList;
import java.util.Objects;

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

    // Get the task
    public Task getTask(int index) {
        return taskList.get(index);
    }

    // Add a todo to the taskList
    public void addTodo(String description) {
        Todo taskTodo = new Todo(description);
        addTask(taskTodo);
        System.out.println(OUTPUT_INDENTATION + "Awesome! Something to do without deadline hehe\n"
                + OUTPUT_INDENTATION + "  " + taskTodo + "\n"
                + OUTPUT_INDENTATION + "You better not procrastinate... or maybe you should");
    }

    // Add a deadline to the taskList
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

    // Add an event to the taskList
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
        if (task_number < 0) {
            throw new ChandlerException("Invalid task number");
        }
        if (task_number >= listSize) {
            throw new ChandlerException("Task number exceeds list size");
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

    // Mark a task as undone
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

    // List all the tasks in the list
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
