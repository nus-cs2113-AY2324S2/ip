package tasktype;

import commandexceptions.JingHaoExceptions;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents the Deadline task of the JingHao chatbot.
 * Deadline class is a subclass of task.
 */
public class Deadline extends Task {
    private final static String ICON_TYPE = "[D]";
    protected LocalDateTime date;

    /**
     * Constructs a deadline object with the specific description and deadline date.
     *
     * @param description The description of the deadline task from the user.
     * @param by The deadline date for the deadline task.
     * @param isfromFile Whether the construction request is from data file.
     * @throws JingHaoExceptions If user input does not satisfy the required format.
     */
    public Deadline(String description, String by, boolean isfromFile) throws JingHaoExceptions {
        super(description);
        this.date = convertTime(by, isfromFile);
    }

    /**
     * Converts the string that the user input as the time into a valid LocalDateTime variable.
     *
     * @param by The deadline date for the deadline task.
     * @param isFromFile Whether the construction request is from data file.
     * @return The input deadline from the user.
     * @throws JingHaoExceptions If there is an error in the input date.
     */
    private LocalDateTime convertTime(String by, boolean isFromFile) throws JingHaoExceptions{
        LocalDateTime inputDate;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            int ISO_Length = 10;
            if (by.length() == ISO_Length) {
                by += " 2359";
            }
            inputDate = LocalDateTime.parse(by, formatter);
            if (!isFromFile && inputDate.isBefore(LocalDateTime.now())) {
                String deadlineOverMessage = "Hold on...\n" +
                        "The deadline stated is already over";
                throw new JingHaoExceptions(deadlineOverMessage);
            }
        } catch (DateTimeException e) {
            String incorrectFormat = "Wrong date format :(\n" +
                    "Use the command: (Task description) + /by + (YYYY-MM-DD) or (YYYY-MM-DD HHmm)\n" +
                    "Example: deadline Return book /by 2024-02-25 2300";
            throw new JingHaoExceptions(incorrectFormat);
        }
        return inputDate;
    }

    /**
     * Converts the deadline task to a specific format to display on the screen.
     *
     * @return A formatted string containing the deadline task.
     */
    public String toString(){
        return ICON_TYPE + super.toString() + " (By: " + date.format(DateTimeFormatter.ofPattern("d MMM yyyy HHmm")) + ")";
    }

    /**
     * Converts the Deadline task to the specific format for saving to the text file.
     *
     * @return A formatted string containing the Deadline task for saving.
     */
    @Override
    public String toDiskFormat() {
        return "D," + (this.isDone ? "TRUE," : "FALSE,")
                + description + "," + date + "\n";
    }
}
