import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {
    private static final String TYPE = "D";
    private final Date timestamp;

    private static final SimpleDateFormat formatter =
            new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

    Deadline(String description, Date timestamp) {
        super(description, TYPE);
        this.timestamp = timestamp;
    }

    Deadline(String description, boolean status, Date timestamp) {
        super(description, TYPE, status);
        this.timestamp = timestamp;
    }

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
