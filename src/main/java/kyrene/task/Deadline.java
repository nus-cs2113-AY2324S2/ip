package kyrene.task;

import kyrene.exception.KyreneMissingTimeException;
import kyrene.ui.Ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Deadline extends Task{
    protected static DateTimeFormatter formatterInput = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    protected static DateTimeFormatter formatterOutput = DateTimeFormatter.ofPattern("HH:mm MMM dd yyyy");
    protected LocalDateTime deadline;

    public Deadline() {
        super(null);
    }

    public Deadline(String line) throws KyreneMissingTimeException {
        super(line);
        taskType = "D";
        int dividerIndex = line.indexOf("/by");
        if(dividerIndex == -1){
            throw new KyreneMissingTimeException();
        }
        String deadline = line.substring(dividerIndex + "/by ".length());
        setDeadline(deadline);
        taskName = line.substring(0, dividerIndex - " ".length());
        setTaskName(taskName);
    }

    public String getDeadline() {
        return deadline.format(formatterOutput);
    }

    public void setDeadline(String deadline) {
        try {
            this.deadline = LocalDateTime.parse(deadline, formatterInput);
        } catch (DateTimeParseException e) {
            setDeadlineWithoutTime(deadline);
        }
    }

    private void setDeadlineWithoutTime(String deadline) {
        try {
            this.deadline = LocalDateTime.parse(deadline + " 2359", formatterInput);
        } catch (DateTimeParseException e) {
            setDeadlineWithoutDate(deadline);
        }
    }

    private void setDeadlineWithoutDate(String deadline) {
        try {
            String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            this.deadline = LocalDateTime.parse(today + " " + deadline, formatterInput);
        } catch (DateTimeParseException e) {
            Ui.showErrorInvalidDdlTimeFormat();
            deadline = Ui.readCommand();
            setDeadline(deadline);
        }
    }

    public boolean isBefore(LocalDateTime time) {
        if (deadline.isAfter(time)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (by %s)", taskType, doneSymbol, taskName, getDeadline());
    }

    @Override
    public String format() { return String.format("%b deadline %s /by %s\n", isDone, taskName, deadline.format(formatterInput));}

}
