package TaskList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected String rawDateTime;
    protected LocalDate dateTime;
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("d-M-yyyy");
    private static final DateTimeFormatter INPUT_FORMATTER2 = DateTimeFormatter.ofPattern("MMMM d yyyy");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMMM d yyyy");
    private String warningMessage;

    /**
     * Constructs a new Deadline with the specified description and parses the due date.
     *
     * @param description Description and date of the deadline in the format "description /by dd-MM-yyyy".
     */
    public Deadline(String description) {
        super(description);
        String[] descriptionParts = description.split("/by ", 2);
        this.description = descriptionParts.length >= 1 ? descriptionParts[0].trim() : "";
        if (descriptionParts.length > 1) {
            this.rawDateTime = descriptionParts[1].trim(); // Store the raw date string
            try {
                this.dateTime = LocalDate.parse(this.rawDateTime, INPUT_FORMATTER);
            } catch (DateTimeParseException e1) {
                try {
                    this.dateTime = LocalDate.parse(this.rawDateTime, INPUT_FORMATTER2);
                } catch (DateTimeParseException e2) {
                    this.warningMessage = "Advice: " + this.rawDateTime + " is an invalid date format. Please use 'dd-MM-yyyy' or 'MMMM d yyyy'.";
                    this.dateTime = null; // Ensure dateTime is null if parsing fails
                }
            }
        } else {
            this.rawDateTime = "";
        }
    }

    /**
     * Retrieves the warning message generated during the construction of this deadline.
     *
     * @return The warning message if the date was invalid, otherwise null.
     */
    public String getWarningMessage() {
        return warningMessage;
    }

    /**
     * Returns a string representation of the deadline, including its status icon,
     * description, and due date.
     *
     * @return A string representation of the deadline task.
     */
    @Override
    public String toString() {
        String dateTimeString = (dateTime != null ) ? dateTime.format(OUTPUT_FORMATTER) : this.rawDateTime;
        return "[D]" + getStatusIcon() + " " + this.description + " (by: " + dateTimeString + ")";
    }
}
