public class Deadline extends Task{
    private final static char TASK_TYPE = 'D';
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
    public char getTaskType(){
        return TASK_TYPE;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
