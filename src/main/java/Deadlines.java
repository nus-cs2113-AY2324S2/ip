public class Deadlines extends Task{

    protected String by;

    public Deadlines(String description, String by) {

        super(description);
        this.by = by;
    }

    public String getByDate(){

        return by;
    }

    @Override
    public String getStatusIcon() {
        return "[D]" + super.getStatusIcon();
    }

    @Override
    public String getDescription(){

        return super.getDescription() + " (by: " + by + ")";
    }
}

