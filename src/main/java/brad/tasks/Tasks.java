package brad.tasks;

public class Tasks {
    private String description;
    private boolean isDone;

    public Tasks(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public String getTaskDescription() {
        return description;
    }

    /**
     * Get full description of the tasks on the list.
     * Includes type of task and if it is marked as done or not done
     * @return complete task description
     */
    public String getFullDescription() {
        String output = "[";
        output += (getIsDone() ? "X] " : " ] ");
        output += getTaskDescription();
        return output;
    }

    /**
     * Returns any time specified by the user (due time for assignments
     * and event start & end times)
     * @return time if appropriate
     */
    public String getTime() {
        return "";
    }
}