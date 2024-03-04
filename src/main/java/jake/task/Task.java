package jake.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatus() {
        String icon = isDone ? "X" : " ";
        String status= "[" + icon + "] ";
        return status;
    }

    public void markTask(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return getStatus() + this.description;
    }

    public String convertDateTime(String dateTime) {
        // dateTime should come in either format: DD/MM/YYYY HH:MM, or DD/MM/YYYY
        try {
            if (dateTime.contains(" ")) {
                String[] dateAndTime = dateTime.split(" ");
                String date = LocalDate.parse(dateAndTime[0]).format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                String time = LocalTime.parse(dateAndTime[1]).format(DateTimeFormatter.ofPattern("hh:mma"));
                return date + ", " + time;
            } else {
                String date = LocalDate.parse(dateTime).format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                return date;
            }
        } catch (DateTimeParseException e) {
            // Placeholder date used
            System.out.println("Invalid date and/or time format given. Using current date");
            return LocalDate.parse(LocalDate.now().toString()).format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }
    }
}
