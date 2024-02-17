public class Deadline extends Task {
    protected String by;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public static Task fromString(String line) throws MissingParameterException {
        String deadline = line.replace("deadline", "");
        String[] segments = deadline.split("/by");
        if(segments.length < 2) {
            throw new MissingParameterException("deadline");
        }
        String deadlineName = segments[0].trim();
        String by = segments[1].trim();
        if(deadlineName.isEmpty() || by.isEmpty()) {
            throw new MissingParameterException("deadline");
        }
        return new Deadline(deadlineName, by);
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

}
