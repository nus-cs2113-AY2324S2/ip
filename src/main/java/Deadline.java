public class Deadline extends Task{

    protected String by;


    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.type = "[D]";
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString(){
        return type + super.toString() + " (by: " + by + ")";
    }
}