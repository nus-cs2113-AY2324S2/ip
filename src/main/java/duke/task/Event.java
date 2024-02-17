package duke.task;

import duke.DukeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.DateTimeException;

/**
 * Represents the Event task of the Duke chatbot.
 * Event tasks are tasks with a start and end date/time.
 */
public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs a new Event object with the specified description, start date/time and end date/time.
     *
     * @param description Description of the Event task
     * @param from Start date/time of the Event task
     * @param to End date/time of the Event task
     */
    public Event(String description, String from, String to) throws DukeException {
        super(description);
        this.from = convertTime(from, true);
        this.to = convertTime(to, false);
        if (this.to.isBefore(this.from)) {
            throw new DukeException("Time Out....\n\t " +
                    "OOPS!!! Your event ended before it could start!");
        }
    }

    private LocalDateTime convertTime(String time, boolean start) throws DukeException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        int ISOLength = 10;
        if (time.length() == ISOLength) {
            if (start) {
                time += " 0000";
            } else {
                time += " 2359";
            }
        }
        LocalDateTime parsedTime;
        try {
            parsedTime = LocalDateTime.parse(time, formatter);
        } catch (DateTimeException e) {
            throw new DukeException("Time Out....\n\t " +
                    "OOPS!!! The format of an event task is wrong!\n\t " +
                    "TASK /from START TIME /to END TIME (both in date: yyyy-mm-dd time: HHmm format)\n\t " +
                    "Example: event meet friends /from 2024-05-01 0610 /to 2024-06-01 1720");
        }
        if (parsedTime.isBefore(LocalDateTime.now())) {
            throw new DukeException("Time Out....\n\t OOPS!!! Your event is long over!");
        }
        return parsedTime;
    }

    /**
     * Converts the Event task to the specific format for saving to the text file.
     *
     * @return A formatted string containing the Event task for saving.
     */
    @Override
    public String toDisk() {
        String fromToDisk = this.from.toString().replace("T", " ").replace(":", "");
        String toToDisk = this.from.toString().replace("T", " ").replace(":", "");
        return "E" + super.toDisk() + " | " + fromToDisk + " - " + toToDisk + System.lineSeparator();
    }

    /**
     * Converts the Event task to the specific format for displaying on the screen.
     *
     * @return A formatted string containing the Event task for display.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        return "[E]" + super.toString() + " (from: " + this.from.format(formatter)
                + " to: " + this.to.format(formatter) + ")";
    }
}
