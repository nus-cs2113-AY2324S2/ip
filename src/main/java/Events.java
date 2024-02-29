public class Events extends Task {

    protected String From;
    protected String To;

    Events(String description, String from, String to) {
        super(description);
        this.From = from;
        this.To = to;
    }

    @Override
    public String getType() {
        return "E";
    }

    @Override
    public String toString() {
        return super.toString() + "(from:" + From + " to: " + To + ")";
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " (from: " + From + " to: " + To + ")";
    }

}
