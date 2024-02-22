public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String desc) {
        this.description = desc;
        this.isDone = false;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDone(boolean done) {
        this.isDone = done;
        if (done) {
            System.out.println("Well done! This task is marked as done:");
            this.printTask();
        } else {
            System.out.println("This task is marked as undone:");
        }
        this.printTask();
    }

    public void printTask() {
        System.out.println(this);
    }

    public String printDone() {
       return (this.isDone ? "X" : " ");
    }

    @Override
    public String toString() {
        return "[" + this.printDone() + "] " + this.description;
    }
}
