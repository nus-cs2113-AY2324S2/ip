public class Todo extends Task {

    public Todo (String description) {
        super(description);
        this.taskType = "[T]";
    }
    public String taskInFileFormat() {
        boolean isDone = false;
        if (getStatusIcon().equals("[X]")) {
            isDone = true;
        }
        return "T | " + (isDone ? "1" : "0") + " | " + this.description + "\n";
    }
    @Override
    public void printTask() {
        System.out.println(taskType + getStatusIcon() + " " + description);
    }
}

