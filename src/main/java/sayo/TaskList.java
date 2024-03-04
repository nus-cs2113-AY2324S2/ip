package sayo;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private ArrayList<Task> items;

    public TaskList() {
        items = new ArrayList<>();
    }

    public TaskList(List<String> lines) throws SayoException {
        items = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split(" \\| ");
            Task task;
            switch (parts[0]) {
                case "T":
                    task = ToDo.fromFileFormat(line);
                    break;
                case "D":
                    task = Deadline.fromFileFormat(line);
                    break;
                case "E":
                    task = Event.fromFileFormat(line);
                    break;
                default:
                    throw new SayoException("Unknown task type in file: " + parts[0]);
            }
            items.add(task);
        }
    }

    public void addTask(Task task) {
        items.add(task);
    }

    public Task removeTask(int index) {
        return items.remove(index);
    }

    public Task getTask(int index) {
        return items.get(index);
    }

    public int getSize() {
        return items.size();
    }

    public ArrayList<Task> getTasks() {
        return items;
    }

    public ArrayList<Task> findTasksByKeyword(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task: this.items) {
            if (task.toString().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

}