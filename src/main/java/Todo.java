
// subclass of Task
public class Todo extends Task {

    // constructor
    public Todo(String description, int index) {
        super(description, index);
        System.out.println(toString());
    }

    // since overriding occurs, toString() of Todo class is called instead
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
