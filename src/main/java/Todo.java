
// subclass of Task
public class Todo extends Task {

    // constructor
    public Todo(String description, int index) {
        super(description, index);
        System.out.println(toString());
    }

    // override task's toString() to add [T]
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
