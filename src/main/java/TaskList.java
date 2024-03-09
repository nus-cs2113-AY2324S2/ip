import java.util.ArrayList;
import java.util.List;

/**
 * Deals with actions relating to the list of tasks, stored as an ArrayList.
 */
public class TaskList {

    protected static int totalTasks = 0;
    protected static List<Task> list;
    public TaskList(ArrayList<Task> l) {
        list = l;
    }

    public TaskList() {
        list = new ArrayList<>();
    }

    /**
     * Creates a new Task from the inputted string, and adds it to our list.
     * @param str Raw string input from user.
     */
    public static void addToList(String str) {
        Task task = null;
        if (str.contains("deadline")) {
            try {
                String[] split = str.substring(9).split(" /by ");
                task = new Deadline(split[0], split[1]);
            } catch (Exception e) {
                Ui.printThis("Need to specify the deadline using /by");
            }

        } else if (str.contains("event")) {
            try {
                String[] split = str.substring(6).split(" /to | /from ");
                task = new Event(split[0], split[1], split[2]);
            } catch (Exception e) {
                Ui.printThis("Need to specify the time of the event using /from and /to");
            }
        } else if (str.contains("todo")) {
            try {
                task = new Todo(str.substring(5));
            } catch (Exception e) {
                Ui.printThis("Description of a todo cannot be empty!");
            }
        } else {
            Ui.printThis("Task is not in an accepted format.");
        }

        if (task != null) {
            list.add(task);
            totalTasks += 1;
            System.out.println(task.getStatusIcon());
            Ui.printThis("Got it. I've added this task: \n \t" + task.type() + task.toString() +
                    "\nNow you have " + totalTasks + " task" + pluralChecker() + " in the list.");
        }
    }

    /**
     * Actions for non-new items in Task List for the purpose of streamlining the method modifyTask.
     */
    public enum action {
        DELETE, MARK, UNMARK
    }

    /**
     * Modifies tasks in Task List of the form delete, mark, or unmark.
     * @param a action which must be in the enum defined above.
     * @param taskNumber should be the number of the task which this method modifies. Still in string format from the
     *                   user input. If an invalid number, nothing happens.
     */
    public static void modifyTask(action a, String taskNumber) {
        int taskNo;
        try {
            taskNo = Integer.parseInt(taskNumber);
            if (taskNo > TaskList.totalTasks) {
                Ui.printThis("This task number is not in your list");
            } else {
                Task theTask = TaskList.list.get(taskNo - 1);
                switch (a) {
                case DELETE:
                    TaskList.totalTasks -= 1;
                    TaskList.list.remove(taskNo -1);
                    Ui.printThis("Noted. I've removed this task: \n " + theTask.type() + "[" + theTask.getStatusIcon()
                            + "]" + theTask.getDescription() +
                            "\nNow you have " + TaskList.totalTasks + " task" + TaskList.pluralChecker() + " in the list.");
                    break;
                case UNMARK:
                    theTask.markAsUndone();
                    Ui.printThis("OK, I've marked this task as not done yet: \n " + theTask.type()
                            + "[ ] " + theTask.getDescription());
                    break;
                case MARK:
                    theTask.markAsDone();
                    Ui.printThis("Nice! I've marked this task as done: \n " + theTask.type() +
                            "[X] " + theTask.getDescription());
                    break;
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("This is not a valid number");
        }
    }

    /**
     * Searches through Task List for tasks that contain the word the user inputted. Prints out all matches.
     */
    public static void find(String target) {
        List<Task> matches = new ArrayList<Task>();
        for (Task t : list) {
            if (t.description.contains(target)) {
                matches.add(t);
            }
        }
        printList("Here are the matching tasks in your list:", matches);
    }

    /**
     * Checks if the Task List has 1 task, for use in printing out the number of task(s) are in the list.
     */
    public static String pluralChecker() {
        if (totalTasks == 1) {
            return "";
        } else {
            return "s";
        }
    }

    /**
     * Prints the tasks in the given list as well as their status icon.
     */
    public static void printList(String message, List<Task> l) {
        Ui.printLine();
        System.out.println(message);
        int counter = 1;
        for (Task t : l) {
            System.out.println(counter + "." + t.type() + t);
            counter += 1;
        }
        Ui.printLine();
    }
}
