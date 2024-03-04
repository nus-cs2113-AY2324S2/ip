package oley.tasks;

import oley.commands.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task of Deadline type. A Deadline object corresponds to a task with its name, due time, and state of
 * done being specified.
 * It is a subclass of Task.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructor of Deadline.
     * Identifies due time in string type through index of "/by".
     *
     * @param description The name and time of the deadline entered by user
     * @throws TimingNotFoundException If the time of due is not specified.
     */
    public Deadline(String description) throws TimingNotFoundException {
        super(description);
        if (!description.contains("/by")) {
            throw new TimingNotFoundException();
        }
        int dividerIndex = description.indexOf("/");
        String taskName = description.substring(0, dividerIndex - 1);
        setTaskName(taskName);
        String byBeforeFormatting = description.substring(dividerIndex + 4);
        setBy(byBeforeFormatting);
    }

    /**
     * Set the LocalDateTime object 'by' from its string type.
     * If the time is not provided in the correct format, or the due time has already passed, user will be asked to
     * enter the due time again until the correct time is read in.
     *
     * @param byBeforeFormatting The due time under String type.
     */
    public void setBy(String byBeforeFormatting) {
        try {
            this.by = LocalDateTime.parse(byBeforeFormatting, DateTimeFormatter.ofPattern("yyyy-MM-dd-HHmm"));
        } catch (DateTimeParseException e) {
            Ui.printError();
            Ui.printCorrectFormat("by");
            Ui.lineBreaker();
            byBeforeFormatting = Ui.readCommand();
            setBy(byBeforeFormatting);
        }
        if (by.isBefore(LocalDateTime.now())) {
            Ui.printError();
            Ui.printDeadlinePassed();
            Ui.lineBreaker();
            byBeforeFormatting = Ui.readCommand();
            setBy(byBeforeFormatting);
        }
    }

    /**
     * Override the toString method of its parent class Task.
     * Returns the type, due time, state and name of task being printed and shown under a specified format
     * to the users.
     * e.g. [D][#] Sleep (by: 23:00 OCT 17 2023)
     *
     * @return The type, due time, done state and name of task under a specified format.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("HH:mm MMM dd yyyy")) + ")";
    }

    @Override
    public LocalDateTime getTime() {
        return by;
    }

    /**
     * Override the format method of its parent class Task.
     * Returns the type, due time, state and name of task being recorded under a specified format in the data file.
     * e.g. 1deadline /by 2023-10-17-2300
     *
     * @return The type, due time, done state and name of task under a specified format.
     */
    @Override
    public String format() {
        if (checkDone()) {
            return "1deadline " + getTaskName() + " /by " + by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HHmm"));
        } else {
            return "0deadline " + getTaskName() + " /by " + by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HHmm"));
        }
    }
}
