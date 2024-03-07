import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

class Deadline extends Task {
    private LocalDateTime byDateTime;
    private String byString;
    // Define an array of formatters to support multiple date formats
    private static final DateTimeFormatter[] formatters = {
            DateTimeFormatter.ofPattern("d-MM-yyyy HHmm"),
            DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
            DateTimeFormatter.ofPattern("d/MM/yyyy HHmm"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"),
            DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm")
    };
    private static final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.byDateTime = parseDateTime(by); // Attempt to parse the date using the defined formatters
        this.byString = (this.byDateTime == null) ? by : null; // If parsing fails, treat by as a regular string
    }

    private static LocalDateTime parseDateTime(String dateTimeStr) {
        // Try to parse the string with each formatter
        for (DateTimeFormatter formatter : formatters) {
            try {
                // If parsing succeeds, return the LocalDateTime object
                return LocalDateTime.parse(dateTimeStr, formatter);
            } catch (DateTimeParseException e) {
                // If parsing fails, catch the exception and try the next formatter
            }
        }
        // If all formatters fail, return null
        return null;
    }

    @Override
    public String toString() {
        if (byDateTime != null) {
            // If byDateTime is not null, format it using the outputFormatter
            return super.toString() + " (by: " + byDateTime.format(outputFormatter) + ")";
        } else {
            // If byDateTime is null, byString contains the original string
            return super.toString() + " (by: " + byString + ")";
        }
    }

    @Override
    public String toSaveFormat() {
        if (byDateTime != null) {
            // For consistency, use the first formatter's pattern when saving to file
            return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + byDateTime.format(formatters[0]);
        } else {
            return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + byString;
        }
    }
    public String getType() {
        return "[D]";
    }
}








