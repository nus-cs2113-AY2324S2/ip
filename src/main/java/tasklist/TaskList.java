package tasklist;

import tasklist.deadline.Deadline;
import tasklist.event.Event;
import tasklist.todo.Todo;
import java.util.ArrayList;

public class TaskList {

    public TaskList() {
    }

    public static void addTodo(ArrayList<Todo> list, String task, int taskNum) {
        list.add(taskNum, new Todo(task));
    }
    public static void addDeadline(ArrayList<Todo> list, String task, int taskNum) {
        int pos = task.indexOf("/");
        String description = task.substring(0, pos);
        String date = task.substring(pos + 3);
        list.add(taskNum, new Deadline(description, date));
    }

    public static void addEvent(ArrayList<Todo> list, String task, int taskNum) {
        int pos = task.indexOf("/");
        String description = task.substring(0, pos);
        task = task.substring(pos + 5);
        pos = task.indexOf("/");
        String start = task.substring(0, pos);
        String end = task.substring(pos + 3);
        list.add(taskNum, new Event(description, start, end)) ;
    }
}
