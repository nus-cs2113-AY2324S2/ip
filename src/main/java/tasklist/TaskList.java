package tasklist;
import tasklist.deadline.Deadline;
import tasklist.event.Event;
import tasklist.todo.Todo;
import java.util.ArrayList;

/**
 * Contains the ArrayList where tasks and number of tasks are stored.
 * Adds tasks to the ArrayList.
 * Contains Todo, Deadline and Event class.
 */
public class TaskList {
    protected ArrayList<Todo> list;
    public int taskNum;


    public TaskList(ArrayList<Todo> list, int taskNum) {
        this.list = list;
        this.taskNum = taskNum;
    }

    public ArrayList<Todo> getList() {
        return list;
    }

    public void addTodo(ArrayList<Todo> list, String task, int taskNum) {

        list.add(taskNum, new Todo(task));
    }
    public void addDeadline(ArrayList<Todo> list, String task, int taskNum) {
        int pos = task.indexOf("/");
        String description = task.substring(0, pos);
        String date = task.substring(pos + 3);
        list.add(taskNum, new Deadline(description, date));
    }

    public void addEvent(ArrayList<Todo> list, String task, int taskNum) {
        int pos = task.indexOf("/");
        String description = task.substring(0, pos);
        task = task.substring(pos + 5);
        pos = task.indexOf("/");
        String start = task.substring(0, pos);
        String end = task.substring(pos + 3);
        list.add(taskNum, new Event(description, start, end)) ;
    }
}
