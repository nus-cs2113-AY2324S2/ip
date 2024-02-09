public class ToDo extends Task {

    //ToDo subclass of Task superclass for item with no deadline or timing
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        String statusMark = this.isCompleted() ? "x" : " "; // Mark with 'x' if completed
        return "[T]" + "[" + statusMark + "] " + super.toString();
    }
}
