package daisy.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDate Date;

    protected LocalTime fromTime;
    protected LocalTime toTime;

    public Event(String taskName, String fromDate, String toTime){
        super(taskName);

        String[] dateTime = fromDate.split(" ");
        this.Date = LocalDate.parse(dateTime[0], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.fromTime = LocalTime.parse(dateTime[1], DateTimeFormatter.ofPattern("HHmm"));
        this.toTime = LocalTime.parse(toTime, DateTimeFormatter.ofPattern("HHmm"));
    }

    public String toString() {
        return String.format("[E][%s] %s (from: %s %s to: %s)", (this.isDone)? "X":" ", this.taskName, this.Date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")), this.fromTime.format(DateTimeFormatter.ofPattern("h:mma")), this.toTime.format(DateTimeFormatter.ofPattern("h:mma")));
    }

    public String save() {
        return String.format("E,%s,%s,%s %s,%s", (this.isDone)? "1":"0", this.taskName, this.Date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), this.fromTime.format(DateTimeFormatter.ofPattern("HHmm")), this.toTime.format(DateTimeFormatter.ofPattern("HHmm")));
    }

}
