
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
    public void setDone() {
        this.isDone = false;
    }

    public void setCounter(int index) {
        this.index = index;
    }


    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void printHeaders() {
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        //System.out.println(this.toString());
    }

    public String toString() {
        String[] splitLine = description.split("\\s+"); // split if there is 1 or more whitespace
        return "[" + this.getStatusIcon() + "] " + splitLine[1] + ' ' + splitLine[2];
    }

}
