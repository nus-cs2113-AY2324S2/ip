package allez;

import allez.task.Deadline;
import allez.task.Event;
import allez.task.Task;
import allez.task.ToDo;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    public TaskList(Storage storage) {
        tasks = storage.loadSave();
    }

    public int numberOfTasks() {
        return tasks.size();
    }
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void addToDo(String[] lineSegment) {
        String description = lineSegment[0];
        tasks.add(new ToDo(description));
    }

    public void addDeadline(String[] lineSegment) {
        String description = lineSegment[0];
        String by = lineSegment[1];
        tasks.add(new Deadline(description,by));
    }

    public void addEvent(String[] lineSegment) {
        String description = lineSegment[0].trim();
        String from = lineSegment[1].trim();
        String to = lineSegment[2].trim();
        tasks.add(new Event(description, from, to));
    }

    public void deleteTask(int toDelete) {
        tasks.remove(toDelete);

    }

    public Task getTask(int taskNumber) {
        return tasks.get(taskNumber);
    }
    
}
