public class Task {
    protected String description;
    protected Boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markTask() {
        this.isDone = true;
    }

    public void unmarkTask() {
        this.isDone = false;
    }

    public Boolean isDone() {
        return isDone;
    }

    public String getType() {
        return "T";
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * returns "X" if task is mark done and
     * " " if not marked
     *
     *
     * @return "X" or " "
     */
    protected String getStatusIcon() {
        if (isDone) {
            return "X";
        }
        return " ";
    }

    public String getDescription() {
        return description;
    }
}