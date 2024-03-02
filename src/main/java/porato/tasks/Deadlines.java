package porato.tasks;

/**
 * Represents the deadline tasks.
 * It inherits from the Task class.
 * It contains a deadline date
 */
public class Deadlines extends Task{
    /** Task deadline */
    private String by;

    public Deadlines(String line, String by){
        super(line);
        this.by = by;
    }

    @Override
    public String getTask(){
        return super.getTask() + "/" + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + by + ")";
    }
}
