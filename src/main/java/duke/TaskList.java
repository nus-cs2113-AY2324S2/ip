package duke;

import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> parsedTaskList = new ArrayList<>(2);

    public Task get(int i) {
        return parsedTaskList.get(i);
    }

    public void add(Task input) {
        parsedTaskList.add(input);
    }

    public void remove(Task input) {
        parsedTaskList.remove(input);
    }

    public int size() {
        return parsedTaskList.size();
    }

}
