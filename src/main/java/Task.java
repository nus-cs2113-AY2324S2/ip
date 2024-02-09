public class Task {
    protected String description;
    protected boolean isDone;
    protected String startDate = "";
    protected String endDate = "";
    protected String taskType = "";
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
    public void setTaskType(String status) {
        switch(status) {
            case "todo":
                this.taskType = "T";
                break;
            case "deadline":
                this.taskType = "D";
                break;
            case "event":
                this.taskType = "E";
                break;
            default:
                break;
        }
    }
    public String getTaskType() {
        return this.taskType;
    }
}
