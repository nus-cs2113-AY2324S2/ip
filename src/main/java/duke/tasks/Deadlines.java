package duke.tasks;

public class Deadlines extends Task{
    /** Task deadline */
    private String by;

    public Deadlines(String line, String by){
        super(line);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + by + ")";
    }
}
