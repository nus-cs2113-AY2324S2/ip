package tasks;
public class TaskList {
    protected String description;
    public boolean isDone;

    public TaskList(String description) {
        setDescription(description);
        setDone();
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public static void printHeaders() {
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
    }


    /* Returns the type of task and their action. */
    public String toString() {
        String[] original = description.split("/");
        String value = String.valueOf(original[0]); // Obtain the task and action

        String[] splitLine = value.split("\\s+");
        StringBuilder action = new StringBuilder();

        // Obtain the action of the task
        for (int i = 1; i < splitLine.length; i += 1) {
            action.append(splitLine[i]).append(" ");
        }
        return "[" + this.getStatusIcon() + "] " + action;
    }

}