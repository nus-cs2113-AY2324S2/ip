package interactions;
import interactions.Task;

public class ToDo extends Task {
    private boolean haveToDo, haveDeadline, isEvent;
    private String deadline, eventFrom, eventTo;
    private String taskType = "U";

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public boolean haveToDo() {
        return haveToDo;
    }

    public boolean haveDeadline() {
        return haveDeadline;
    }

    public boolean isEvent() {
        return isEvent;
    }

    public String getDeadline() {
        return deadline;
    }
    public void setDeadline(String deadline) {
        this.deadline = deadline;
        this.haveDeadline = true;
    }

    public String getEventFrom() {
        return eventFrom;
    }

    public void setEventFrom(String eventFrom) {
        this.eventFrom = eventFrom;
    }

    public String getEventTo() {
        return eventTo;
    }

    public void setEventTo(String eventTo) {
        this.eventTo = eventTo;
    }

    public void setEvent(boolean event) {
        isEvent = event;
    }

    public void setHaveToDo(boolean haveToDo) {
        this.haveToDo = haveToDo;
    }

    public String getTaskType() {
        return taskType;
    }

    public ToDo(String task) {
        super(task);
        this.haveToDo = false;
        this.haveDeadline = false;
        this.isEvent = false;
    }
    @Override
    public void print() {
        String additionalInfo;

        if (haveDeadline) {
            additionalInfo = " (by: " + deadline + ")";
        }
        else if (isEvent) {
            additionalInfo = " (from: " + eventFrom + " to: " + eventTo + ")";
        } else {
            additionalInfo = "";
        }
        System.out.print('[' + taskType + ']');
        System.out.print(isMarked() ? "[X] " : "[ ] ");
        System.out.println(getTask() + additionalInfo);
    }
}
