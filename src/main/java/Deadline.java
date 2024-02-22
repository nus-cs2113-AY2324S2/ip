public class Deadline extends Task{
    protected String by;

    public Deadline(String desc, String by) {
        super(desc);
        this.by = by;
    }

    public String getBy() {
        return this.by;
    }

    public void setBy(String newBy) {
        this.by = newBy;
    }

    @Override
    public String convertToCommand() {
        return "deadline " + super.convertToCommand()
                + "/by " + this.by;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " by " + this.by;
    }

}
