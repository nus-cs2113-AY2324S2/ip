package ChelleCommands;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description) {
        super(parseDescription(description));
        this.by = parseBy(description);
    }

    private static String parseDescription(String description) {
        String[] descriptionPart = description.split("/by", 2);
        return descriptionPart[0].trim();
    }

    private String parseBy(String description) {
        String[] byPart = description.split("/by", 2);
        return byPart[1].trim();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
