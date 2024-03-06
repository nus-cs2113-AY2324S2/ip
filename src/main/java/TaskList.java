import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private static int OFFSET = -1; //converts from 1-indexing to 0-indexing
    protected static final ArrayList<Task> tasks = new ArrayList<>();

    public int getTaskListSize() {
        return tasks.size();
    }

    public static void addNewTask(String commandWord, String command) throws IOException {
        switch (commandWord) {
        case "todo":
            tasks.add(ToDo.addTodo(command));
            tasks.get(tasks.size() - 1).printTask();
            break;
        case "deadline":
            tasks.add(Deadline.addDeadline(command));
            tasks.get(tasks.size() - 1).printTask();
            break;
        case "event":
            tasks.add(Event.addEvent(command));
            tasks.get(tasks.size() - 1).printTask();
            break;
        }
    }


    public static void deleteTask(String ind) throws IOException {
        int index = Integer.parseInt(ind) + OFFSET;
        System.out.println("Task Deleted: " + tasks.get(index));
        tasks.remove(index);
        Storage.overwriteFile();
    }


    public static void markTask(String ind) {
        int index = Integer.parseInt(ind) + OFFSET;
        tasks.get(index).setDone(true);
    }

    public static void unmarkTask(String ind) {
        int index = Integer.parseInt(ind) + OFFSET;
        tasks.get(index).setDone(false);
    }

    public static void printAllTasks() {
        for (Task t: tasks) {
            t.printTask();
        }
    }

    public static ArrayList<Task> findTasks(String query) {
        ArrayList<Task> results = new ArrayList<>();

       for (Task t: tasks) {
           if (t.description.contains(query)) {
               results.add(t);
           }
       }

       return results;
    }

}
