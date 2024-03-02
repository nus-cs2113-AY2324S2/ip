package TaskList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
public class Event extends Task {
    protected String rawStartDateTime;
    protected String rawEndDateTime;
    protected LocalDate startDateTime;
    protected LocalDate endDateTime;
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("d-M-yyyy");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMMM d yyyy");

    private String warningMessage;

    /**
     * Constructs an Event with a specified description including its start and end times.
     *
     * @param description Description of the event, including "/from" and "/to" time markers.
     */
    public Event(String description) {
        super(description);
        try {
            String[] givenTimeline = description.split("/from ", 2);
            this.description = givenTimeline.length >= 1 ? givenTimeline[0].trim() : "";
            if (givenTimeline.length > 1) {
                String[] startEnd = givenTimeline[1].split(" /to ", 2);
                this.rawStartDateTime = startEnd.length > 0 ? startEnd[0].trim() : "";
                this.rawEndDateTime = startEnd.length > 0 ? startEnd[1].trim() : "";
                try {
                    this.startDateTime = LocalDate.parse(this.rawStartDateTime, INPUT_FORMATTER);
                    this.endDateTime = LocalDate.parse(this.rawEndDateTime, INPUT_FORMATTER);
                } catch (DateTimeParseException e) {
                    this.warningMessage = "Advice: " + this.rawStartDateTime + " and " + this.rawEndDateTime + " is invalid dead format. Please use DD-MM-YYY.";
                    this.startDateTime = null;
                    this.endDateTime = null;
                }
            } else {
                this.rawStartDateTime = "";
                this.rawEndDateTime = "";
            }
        } catch (Exception e) {
            this.rawStartDateTime = "";
            this.rawEndDateTime = "";
        }
    }

    /**
     * Gets the warning message generated during construction if the date format was invalid.
     *
     * @return Warning message if date was invalid, null otherwise.
     */
    public String getWarningMessage() {
        return warningMessage;
    }

    /**
     * Returns a string representation of the event, including its status icon, description,
     * and formatted start and end times.
     *
     * @return A string representation of the event.
     */
    @Override
    public String toString() {
        String startDateTimeString = (startDateTime != null ) ? startDateTime.format(OUTPUT_FORMATTER) : this.rawStartDateTime;
        String endDateTimeString = (endDateTime != null ) ? endDateTime.format(OUTPUT_FORMATTER) : this.rawEndDateTime;
        return "[E]" + getStatusIcon() + " " + description + " (from: " + startDateTimeString + " to: " + endDateTimeString + ")";
    }
}
