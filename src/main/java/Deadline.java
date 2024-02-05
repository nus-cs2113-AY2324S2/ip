public class Deadline extends Task{
    protected String by;
    protected static String TYPE = "deadline";
    public Deadline(String description) {
        super(description);
    }

    public String getDescription(){
        return ("[D][" + super.getStatusIcon() + "] " + this.description + " (by: " + by + ")");
    }

    public void setBy(String item) {
        this.by = item;
    }

    public String getBy(){
        return this.by;
    }

    public String getTYPE() {
        return this.TYPE;
    }
}
