public class Todo extends Task {

    /**
     * Constructor for Todo
     *
     * @param description task description
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns task description, whether it has been completed or not and label for Todo subclass
     *
     * @return [T] label, completion status of task and task description as string
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
