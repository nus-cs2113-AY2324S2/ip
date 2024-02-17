// Superclass of 3 subclasses: Todo, Deadline and Event

package tasks;
public class Task {
    // 3 class attributes
    protected String description;
    public boolean isDone;

    // constructor
    public Task(String description, boolean newInput) {
        setDescription(description);
        setDone();
        //setCounter(index);
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

    // Standard 2 lines that will always get printed out
    public void printHeaders() {
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
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