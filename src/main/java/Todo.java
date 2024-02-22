public class Todo extends Task {

    protected String by;

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]"  +"[" + this.getStatusIcon()+ "] " + super.toString();
    }

    @Override
    public String toFileString() {
        return "T|" + super.toFileString(); // Prefix with "T" to indicate Todo
    }
}

