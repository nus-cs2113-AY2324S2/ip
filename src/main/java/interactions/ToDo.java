package interactions;
import interactions.Task;

public class ToDo extends Task {
    private boolean haveToDo, haveDeadline, isEvent;

    public String getDeadline() {
        return deadline;
    }
    public void setDeadline(String deadline) {
        this.deadline = deadline;
        this.haveDeadline = true;
    }
    private String deadline;

    public String getEventFrom() {
        return eventFrom;
    }

    public void setEventFrom(String eventFrom) {
        this.eventFrom = eventFrom;
    }

    private String eventFrom = "";

    public String getEventTo() {
        return eventTo;
    }

    public void setEventTo(String eventTo) {
        this.eventTo = eventTo;
    }

    private String eventTo;
    public void setEvent(boolean event) {
        isEvent = event;
    }

    public void setHaveToDo(boolean haveToDo) {
        this.haveToDo = haveToDo;
    }

    public ToDo(String task) {
        super(task);
        this.haveToDo = false;
        this.haveDeadline = false;
        this.isEvent = false;
    }
    @Override
    public void print() {
        String taskType = "[ ]";
        String additionalInfo = "";
        if (haveToDo) {
            taskType = "[T]";
        }
        else if (haveDeadline) {
            taskType = "[D]";
            additionalInfo = " (by: " + deadline + ")";
        }
        else if (isEvent) {
            taskType = "[E]";
            additionalInfo = " (from: " + eventFrom + " to: " + eventTo + ")";
        }
        System.out.print(taskType);
        System.out.print(isMarked() ? "[X] " : "[ ] ");
        System.out.println(getTask() + additionalInfo);
    }
}
