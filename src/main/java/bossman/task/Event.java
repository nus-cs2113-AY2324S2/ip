package bossman.task;

import bossman.ui.Ui;

/**
 * Event class represents an event task in the task management application.
 * It is a subclass of Todo.
 */
public class Event extends Todo {
    private final String FROM; // Starting time of the event
    private final String TO; // Ending time of the event

    public Event(String task, boolean isMark, String from, String to) {
        super(task, isMark);
        this.FROM = from;
        this.TO = to;
        this.typeSymbol = "[E]";
    }

    /**
     * Prints the event task with its mark status and time duration.
     * Overrides the method in the superclass to include time duration.
     */
    @Override
    public void printTask() {
        super.printTask();
        Ui.printMessageNoSepSameLine(" (from:" + FROM + " to:" + TO + ")");
    }

    /**
     * Formats the event task for saving to file.
     * Overrides the method in the superclass to provide specific formatting for event tasks.
     *
     * @return the formatted string representing the event task for saving
     */
    @Override
    public String formatForSave() {
        return "E" + "," + isMark + "," + DESCRIPTION + "," + FROM + "," + TO;
    }
}
