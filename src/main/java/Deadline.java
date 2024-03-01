/**
 * Defines a specific type of task that has a "By" date.
 */
public class Deadline extends Task{
    protected String by;
    protected static String TYPE = "deadline";

    /**
     * Creates an instance of a deadline task with a description, "by" date and a completeness.
     *
     * @param description Description of the deadline task.
     * @param deadLine By date.
     * @param isCompleted Completeness of the task.

     */
    public Deadline(String description, String deadLine, boolean isCompleted) {
        super(description);
        this.by = deadLine;
        this.isDone = isCompleted;
    }


    /**
     * Returns the deadline's description in the proper format displayed in the list.
     *
     * @return Formatted deadline description.
     */
    @Override
    public String getDescription(){
        return ("[D][" + super.getStatusIcon() + "] " + this.description + " (by: " + by + ")");
    }


    public void setBy(String item) {
        this.by = item;
    }


    public String getBy(){
        return this.by;
    }
}
