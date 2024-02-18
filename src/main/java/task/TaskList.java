package task;

import ui.Parser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TaskList implements Serializable {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public Task getTask(int index) {
        return this.tasks.get(index - 1);
    }

    public int size() {
        return this.tasks.size();
    }

    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    public void addTask(Parser token, String[] parsedInput) {
        if (token == Parser.TODO) {
            tasks.add(new ToDo(parsedInput[0]));
        } else if (token == Parser.DEADLINE) {
            tasks.add(new Deadline(parsedInput[0], parsedInput[1]));
        } else {
            tasks.add(new Event(parsedInput[0], parsedInput[1], parsedInput[2]));
        }
    }

    public void deleteTask(int index) {
        this.tasks.remove(index - 1);
    }

    public void displayAll() {
        for (int index = 1; index <= this.size(); index++) {
            System.out.println(index + "." + getTask(index));
        }
    }
}
