public class Task {
    protected String description;
    protected boolean isDone;

    protected String startDate = "";
    protected String endDate = "";
    protected String taskStatus = "";
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    public String getDescription() {
        return this.description;
    }
    public void markAsDone() {
        this.isDone = true;
    }
    public void markAsUndone() {
        this.isDone = false;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getStartDate() {
        return this.startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public String getEndDate() {
        return this.endDate;
    }
    public void setTaskStatus(String status) {
        switch(status) {
            case "todo":
                this.taskStatus = "T";
                break;
            case "deadline":
                this.taskStatus = "D";
                break;
            case "event":
                this.taskStatus = "E";
                break;
            default:
                break;
        }
    }
    public String getTaskStatus() {
        return this.taskStatus;
    }
}
