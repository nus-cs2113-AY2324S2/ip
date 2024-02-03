public class Deadline extends Task {

    protected String by;

    public Deadline(String description) {
        super(description);
        this.taskType = "[D]";
    }

}
