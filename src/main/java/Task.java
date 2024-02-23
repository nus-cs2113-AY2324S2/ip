public class Task {
    protected String description;
    protected boolean isDone;

    protected String taskType;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public static int extractNumber(String userCommand) {
        String numberExtracted = userCommand.replaceAll("[^0-9]", "");
        return Integer.parseInt(numberExtracted);
    }
    public void setDone(Boolean status) {
        this.isDone = status;
    }
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public void markAsDoneOrNotDone(String[] arrayOfCommand) {
        if (arrayOfCommand[0].equals("mark")) {
            this.isDone = true;
        } else {
            this.isDone = false;
        }
    }
    public String taskInFileFormat() {
        return getStatusIcon() + " " + this.description;
    }
    public void printTask() {
        System.out.println(getStatusIcon() + " " + description);
    }

}
