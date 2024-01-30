public class Task {
    protected boolean isCompleted;
    protected String label;

    public Task(String input) {
        this.label = input;
        this.isCompleted = false;
    }
}
