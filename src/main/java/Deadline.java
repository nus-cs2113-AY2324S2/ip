import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {
    private static final String TYPE = "D";
    private final Date timestamp;
    private static final SimpleDateFormat formatter =
            new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

    /**
     * Constructor for Deadline task
     *
     * @param description description of the task
     * @param timestamp when the task needs to be done
     */
    Deadline(String description, Date timestamp) {
        super(description, TYPE);
        this.timestamp = timestamp;
    }

    /**
     * Constructor for Deadline task
     *
     * @param description description of the task
     * @param status status of the task
     * @param timestamp when the task needs to be done
     */
    Deadline(String description, boolean status, Date timestamp) {
        super(description, TYPE, status);
        this.timestamp = timestamp;
    }

    /**
     * Returns new marked Deadline task
     *
     * @return new Deadline task that is marked
     */
    @Override
    Deadline markTask() {
        return new Deadline(
                this.getDescription(),
                true, this.timestamp);
    }

    /**
     * Returns new unmarked Deadline task
     *
     * @return new Deadline task that is unmarked
     */
    @Override
    Deadline unmarkTask() {
        return new Deadline(
                this.getDescription(),
                false, this.timestamp);
    }

    /**
     * Encodes Deadline task in a suitable format to be saved in txt file
     *
     * @return encoded string
     */
    @Override
    String encodeString() {
        String type = super.getType();
        String status = super.getStatus() ? "X" : " ";
        String input = super.getDescription();
        String timestamp = formatter.format(this.timestamp);
        return String.format(("%s|%s|%s|%s"),
                type,
                status,
                input,
                timestamp);
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
        String input = super.getDescription();
        Date timestamp = this.timestamp;
        return String.format(
                "%s|%s|%s(by: %s)",
                type,
                status,
                input,
                timestamp);
    }


}
