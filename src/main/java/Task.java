public class Task {
    protected String task;
    protected boolean isDone;

    public Task (String task) {
        this.task = task;
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = !isDone;
    }

    public String getTaskType() {
        return "";
    }

    public String getDeadline() {
        return "";
    }

    public String getStart() {
        return "";
    }

    public String getEnd() {
        return "";
    }

    public void iji() {
        return;
    }
}
