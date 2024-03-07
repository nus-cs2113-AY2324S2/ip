package bobby;

import java.util.ArrayList;

public class TaskList {
    private final Ui ui = new Ui();
    public ArrayList<Task> list;

    public TaskList() {
        list = new ArrayList<Task>();
    }

    public void addTodo(String description) {
        list.add(new Todo(description, false));
    }

    public void addDeadline(String description, String by) {
        list.add(new Deadline(description, false, by));
    }

    public void addEvent(String description, String by, String from) {
        list.add(new Event(description, false, by, from));
    }

    public void deleteTask(int entry) {
        list.remove(entry - 1);
    }

    public void markTask(int entry) {
        list.get(entry - 1).setDone(true);
    }

    public void unmarkTask(int entry) {
        list.get(entry - 1).setDone(false);
    }

    public void findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : list) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        if (matchingTasks.isEmpty()) {
            ui.showNoMatchMessage();
        } else {
            ui.showMatchMessage();
            ui.showList(matchingTasks);
        }
    }
}
