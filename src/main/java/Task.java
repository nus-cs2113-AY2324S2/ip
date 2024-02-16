public class Task {
    protected boolean isDone;
    protected String description;
    protected String type;

    public Task() {
        this(false,"");
    }

    public Task(boolean status, String s) {
        this.isDone = status;
        this.description = s;
        this.type = "T";
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     *
     * @return boolean isDone of Task
     */
    public boolean getStatus() {
        return this.isDone;
    }

    /**
     *
     * @return String description of Task
     */
    public String getDescription() {
        return this.description;
    }

    public void printTask() {
        if (isDone) {
            System.out.println("[" + this.type + "][X] " + this.getDescription());
        } else {
            System.out.println("[" + this.type + "][ ] " + this.getDescription());
        }
    }
}
