public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void printTask() {
        char checkBox = ' ';
        if(isDone) {
            checkBox = 'X';
        }
        System.out.println("["+ checkBox + "] " + description);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone() {
        isDone = true;
        System.out.println("Hey, looks like you're done with this task:");
        printTask();
    }

    public void setUndone() {
        isDone = false;
        System.out.println("Oops, looks like this task's still not done:");
        printTask();
    }
}
