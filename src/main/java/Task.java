public class Task {

    private final String name;
    private boolean hasDone;
    public Task(String task) {
        this.name = task;
        this.hasDone = false;
    }
    public String getName() {
        return this.name;
    }
    public void markDone() {
        this.hasDone = true;
    }

    public void unmarkDone() {
        this.hasDone = false;
    }

    public String getTick() {
        return this.hasDone ? "[X] " : "[ ] ";
    }

}
