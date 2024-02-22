public abstract class Task {
    protected String label;
    protected boolean isCompleted;

    public Task(String input) {
        this.label = input;
        this.isCompleted = false;
    }

    public String getStatusIcon() {
        return (isCompleted ? "X" : " "); // mark done task with X
    }


    public void setCompleted(boolean value) {
        this.isCompleted = value;
    }
    public abstract String getType();
    public abstract String getLabel();
    public abstract String getRange();

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + label;
    }
}
