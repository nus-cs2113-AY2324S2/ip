import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private static int OFFSET = -1; //converts from 1-indexing to 0-indexing
    protected static final ArrayList<Task> tasks = new ArrayList<>();

    public int getTaskListSize() {
        return tasks.size();
    }

    /**
     * Creates a new Task of various classes based on commandWord
     * @param commandWord the Task subclass to create
     * @param command The description and details of the Task
     */
    public static void addNewTask(String commandWord, String command) {
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


    /**
     * Deletes a task and overwrites the save file, removing it from the save
     * @param ind the index of the Task to be deleted
     * @throws IOException when the new file has error being written to
     */
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

    /**
     * Searches all tasks for any task that contains query in its description
     * @param query the search query
     * @return an ArrayList of Tasks that contains the query
     */
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
