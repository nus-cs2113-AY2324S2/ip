package tasktype;

import commandexceptions.JingHaoExceptions;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final static String ICON_TYPE = "[E]";
    protected LocalDateTime startDate;
    protected LocalDateTime endDate;
    public Event(String description, String fromDate, String toDate, boolean isFromFile) throws JingHaoExceptions{
        super(description);
        LocalDateTime begin = convertTime(fromDate);
        LocalDateTime end = convertTime(toDate);
        verifyTime(begin, end, isFromFile);
    }

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

    private void verifyTime(LocalDateTime begin, LocalDateTime end, boolean isFromFile) throws JingHaoExceptions {
        if(!isFromFile && begin.isAfter(end)) {
            String invalidBeginAndEndTime = "Hmm... start date seems to be after end date\n" +
                    "Please make sure the input dates are correct";
            throw new JingHaoExceptions(invalidBeginAndEndTime);
        }
        this.startDate = begin;
        this.endDate = end;
    }
    
    public String toString() {
        return ICON_TYPE + super.toString()
                + "(From: " + startDate.format(DateTimeFormatter.ofPattern("d MMM yyyy HHmm")) + " To: "+
                endDate.format(DateTimeFormatter.ofPattern("d MMM yyyy HHmm")) + ")";
    }

    @Override
    public String toDiskFormat() {
        return "E," + (this.isDone ? "TRUE," : "FALSE,") + description + ","
                + startDate + "," + endDate + "\n";
    }

}
