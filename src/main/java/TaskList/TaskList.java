package TaskList;

import tasks.Task;
import tasks.ToDo;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }


    public void add(Task newEntry) {
        tasks.add(newEntry);
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    public void remove(int index) {
        tasks.remove(index);
    }

    public ArrayList<Task> getAll() {
        return tasks;
    }
}
