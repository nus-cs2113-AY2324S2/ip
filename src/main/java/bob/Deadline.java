package bob;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    public Deadline (String stringRepresentation) {
        super(stringRepresentation);
        String[] split = stringRepresentation.substring(7).split(" [(]");
        this.description = split[0];
        this.by = split[1].substring(4, split[1].length() - 1);
    }

    @Override
    public String toString() {
        return Ui.DEADLINE_ICON + super.getListItem() + " (by: " + by + ")";
    }
}