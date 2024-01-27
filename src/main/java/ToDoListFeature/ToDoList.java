package ToDoListFeature;

import java.util.ArrayList;

public class ToDoList {
    private final ArrayList<ToDoItem> toDoListArray;

    public ToDoList() {
        this.toDoListArray = new ArrayList<ToDoItem>();
    }

    public void addItem(ToDoItem item) {
        this.toDoListArray.add(item);
    }

    @Override
    public String toString() {
        if (this.toDoListArray.isEmpty()) {
            return "Empty ToDoList";
        }
        String output = "";
        int count = 1;
        for (ToDoItem item: this.toDoListArray) {
            output += count + ". " + item.toString() + "\n";
            count ++;
        }
        return output.endsWith("\n") ? output.substring(0, output.length()-1) : output;
    }
}
