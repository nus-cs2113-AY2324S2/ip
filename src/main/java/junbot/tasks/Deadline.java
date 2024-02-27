package junbot.tasks;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
public class Deadline extends Todo {
    protected LocalDate endDate;

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