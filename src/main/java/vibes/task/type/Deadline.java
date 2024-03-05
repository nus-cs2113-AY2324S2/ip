package vibes.task.type;

public class Deadline extends Task{
    private final static String TASK_TYPE = "D";
    public static final String PRINT_FORMAT = "[D]%s (by: %s)";
    protected String by;

    public Deadline (String description, String by){
        super(description);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    @Override
    public String getTaskType(){
        return TASK_TYPE;
    }

    @Override
    public String toString() {
        return String.format(PRINT_FORMAT, super.toString(), by);
    }
}
