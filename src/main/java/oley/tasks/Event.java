package oley.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import oley.commands.Ui;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

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

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("HH:mm MMM dd yyyy")) + " to: " + to.format(DateTimeFormatter.ofPattern("HH:mm MMM dd yyyy")) + ")";
    }

    @Override
    public String format() {
        if (checkDone()) {
            return "1event " + getTaskName() + " /from " + from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HHmm")) + " /to " + to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HHmm"));
        } else {
            return "0event " + getTaskName() + " /from " + from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HHmm")) + " /to " + to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HHmm"));
        }
    }
}
