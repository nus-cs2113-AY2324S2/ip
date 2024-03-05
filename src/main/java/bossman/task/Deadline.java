package bossman.task;

import bossman.ui.Ui;
import java.time.LocalDate;

/**
 * Deadline class represents an deadline task in the task management application.
 * It is a subclass of Todo.
 */
public class Deadline extends Todo {
    private final LocalDate BY; //Deadline date
    public Deadline(String task, boolean isMark, LocalDate by) {
        super(task, isMark);
        this.BY = by;
        this.typeSymbol = "[D]";
    }

    /**
     * Prints the deadline task with its mark status and time duration.
     * Overrides the method in the superclass to include deadline date.
     */
    @Override
    public void printTask() {
        super.printTask();
        Ui.printMessageNoSepSameLine("(do by:" + BY + ")");
    }

    /**
     * Formats the deadline task for saving to file.
     * Overrides the method in the superclass to provide specific formatting for deadline tasks.
     *
     * @return the formatted string representing the deadline task for saving
     */
    @Override
    public String formatForSave() {
        return "D" + "," + isMark + "," + DESCRIPTION + "," + BY;
    }
}
