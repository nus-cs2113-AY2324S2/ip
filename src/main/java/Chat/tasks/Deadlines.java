package Chat.tasks;

public class Deadlines extends Task{
    private String by;

    /**
     * Construct a Deadline object that takes in description and time of the task.
     * Inherits from Task class.
     * @param description The description of the task of deadline subclass, in string.
     * @param by The deadline time of the task, in string.
     */
    public Deadlines(String description, String by) {
        super(description);
        this.by = by;
        this.time = by;
        this.type = TaskType.DEADLINE;
        shortType = this.type.name().substring(0, 1);
    }

    /**
     * Override the toString method from superclass Task so the output can be reformatted.
     * @return A string of [TaskType] Description (by: DeadlineTime) is returned.
     */
    @Override
    public String toString(){
        return "[" + shortType +"]" + super.toString() + "(by: "+ by +")";
    }

}
