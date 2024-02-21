public class Task {
        protected String description;
    protected boolean isDone;


    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println(getTypeIcon() + getStatusIcon() + this.description);
    }

    public void markAsDoneWithoutPrints() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
        System.out.println(getTypeIcon() + getStatusIcon() + this.description);
    }

    public void markAsUndoneWithoutPrints() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public void printTask() {
        return;
    }

    public String getTypeIcon(){
        return "?";
    }
}