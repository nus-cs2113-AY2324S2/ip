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

    //...
}
