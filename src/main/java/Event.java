public class Event extends Task {
    private static final String TYPE = "E";
    private final String from;
    private final String to;

    Event(String input, String from, String to) {
        super(input, TYPE);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return String.format(
                "[%s][%s] %s(from: %s to: %s)",super.getType(),
                super.getStatus() ? "X" : " ",
                super.getInput(), this.from, this.to);

    }

}
