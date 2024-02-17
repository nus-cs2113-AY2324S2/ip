package Quokka.tasks;

import Quokka.tasks.Task;

public class Todo extends Task {
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public static Todo parseFromString(String data, boolean isDone) {

        String description = data.trim();
        return new Todo(description, isDone);
    }
}