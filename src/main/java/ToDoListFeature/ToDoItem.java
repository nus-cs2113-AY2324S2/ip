package ToDoListFeature;

public class ToDoItem {
    private final String name;

    public ToDoItem(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
