package Chat.tasks;

public class Events extends Task {
    private String from;
    private String to;

    /**
     * Construct a Event class that takes in task description, from time and to time of the evnet.
     * Inherits from the Task superclass.
     * @param description The description of the task of subclass event.
     * @param from The start time of the event, in string.
     * @param to The end time of the event, in string.
     */
    public Events(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        this.time = from + "-" +to;
        this.type = TaskType.EVENT;
        shortType = this.type.name().substring(0, 1);
    }

    /**
     *  Override the toString method from superclass Task so the output can be reformatted.
     * @return A string of [TaskType] Description (from: StartTime to: EndTime) is returned.
     */
    @Override
    public String toString() {
        return "[" + shortType + "]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
