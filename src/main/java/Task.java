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

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public void printTask() {
        System.out.println(getStatusIcon() + " " + description);
    }

}
