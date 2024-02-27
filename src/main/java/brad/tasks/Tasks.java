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

    public String getFullDescription() {
        String output = "[";
        output += (getIsDone() ? "X] " : " ] ");
        output += getTaskDescription();
        return output;
    }

    public String getTime() {
        return "";
    }
}