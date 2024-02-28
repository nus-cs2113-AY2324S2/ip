import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {
    private static final String TYPE = "E";
    private final Date from;
    private final Date to;
    private static final SimpleDateFormat formatter =
            new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

    Event(String description, Date from, Date to) {
        super(description, TYPE);
        this.from = from;
        this.to = to;
    }

    Event(String description, boolean status, Date from, Date to) {
        super(description, TYPE, status);
        this.from = from;
        this.to = to;
    }

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
