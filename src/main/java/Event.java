import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {
    private static final String TYPE = "E";
    private final Date from;
    private final Date to;
    private static final SimpleDateFormat formatter =
            new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

    /**
     * Constructor for Event task
     *
     * @param description description of the task
     * @param from when the task starts
     * @param to when the task ends
     */
    Event(String description, Date from, Date to) {
        super(description, TYPE);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructor for Event task
     *
     * @param description description of the task
     * @param status status of the task
     * @param from when the task starts
     * @param to when the task ends
     */
    Event(String description, boolean status, Date from, Date to) {
        super(description, TYPE, status);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns new marked Event task
     *
     * @return new Event task that is marked
     */
    @Override
    Event markTask() {
        return new Event(
                this.getDescription(),
                true, this.from, this.to);
    }

    /**
     * Returns new unmarked Event task
     *
     * @return new Event task that is unmarked
     */
    @Override
    Event unmarkTask() {
        return new Event(
                this.getDescription(),
                false, this.from, this.to);
    }

    /**
     * Encodes Event task in a suitable format to be saved in txt file
     *
     * @return encoded string
     */
    @Override
    String encodeString() {
        String type = super.getType();
        String status = super.getStatus() ? "X" : " ";
        String description = super.getDescription();
        String from = formatter.format(this.from);
        String to = formatter.format(this.to);
        return String.format(
                "%s|%s|%s|%s|%s",
                type,
                status,
                description,
                from,
                to);
    }

    /**
     * Overrides Object to String method for more verbose output
     *
     * @return overriden string
     */
    @Override
    public String toString() {
        String type = super.getType();
        String status = super.getStatus() ? "X" : " ";
        String description = super.getDescription();
        Date from = this.from;
        Date to = this.to;
        return String.format(
                "%s|%s|%s(from: %s to: %s)",
                type,
                status,
                description,
                from,
                to);

    }

}
