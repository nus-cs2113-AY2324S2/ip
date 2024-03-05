package bossman.task;

/**
 * Todo class represents a todos task in the task management application.
 * It is a subclass of Task.
 */
public class Todo extends Task {

    public Todo(String task, boolean isMark) {
        super(task, isMark);
        this.typeSymbol = "[T]";
    }

    /**
     * Formats the todo task for saving to file.
     * Overrides the method in the superclass to provide specific formatting for todo tasks.
     *
     * @return the formatted string representing the todo task for saving
     */
    @Override
    public String formatForSave() {
        return "T" + "," + isMark + "," + DESCRIPTION ;
    }
}