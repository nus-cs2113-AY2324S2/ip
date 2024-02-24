package tasks;
public class TaskList {
    protected String description;
    public boolean isDone;

    // constructor
    public TaskList(String description) {
        setDescription(description);
        setDone();
    }

    // Set for description attribute
    public void setDescription(String description) {
        this.description = description;
    }

    // Set if task attribute is done/undone
    public void setDone() {
        this.isDone = false;
    }

    // Get status icon
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }


    public String toString() {
        String[] original = description.split("/"); // split the original input by /
        String value = String.valueOf(original[0]); //obtain the task and action

        String[] splitLine = value.split("\\s+"); //split the words by whitespace
        StringBuilder action = new StringBuilder();

        //obtain the action of the task
        for (int i = 1; i < splitLine.length; i += 1) {
            action.append(splitLine[i]).append(" ");
        }
        return "[" + this.getStatusIcon() + "] " + action;
    }

}