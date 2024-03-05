package oley.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import oley.commands.Ui;

/**
 * Represents a task of Event type. An Event object corresponds to a task with its name, start time, end time, and
 * state of done being specified.
 * It is a subclass of Task.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructor of Event.
     * Identifies from and to in string type through index of "/from" and "/to".
     *
     * @param description The name and time of the event entered by user
     * @throws TimingNotFoundException If the time of start or end is not specified.
     */
    public Event (String description) throws TimingNotFoundException {
        super(description);
        if (!description.contains("/from") || !description.contains("/to")) {
            throw new TimingNotFoundException();
        }
        int fromIndex = description.indexOf("/");
        String time = description.substring(fromIndex + 1);
        int toIndex = time.indexOf("/") + fromIndex + 1;
        String taskName = description.substring(0, fromIndex - 1);
        setTaskName(taskName);
        String fromBeforeFormatting = description.substring(fromIndex + 6, toIndex - 1);
        String toBeforeFormatting = description.substring(toIndex + 4);
        setFrom(fromBeforeFormatting);
        setTo(toBeforeFormatting);
    }

    /**
     * Set the LocalDateTime object 'from' from its string type.
     * If the time is not provided in the correct format, user will be asked to enter the start time again
     * until the correct format is read in.
     *
     * @param fromBeforeFormatting The start time under String type.
     */
    public void setFrom(String fromBeforeFormatting) {
        try {
            this.from = LocalDateTime.parse(fromBeforeFormatting, DateTimeFormatter.ofPattern("yyyy-MM-dd-HHmm"));
        } catch (DateTimeParseException e) {
            Ui.printError();
            Ui.printCorrectFormat("from");
            Ui.lineBreaker();
            fromBeforeFormatting = Ui.readCommand();
            setFrom(fromBeforeFormatting);
        }
    }

    /**
     * Set the LocalDateTime object 'to' from its string type.
     * If the time is not provided in the correct format, or end time is before start time, user will be asked to
     * enter the end time again until the correct time is read in.
     *
     * @param toBeforeFormatting The end time under String type.
     */
    public void setTo(String toBeforeFormatting) {
        try {
            this.to = LocalDateTime.parse(toBeforeFormatting, DateTimeFormatter.ofPattern("yyyy-MM-dd-HHmm"));
        } catch (DateTimeParseException e) {
            Ui.printCorrectFormat("to");
            Ui.lineBreaker();
            toBeforeFormatting = Ui.readCommand();
            setTo(toBeforeFormatting);
        }
        if (to.isBefore(from)) {
            Ui.printError();
            Ui.printToBeforeFrom();
            Ui.lineBreaker();
            toBeforeFormatting = Ui.readCommand();
            setTo(toBeforeFormatting);
        }
    }

    @Override
    public LocalDateTime getTime() {
        return to;
    }

    /**
     * Override the toString method of its parent class Task.
     * Returns the type, start time, end time, state and name of task being printed and shown under a specified format
     * to the users.
     * e.g. [E][ ] Sleep (from: 23:59 OCT 16 2023 to: 08:00 OCT 17 2023)
     *
     * @return The type, start time, end time, done state and name of task under a specified format.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("HH:mm MMM dd yyyy")) + " to: " + to.format(DateTimeFormatter.ofPattern("HH:mm MMM dd yyyy")) + ")";
    }

    /**
     * Override the format method of its parent class Task.
     * Returns the type, start time, end time, state and name of task being recorded under a specified format in the
     * data file.
     * e.g. 0event Sleep /from 2023-10-16-2359 /to 2023-10-17-0800
     *
     * @return The type, start time, end time, done state and name of task under a specified format.
     */
    @Override
    public String format() {
        if (checkDone()) {
            return "1event " + getTaskName() + " /from " + from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HHmm")) + " /to " + to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HHmm"));
        } else {
            return "0event " + getTaskName() + " /from " + from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HHmm")) + " /to " + to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HHmm"));
        }
    }
}
