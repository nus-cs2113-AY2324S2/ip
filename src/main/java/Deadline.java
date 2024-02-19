public class Deadline extends Task {
    private static final String TYPE = "D";
    private final String timestamp;

    Deadline(String input, String timestamp) {
        super(input, TYPE);
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return String.format(
                "[%s][%s] %s(by: %s)",super.getType(),
                super.getStatus() ? "X" : " ",
                super.getInput(), this.timestamp);

    }


}
