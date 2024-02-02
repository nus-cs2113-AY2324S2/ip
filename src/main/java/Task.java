public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void printTask() {
        char checkBox = ' ';
        if (isDone) {
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

    public boolean checkDone() {
        return isDone;
    }

    public void setDone() {
        isDone = true;
        System.out.print("    Hey, looks like you're done with this task:\n   ");
        printTask();
    }

    public void setUndone() {
        isDone = false;
        System.out.print("    Oops, looks like this task's still not done:\n    ");
        printTask();
    }
}
