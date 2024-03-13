package junbot.tasks;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class Deadline extends Todo {
    protected LocalDate endDate;

    /**
     * Constructs a Deadline object with the provided description and endDate, along with its associated tag D
     *
     * @param description description of the task
     * @param endDate deadline of the task
     */
    public Deadline(String description, LocalDate endDate) {
        super(description);
        this.endDate = endDate;
        this.tag = "D";
    }

    @Override
    public String getEndDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        String endDateString = endDate.format(formatter);
        return endDateString;
    }

    @Override
    public String toString() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String formattedDeadline = this.endDate.format(outputFormatter);

        return super.toString() + " (by: " + formattedDeadline + ")";
    }

}