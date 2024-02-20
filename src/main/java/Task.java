public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markNotDone() {
        this.isDone = false;
    }

    public void showTask() {
        if (this.getClass().toString().equals("class Todo")) {
            System.out.print("[T]");
        }
        else if (this.getClass().toString().equals("class Deadline")) {
            System.out.print("[D]");
        }
        else {
            System.out.print("[E]");
        }
        System.out.println("[" + (isDone ? "X" : " ") + "]" + this.description);
    }

}
