package task;

import ui.Parser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class TaskList {
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

    public void addTask(Parser token, String[] parsedInput) {
        if (token == Parser.TODO) {
            tasks.add(new ToDo(parsedInput[0]));
        } else if (token == Parser.DEADLINE) {
            tasks.add(new Deadline(parsedInput[0], parsedInput[1]));
        } else {
            tasks.add(new Event(parsedInput[0], parsedInput[1], parsedInput[2]));
        }
    }

    public void displayAll() {
        IntStream.rangeClosed(1, tasks.size())
                .mapToObj(index -> index + "." + getTask(index))
                .forEach(System.out::println);
    }
}
