package tasktype;

import commandexceptions.JingHaoExceptions;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final static String ICON_TYPE = "[D]";
    protected LocalDateTime date;

    public Deadline(String description, String by, boolean isfromFile) throws JingHaoExceptions {
        super(description);
        this.date = convertTime(by, isfromFile);
    }

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

    public String toString(){
        return ICON_TYPE + super.toString() + " (By: " + date.format(DateTimeFormatter.ofPattern("d MMM yyyy HHmm")) + ")";
    }

    @Override
    public String toDiskFormat() {
//        String byFormat = date.toString().replace("T"," ").replace(":",)
        return "D," + (this.isDone ? "TRUE," : "FALSE,")
                + description + "," + date + "\n";
    }
}
