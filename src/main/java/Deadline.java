public class Deadline extends Task{
    protected String by;
    protected static String TYPE = "deadline";
    public Deadline(String description, String deadLine, boolean isCompleted) {
        super(description);
        this.by = deadLine;
        this.isDone = isCompleted;
    }

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
