// Subclass of Task
public class Todo extends Task {

    // Constructor
    public Todo(String description, int index) {
        super(description, index);

        System.out.println(toString());
    }

    // Override task's toString() to add [T]
    @Override
    public String toString() {
        if (super.toString() != null) {
            //printHeaders();
            return "[T]" + super.toString();
        }
        return null;
    }
}
