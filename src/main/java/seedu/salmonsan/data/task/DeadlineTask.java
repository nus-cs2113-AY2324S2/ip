package seedu.salmonsan.data.task;

import seedu.salmonsan.data.exception.SalmonMissingArgument;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.time.temporal.UnsupportedTemporalTypeException;

public class DeadlineTask extends Task {
    protected String deadline;
    protected LocalDate deadlineLocalDate;


    public DeadlineTask() throws SalmonMissingArgument {
        this(false, "DEFAULT /by DEFAULT");
    }

    public DeadlineTask(boolean status, String description) throws SalmonMissingArgument {
        this.isDone = status;
        this.description = setDeadline(description);
        this.type = "D";
    }

    /**
     * setDeadline parse argument of inputted query,
     * get the deadline,
     * assign it to the object
     *
     * @param argument
     * @return String description of deadlineTask
     */
    public String setDeadline(String argument) throws SalmonMissingArgument {
        if (argument == null) {
            throw new SalmonMissingArgument();
        }
        // find index of '/'
        int index = argument.indexOf('/');
        deadline = argument.substring(index + 4);
        // check if deadline is a date
        deadlineLocalDate = isValidDate(deadline);
        return argument.substring(0, index - 1);
    }

    /**
     * check if deadline inputted is a date
     * if so, store it in deadlineLocalDate
     * if not, let deadlineLocalDate be null
     *
     * @param s
     * @return LocalDate deadlineLocalDate if valid
     */
    public LocalDate isValidDate(String s) {
        try {
            LocalDate d1 = LocalDate.parse(s);
            return d1;
        } catch (DateTimeParseException e) {
            return null;
        } catch (UnsupportedTemporalTypeException e) {
            System.out.println("Unsupported field");
            return null;
        }
    }

    /**
     *
     * @return String containing deadline of task
     */
    public String getDeadline() {
        if (deadlineLocalDate != null) {
            return deadlineLocalDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } else {
            return deadline;
        }
    }

    /**
     * print the task in proper format
     */
    @Override
    public void printTask() {
        if (isDone) {
            System.out.println("[" + this.type + "][X] " + this.getDescription() + " (by: " + this.getDeadline() + ")");
        } else {
            System.out.println("[" + this.type + "][ ] " + this.getDescription() + " (by: " + this.getDeadline() + ")");
        }
    }

    /**
     * convert task into a string and returns it
     * @return
     */
    @Override
    public String toString() {
        String answer;
        answer = this.type + " | " + this.isDone + " | " + this.description + " | " + this.deadline;
        return answer;
    }

    /**
     * parse string from .txt file into a task
     * @param s
     * @throws SalmonMissingArgument
     * @throws StringIndexOutOfBoundsException
     */
    @Override
    public void parse(String s) throws SalmonMissingArgument, StringIndexOutOfBoundsException {
        // format T | true | description
        int firstSlash = s.indexOf('|');
        int secondSlash = s.indexOf('|', firstSlash + 1);
        int thirdSlash = s.indexOf('|', secondSlash + 1);
        int spaceAfterIsDone = s.indexOf(' ', firstSlash + 2);

        String isDoneStatus = s.substring(firstSlash + 2, spaceAfterIsDone);
        String description = s.substring(secondSlash + 2, thirdSlash - 1);
        String deadline = s.substring(thirdSlash + 2);

        description.replaceAll("\\s+","");

        this.setBoolean(Boolean.parseBoolean(isDoneStatus));
        this.description = description;
        this.deadline = deadline;
    }
}
