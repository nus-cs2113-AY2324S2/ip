package ToDoListFeature;

public class ToDoItem {
    private final String name;
    private boolean isDone;

    public ToDoItem(String name) {
        this.name = name;
        this.isDone = false;
    }

    public String getName() {
        return this.name;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }


    @Override
    public String toString() {
        return isDone ? "[X] " + this.name : "[ ] " + this.name;
    }

}
