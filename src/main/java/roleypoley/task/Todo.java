package roleypoley.task;

import roleypoley.exception.RoleyPoleyParseException;
import roleypoley.task.Task;

/**
 * Represents Todo tasks to be completed
 */
public class Todo extends Task {

    /**
     * Assumption: Every field must be present and not NULL
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String getDescription() {
        return super.getDescription() ;
    }
}
