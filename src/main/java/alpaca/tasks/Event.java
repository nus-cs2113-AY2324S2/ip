package alpaca.tasks;

<<<<<<< HEAD
/**
 * Represents an event task
 */
=======
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

>>>>>>> master
public class Event extends Task {

    private LocalDateTime from;
    private LocalDateTime to;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");


    public Event (String taskName, LocalDate startDay, LocalTime startTime, LocalDate endDay, LocalTime endTime) {
        super(taskName);
        this.from = LocalDateTime.of(startDay, startTime);
        this.to = LocalDateTime.of(endDay, endTime);
    }

    public Event (String taskName, LocalDate startDay,  LocalDate endDay) {
        super(taskName);
        this.from = LocalDateTime.of(startDay, LocalTime.parse("00:00"));
        this.to = LocalDateTime.of(endDay, LocalTime.parse("00:00"));
    }

    public Event (String taskName, LocalDateTime from,  LocalDateTime to) {
        super(taskName);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        String formattedStartTime = this.from.format(formatter);
        String formattedEndTime = this.to.format(formatter);
        return "[E]" + super.toString() + " (from: " + formattedStartTime + " to: " + formattedEndTime + ")";
    }

    @Override
    public String save() {
        String formattedStartTime = this.from.format(formatter);
        String formattedEndTime = this.to.format(formatter);
        return "E | " + this.IntIsDone + " | " + this.description + " | " + formattedStartTime +
            "| " + formattedEndTime;
    }
}