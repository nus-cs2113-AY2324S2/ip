
// Superclass of 3 subclasses: Todo, Deadline and Event
public class Task {
    // 3 class attributes
    protected String description;
    protected boolean isDone;
    protected int index;

    // constructor
    public Task(String description, int index) {
        setDescription(description);
        setDone();
        setCounter(index);
        printHeaders();
    }

    // Set for description attribute
    public void setDescription(String description) {
        this.description = description;
    }

    // Set if task attribute is done/undone
    public void setDone() {
        this.isDone = false;
    }

    // Set index attribute of the task
    public void setCounter(int index) {
        this.index = index;
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

    // Prints from status to end
    // Override the default toString() method
    public String toString() {
        String[] splitLine = description.split("\\s+"); // split if there is 1 or more whitespace
        return "[" + this.getStatusIcon() + "] " + splitLine[1] + ' ' + splitLine[2];
    }

}
