import java.util.ArrayList;
import java.util.List;

public class TaskList {

    protected static int totalTasks = 0;
    protected static List<Task> list;
    public TaskList(ArrayList<Task> l) {
        list = l;
    }

    public TaskList() {
        list = new ArrayList<>();
    }

    // Creates a new Task from the inputted string, and adds it to our list.
    public static void addToList(String str) {
        Task task;
        if (str.contains("deadline")) {
            try {
                String[] split = str.substring(9).split(" /by ");
                task = new Deadline(split[0], split[1]);
            } catch (Exception e) {
                Ui.printThis("Need to specify the deadline using /by");
                task = null;
            }

        } else if (str.contains("event")) {
            try {
                String[] split = str.substring(6).split(" /to | /from ");
                task = new Event(split[0], split[1], split[2]);
            } catch (Exception e) {
                Ui.printThis("Need to specify the time of the event using /from and /to");
                task = null;
            }
        } else if (str.contains("todo")) {
            try {
                task = new Todo(str.substring(5));
            } catch (Exception e) {
                Ui.printThis("Description of a todo cannot be empty!");
                task = null;
            }
        } else {
            Ui.printThis("Task is not in an accepted format.");
            task = null;
        }

        if (task != null) {
            list.add(task);
            totalTasks += 1;
            System.out.println(task.getStatusIcon());
            Ui.printThis("Got it. I've added this task: \n \t" + task.type() + task.toString() +
                    "\nNow you have " + totalTasks + " task" + pluralChecker() + " in the list.");
        }
    }

    public enum action {
        DELETE, MARK, UNMARK
    }

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

    public static String pluralChecker() {
        if (totalTasks == 1) {
            return "";
        } else {
            return "s";
        }
    }

    // Prints the tasks in the list as well as their status icon.
    public static void printList() {
        Ui.printLine();
        System.out.println("Here are the tasks in your list:");
        int counter = 1;
        for (Task t : list) {
            System.out.println(counter + "." + t.type() + t.toString());
            counter += 1;
        }
        Ui.printLine();
    }
}
