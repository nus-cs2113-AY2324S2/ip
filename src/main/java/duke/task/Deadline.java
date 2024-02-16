package duke.task;

import duke.DukeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.DateTimeException;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, String by) throws DukeException {
        super(description);
        this.by = convertBy(by);
    }

    private LocalDateTime convertBy(String by) throws DukeException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        int ISOLength = 10;
        if (by.length() == ISOLength) {
            by += " 2359";
        }
        LocalDateTime parsedBy;
        try {
            parsedBy = LocalDateTime.parse(by, formatter);
        } catch (DateTimeException e) {
            throw new DukeException("Time Out....\n\t " +
                    "OOPS!!! The format of a deadline task is wrong!\n\t " +
                    "TASK /by DEADLINE (in date: yyyy-mm-dd time: HHmm format)\n\t " +
                    "Example: deadline return book /by 2024-05-01 1800");
        }
        if (parsedBy.isBefore(LocalDateTime.now())) {
            throw new DukeException("Time Out....\n\t " +
                    "OOPS!!! Your deadline is long due!");
        }
        return parsedBy;
    }

    @Override
    public String toDisk() {
        String byToDisk = this.by.toString().replace("T", " ").replace(":", "");
        return "D" + super.toDisk() + " | " + byToDisk + System.lineSeparator();
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        return "[D]" + super.toString() + " (by: " + this.by.format(formatter) + ")";
    }
}
