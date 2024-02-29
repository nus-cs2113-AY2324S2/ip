public class Todo extends Task {

    public Todo(String description) {
            super(description);
            if (description.trim().equalsIgnoreCase("todo")) {
                throw new IllegalArgumentException();
            }
            this.description = description.substring(5).trim(); // Remove "todo" and trim
            this.taskType = "[T]";
    }
    public Todo(boolean isDone, String description) {
        super(description);
        this.isDone = isDone;
        this.taskType = "[T]";
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public String toFileString() {
        return "T | " + super.toFileString();
    }
}