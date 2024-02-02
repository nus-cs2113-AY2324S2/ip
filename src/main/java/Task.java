public class Task {

    private final String name;
    private boolean done;
    public Task(String task) {
        this.name = task;
        this.done = false;
    }
    public String getName() {
        return this.name;
    }
    public void markDone() {
        this.done = true;
    }

    public void unmarkDone() {
        this.done = false;
    }

    public String getTick() {
        return this.done ? "[X] " : "[ ] ";
    }

}
