import java.util.ArrayList;
import java.io.IOException;

/**
 * Contains the task list and has operations to add/delete tasks in the list
 */

public class TaskList {
    private static int count;
    private static ArrayList<Task> tasks;

    public TaskList() {
        count = 0;
        tasks = new ArrayList<Task>();
    }

    /**
     * Retrieves the Arraylist of tasks.
     *
     * @return the tasks variable
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds a task to tasks
     *
     * @param t     the task to add
     * @param prnt  flag to determine if ui displayed
     */
    public static void addTask(Task t, boolean prnt) {
        tasks.add(t);
        count += 1;
        if (prnt) {
            Ui.printAddTask(count, t);
        }
    }

    /**
     * Deletes a task from the task list.
     *
     * @param arr the input array
     */
    public void delete(String[] arr) {
        try{
            int delNum = Parser.markDeleteParse(arr, this.count);
            tasks.remove(delNum);
            count -= 1;
            Ui.printNumTasks(count);
        } catch (IllegalArgumentException e) {
            Ui.markDeleteFormatError("delete");
            return;
        }
    }


    /**
     * Marks a task as completed in tasks.
     *
     * @param arr the input array
     */
    public void mark(String[] arr) {
        try{
            int markNum = Parser.markDeleteParse(arr, this.count);
            tasks.get(markNum).markTask(true);
        } catch (IllegalArgumentException e) {
            Ui.markDeleteFormatError("mark");
            return;
        }
    }

    /**
     * Marks a task as incompleted in tasks.
     *
     * @param arr the input array
     */
    public void unmark(String[] arr) {
        try{
            int markNum = Parser.markDeleteParse(arr, this.count);
            tasks.get(markNum).unmarkTask();
        } catch (IllegalArgumentException e) {
            Ui.markDeleteFormatError("unmark");
            return;
        }
    }

    /**
     * Adds a Todo task to tasks.
     *
     * @param arr the input array
     */
    public void addTodo(String[] arr) {
        try {
            Task t = Parser.todoParse(arr);
            addTask(t, true);
            try {
                Storage.writeToFile(t);
            } catch (IOException e) {
                Ui.printError(e.getMessage());
            }
        } catch (IllegalArgumentException e) {
            Ui.taskFormatError("todo");
            return;
        }
    }

    /**
     * Adds a Deadline task to tasks.
     *
     * @param arr the input array
     */
    public void addDeadline(String[] arr) {
        try {
            Task t = Parser.deadlineParse(arr);
            addTask(t, true);
            try {
                Storage.writeToFile(t);
            } catch (IOException e) {
                Ui.printError(e.getMessage());
            }
        } catch (IllegalArgumentException e) {
            Ui.taskFormatError("deadline");
            return;
        }
    }

    /**
     * Adds a Event task to tasks.
     *
     * @param arr the input array
     */
    public void addEvent(String[] arr) {
        try {
            Task t = Parser.eventParse(arr);
            addTask(t, true);
            try {
                Storage.writeToFile(t);
            } catch (IOException e) {
                Ui.printError(e.getMessage());
            }
        } catch (IllegalArgumentException e) {
            Ui.taskFormatError("event");
            return;
        }
    }


    /**
     * Searches for tasks containing a specific keyword in the task list.
     *
     * @param arr the input array
     */
    public void find(String[] arr) {
        try {
            String s = Parser.findParse(arr);
            ArrayList<Task> found = new ArrayList<Task>();
            for(Task t: this.tasks) {
                if(t.getDescription().contains(s)) {
                    found.add(t);
                }
            }
            Ui.printFound(found);
        } catch (IllegalArgumentException e) {
            Ui.findError();
            return;
        }
    }
}
