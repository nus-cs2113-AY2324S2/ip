public class Task {
    protected String label;
    protected boolean isCompleted;

    public Task(String input) {
        this.label = input;
        this.isCompleted = false;
    }

    public String getStatusIcon() {
        return (isCompleted ? "X" : " "); // mark done task with X
    }
    public void toggleCompleted() {
        this.isCompleted = !this.isCompleted;
    }

    public void setCompletedTrue() {
        this.isCompleted = true;
    }

    public void setCompletedFalse() {
        this.isCompleted = false;
    }

    public void printTask() {
        System.out.print("[");
        System.out.print(this.getStatusIcon());
        System.out.print("] ");
        System.out.println(this.label);
    }
}
