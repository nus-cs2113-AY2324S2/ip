package tasktype;

import commandexceptions.JingHaoExceptions;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents the Event task of the JingHao chatbot.
 * Event class is a subclass of task.
 */
public class Event extends Task {
    private final static String ICON_TYPE = "[E]";
    protected LocalDateTime startDate;
    protected LocalDateTime endDate;

    /**
     * Constructs an Event object with the specific description, start and end date.
     *
     * @param description The description of the event task.
     * @param fromDate The starting date and time of the event task.
     * @param toDate The ending date and time of the event task.
     * @param isFromFile Whether the construction request is from data file.
     * @throws JingHaoExceptions If user input does not satisfy the required format.
     */
    public Event(String description, String fromDate, String toDate, boolean isFromFile) throws JingHaoExceptions{
        super(description);
        LocalDateTime begin = convertTime(fromDate);
        LocalDateTime end = convertTime(toDate);
        verifyTime(begin, end, isFromFile);
    }

    /**
     * Converts the string that the user input as the time into a valid LocalDateTime variable.
     *
     * @param date The input date and time from the user.
     * @return The time in the required format.
     * @throws JingHaoExceptions If there is an error in the input date.
     */
    private LocalDateTime convertTime(String date) throws JingHaoExceptions {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        try {
            int ISO_Length = 10;
            if (date.length() == ISO_Length) {
                date += " 2359";
            }
        } catch (DateTimeException e) {
            String incorrectFormat = "Wrong date format :(\n" +
                    "Use the command: (Task description) + /date + (YYYY-MM-DD) or (YYYY-MM-DD HHmm)\n" +
                    "Example: event Return book /from 2024-02-25 2300 /to 2024-02-29 1800";
            throw new JingHaoExceptions(incorrectFormat);
        }
        return LocalDateTime.parse(date, formatter);
    }

    /**
     * Checks if the input dates are valid before storing the date and time.
     *
     * @param begin The starting date and time of the event task.
     * @param end The ending date and time of the event task.
     * @param isFromFile Whether the construction request is from data file.
     * @throws JingHaoExceptions If the end date is earlier than the starting date.
     */
    private void verifyTime(LocalDateTime begin, LocalDateTime end, boolean isFromFile) throws JingHaoExceptions {
        if(!isFromFile && begin.isAfter(end)) {
            String invalidBeginAndEndTime = "Hmm... start date seems to be after end date\n" +
                    "Please make sure the input dates are correct";
            throw new JingHaoExceptions(invalidBeginAndEndTime);
        }
        this.startDate = begin;
        this.endDate = end;
    }

    /**
     * Converts the Event task to a specific format to display on the screen.
     *
     * @return A formatted string containing the Event task.
     */
    public String toString() {
        return ICON_TYPE + super.toString()
                + "(From: " + startDate.format(DateTimeFormatter.ofPattern("d MMM yyyy HHmm")) + " To: "+
                endDate.format(DateTimeFormatter.ofPattern("d MMM yyyy HHmm")) + ")";
    }

    /**
     * Converts the Event task to the specific format for saving to the text file.
     *
     * @return A formatted string containing the Event task for saving.
     */
    @Override
    public String toDiskFormat() {
        return "E," + (this.isDone ? "TRUE," : "FALSE,") + description + ","
                + startDate + "," + endDate + "\n";
    }

}
