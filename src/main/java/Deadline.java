public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public void printTask() {
        System.out.println (getTypeIcon() + getStatusIcon() + " " +  this.description + " (by: " + this.by + ")");
    }

    @Override
    public String getTypeIcon(){
        return "[D]";
    }
}