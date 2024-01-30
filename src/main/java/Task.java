public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String desc) {
        this.description = desc;
        this.isDone = false;
    }

    public void toggleDone() {
        this.isDone = !this.isDone;
    }

    public void printTask() {
        System.out.print("[");
        System.out.print(this.printDone());
        System.out.print("] ");
        System.out.println(this.description);
    }

    public String printDone() {
       return (this.isDone ? "X" : " ");
    }
}
