package oley.tasks;

import oley.commands.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDateTime by;

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

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("HH:mm MMM dd yyyy")) + ")";
    }

    @Override
    public LocalDateTime getTime() {
        return by;
    }

    @Override
    public String format() {
        if (checkDone()) {
            return "1deadline " + getTaskName() + " /by " + by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HHmm"));
        } else {
            return "0deadline " + getTaskName() + " /by " + by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HHmm"));
        }
    }
}
