package huan.task;

/**
 * Class for a task representing an event
 */
public class EventTask extends Task{
    private String startTime, endTime;

    /**
     * Constructor class for an EventTask
     * @param name the name of the Task
     * @param startTime the start date of the event
     * @param endTime the end date of the event
     * @param isDone whether the task is marked as finished
     */
    public EventTask(String name, String startTime, String endTime, Boolean isDone) {
        setName(name);
        this.startTime = startTime;
        this.endTime = endTime;
        setIsDone(isDone);
        setTaskType(2);
    }

    /**
     * Method for printing the Task
     */
    public void printTask() {
        System.out.println("[E][" + (getIsDone() ? "X" : " ") + "] " + getName() + " (from: " + startTime + " to: " + endTime + ")");
    }

    /**
     * Method for writing the Task to file
     * @return the line to be written to file
     */
    @Override
    public String writeLine() {
        return "E" + (getIsDone() ? "T" : "F") + " " + getName() + " /from " + startTime + " /to " + endTime;
    }

    /**
     * Set method for the start date
     * @param startTime the start date
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * Get method for the start date
     * @return the start date
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * Set method for the end date
     * @param endTime the end date
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * Get method for the end date
     * @return the end date
     */
    public String getEndTime() {
        return endTime;
    }
}
