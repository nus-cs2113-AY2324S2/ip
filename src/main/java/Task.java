public class Task {
    protected String description;
    protected boolean isDone;
    protected String taskTypeLetter;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public int isDoneInt(){
        return (isDone ? 1 : 0);
    }

    public String saveTaskDescription() {
        return (this.taskTypeLetter + "|" + this.isDoneInt() + "|" + this.description.trim());
    }
    @Override
    public String toString() {
        return ("[" + this.getStatusIcon() + "] " + this.description);
    }


    public void printTask(int numberOfListItems) {
        return;
    }
}
