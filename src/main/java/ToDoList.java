import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ToDoList {
    private final List<String> tasks;
    private final String sep = "____________________________________________________________";

    public ToDoList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(String task) {
        tasks.add(task);
    }

    public void printTasks() {
        System.out.println("Todo List:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        System.out.println(sep);
    }
}

