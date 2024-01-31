public class Tasks {
    /** The description of the task */
    private String task;
    /** Whether the task is done or not */
    private boolean isChecked;

    /**
     * Constructor of the Tasks class.
     *
     * @param task the string description of the task.
     */
    public Tasks(String task) {
        this.task = task;
        this.isChecked = false;
    }

    /**
     * Change the status of the task to done.
     */
    public void mark() {
        this.isChecked = true;
    }

    /**
     * Change the status of the task to undone.
     */
    public void unMark() {
        this.isChecked = false;
    }

    @Override
    public String toString() {
        if (this.isChecked) {
            return "[X] " + this.task;
        }
        return "[ ] " + this.task;
    }
}
