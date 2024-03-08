package jake.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a Task object based on user inputs.
     *
     * @param description Description of the task.
     * @param isDone True if task is completed. Else, false.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Retrieves the status of task, whether it is completed or not.
     *
     * @return string displaying whether task is completed.
     */
    public String getStatus() {
        String icon = isDone ? "X" : " ";
        String status= "[" + icon + "] ";
        return status;
    }

    /**
     * Updates the status of task, to either completed or not.
     *
     * @param isDone Is task completed? True marks task as completed, false unmarks tasks as uncompleted.
     */
    public void markTask(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Prints the completion status of task, and description of the task.
     *
     * @return description of the task and completion status.
     */
    @Override
    public String toString() {
        return getStatus() + this.description;
    }

    /**
     * Reads in a string representing date and time, and reformats the way it is displayed.
     *
     * @param dateTime Takes in a string, in 1 of 2 formats. Example: 2000-01-01 01:00, or 2000-01-01 01:00.  
     * @return String displaying newly formatted date. Either format: Jan 1 2000, 01:00AM, or Jan 1 2000.
     */
    public String convertDateTime(String dateTime) {
        // dateTime should come in either format: YYYY-MM-DD HH:MM, or YYYY-MM-DD
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
