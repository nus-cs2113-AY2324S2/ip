package tasks;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

/** Represents a task, such as todo, deadline and event, which can be compiled into a list */
public abstract class Task {
    protected String taskDescription;
    private String taskType;
    private boolean isMarked;
    public String getTaskDescription() {
        return taskDescription;
    }
    public boolean isMarked() {
        return isMarked;
    }
    public void setMarked(boolean marked) {
        isMarked = marked;
    }
    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }
    public String getTaskType() {
        return taskType;
    }

    /**
     * Constructs a task given the description.
     *
     * @param taskDescription The description of the task, which may include time.
     */
    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isMarked = false;
    }

    /**
     * Parses a string representing a date in into formatted string with "d MMM yyyy" format.
     * Special words such as "today", "tomorrow", "tmr", "next week" and "next month" are supported.
     *
     * param dateStr String representing day to be parsed.
     * @return Parsed String in "d MMM yyyy" format.
     * @throws DateTimeParseException If input string is invalid as a date
     */
    public String parseStringToDate(String dateStr) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("d MMM yyyy");
        LocalDate today = LocalDate.now();
        if (dateStr.equalsIgnoreCase("today") || dateStr.equalsIgnoreCase("now")) {
            return today.format(outputFormatter);
        }
        if (dateStr.equalsIgnoreCase("tomorrow") ||
                dateStr.equalsIgnoreCase("tmr") || dateStr.equalsIgnoreCase("tmrw")) {
            return today.plusDays(1).format(outputFormatter);
        }
        if (dateStr.equalsIgnoreCase("next week")) {
            return today.plusWeeks(1).format(outputFormatter);
        }
        if (dateStr.equalsIgnoreCase("next month")) {
            return today.plusMonths(1).format(outputFormatter);
        }
        return LocalDate.parse(dateStr, inputFormatter).format(outputFormatter);
        /*} catch (DateTimeParseException e) {
            System.out.println("Sorry, this format is not accepted.");
        }*/
    }

    /** Prints information about type and if it's marked, as indicated by X,
     *  when task is presented by UI.
     */
    public abstract void print();

}