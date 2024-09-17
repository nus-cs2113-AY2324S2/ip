package humi;

/**
 * Represents the event task containing task type, mark, description,
 * start date, and end date
 */
public class EventTask extends Task{
    public String startDate;
    public String endDate;
    EventTask(String description, String startDate, String endDate) {
        this.taskType = TaskType.EVENT;
        this.description = description;
        this.isDone = false;
        this.startDate = startDate;
        this.endDate = endDate;
        Ui.printAddEvent(description, startDate, endDate);
    }

    EventTask(String description, String startDate, String endDate, boolean isDone) {
        this.taskType = TaskType.EVENT;
        this.description = description;
        this.isDone = isDone;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Prints the full format of the event task
     */
    @Override
    public void print() {
        printTaskType();
        printMark();
        System.out.println(description + " from: " + startDate + " to: " + endDate);
    }
}
