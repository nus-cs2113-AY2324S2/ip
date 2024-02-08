public class Deadline extends Task{

    protected String by;
    public Deadline (String description, String by){
        super(description);
        by = this.by;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }
}
