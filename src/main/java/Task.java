
//superclass of 3 subclasses: Todo, Deadline and Event
public class Task {
    // 3 class attributes
    protected String description;
    protected boolean isDone;
    protected int index;

    protected String toString;

    // constructor
    public Task(String description, int index) {
        setDescription(description);
        setDone();
        setCounter(index);
        printHeaders();
    }

    // 3 setter
    public void setDescription(String description) {
        this.description = description;
    }

    // set if task is done/undone
    public void setDone() {
        this.isDone = false;
    }

    //retrieve index of the task
    public void setCounter(int index) {
        this.index = index;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    // standard 2 lines that will always get printed out
    public void printHeaders() {
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
    }

    // prints from status to end
    // override the default toString() method
    public String toString() {
        String[] splitLine = description.split("\\s+"); // split if there is 1 or more whitespace
        return "[" + this.getStatusIcon() + "] " + splitLine[1] + ' ' + splitLine[2];
    }

}
