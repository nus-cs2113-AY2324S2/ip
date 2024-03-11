package mimi.classes;
import mimi.exceptions.MimiException;
import static mimi.helper.Storage.FILE_DELIMITER;

/**
 * This class represents an Event task, which is a task that has a specific start and end time.
 * It inherits from the Task class and overrides the toString and toFileString method.
 */

public class Event extends Task {
    private String startTime;
    private String endTime;
    public Event(String name, String startTime, String endTime) {
        super(name);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * This method overrides the toString method in the Task class.
     * It returns a string representation of the Event task.
     * e.g. [E][✘] return book (from: 2021-08-21 to: 2021-08-22)
     * @return a string representation of the Event task
     */
    @Override
    public String toString(){
        String duration = "(from: " + this.getStartTime() + " to: " + this.getEndTime() + ")";
        return "[E]" + super.toString() + duration;
    }
    /**
     * This method overrides the toFileString method in the Task class.
     * It returns a string representation of the Event task to be written to the file.
     * e.g. E|false|return book|2021-08-21|2021-08-22
     * @return a string representation of the Event task to be written to the file
     */
    @Override
    public String toFileString(){
        return "E" + FILE_DELIMITER + super.toFileString() + FILE_DELIMITER + this.getStartTime() + FILE_DELIMITER + this.getEndTime();
    }
}
