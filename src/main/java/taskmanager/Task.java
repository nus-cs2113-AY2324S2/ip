package taskmanager;

import newexceptions.InvalidGetException;

public class Task {
    protected String description = "";
    protected boolean isDone = false;
    protected String startDate = "";
    protected String endDate = "";
    protected String taskType = "";
    public Task(String description) {
        this.description = description;
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }
    public String getDescription() {
        try {
            if (this.description.isEmpty() || this.description == null) {
                throw new InvalidGetException();
            } else {
                return this.description;
            }
        } catch (InvalidGetException e){
            Messages.invalidTaskAttributeMessage();
            return this.description;
        }
    }
    public void markAsDone() { // mark done task with X
        this.isDone = true;
    }
    public void markAsUndone() { // unmark task by removing X
        this.isDone = false;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getStartDate() {
        try {
            if (this.startDate.isEmpty() || this.startDate == null) {
                throw new InvalidGetException();
            } else {
                return this.startDate;
            }
        } catch (InvalidGetException e){
            Messages.invalidTaskAttributeMessage();
            return this.startDate;
        }
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public String getEndDate() {
        try {
            if (this.endDate.isEmpty() || this.endDate == null) {
                throw new InvalidGetException();
            } else {
                return this.endDate;
            }
        } catch (InvalidGetException e){
            Messages.invalidTaskAttributeMessage();
            return this.endDate;
        }
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
                Messages.invalidTaskTypeMessage();
                break;
        }
    }
    public String getTaskType() {
        try {
            if (this.taskType.isEmpty() || this.taskType == null) {
                throw new InvalidGetException();
            } else {
                return this.taskType;
            }
        } catch (InvalidGetException e){
            Messages.invalidTaskAttributeMessage();
            return this.taskType;
        }
    }
}
