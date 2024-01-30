public class Task {
    protected String description;
    protected boolean isDone;
    protected int index = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] "); // mark done task with X
    }

    public int getIndex() {
        return index;
    }

    public String getDescription() {
        return description;
    }

    public void isDone(String status) {
        if (status.equals("mark")) {
            this.isDone = true;
        }
        else if (status.equals("unmark")) {
            this.isDone = false;
        }
    }

}

