import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public Task markTask(String key, int index) throws Exception {
        // Marks or unmarks tasks
        Task t;
        try {
            t = tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("That task doesn't exist...");
        }
        if (t.isDone) {
            if (key.equalsIgnoreCase("mark")) { // Catch if the task is already marked
                throw new TaskMarkerException("Task " + (index + 1) + " is already marked done");
            }
        } else {
            if (key.equalsIgnoreCase("unmark")) { // Catch if the task is already unmarked
                throw new TaskMarkerException("Task " + (index + 1) + " is already unmarked");
            }
        }
        t.markDone();
        return t;
    }

    public Task createEvent(String title, LocalDateTime start, LocalDateTime end) throws StringIndexOutOfBoundsException {
        // Creates new event
        Task task;
        task = new Event(title, start, end, "E");
        tasks.add(task);
        return task;
    }

    public Task createDeadline(String title, LocalDateTime dueDate) throws ArrayIndexOutOfBoundsException {
        // Creates new deadline task
        Task task;
        task = new Deadline(title, dueDate, "D");
        tasks.add(task);
        return task;
    }

    public Task createTodo(String title) {
        // Creates new task to do
        Task task;
        task = new Todo(title, "T");
        tasks.add(task);
        return task;
    }

    public Task deleteTask(int index) throws ArrayIndexOutOfBoundsException {
        // Deletes task from the list
        Task task;
        try {
            task = tasks.get(index);
            tasks.remove(index);
        }
        catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("I can't delete a task that isn't there...");
        }
        return task;
    }

    public ArrayList<Task> findByDate(LocalDate date) {
        ArrayList<Task> filteredList = new ArrayList<>();
        try {
            for(Task task : this.tasks) {
                if (task.getEnd() == null) {
                    continue;
                }
                if (task.getEnd().toLocalDate().isEqual(date) || task.getEnd().toLocalDate().isBefore(date)) {
                    filteredList.add(task);
                }
            }
        } catch (NullPointerException e) {
            throw new NullPointerException("You have no tasks due by this date");
        }
        return filteredList;
    }
}
