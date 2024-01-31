public class Tasks {
    private String task;
    private boolean done;

    public Tasks(String task) {
        this.task = task;
        this.done = false;
    }

    public void mark() {
        this.done = true;
    }

    public void unMark() {
        this.done = false;
    }

    public String toString() {
        if (this.done) {
            return "[X] " + this.task;
        }
        return "[ ] " + this.task;
    }
}
