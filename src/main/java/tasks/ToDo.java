package tasks;

import exceptions.EmptyTaskDescription;
import exceptions.InvalidTaskArguments;

public class ToDo extends Task {
    public ToDo(String taskName, boolean isCompleted) {
        super(taskName, isCompleted);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String getStringRepresentation() {
        return "todo " + taskName + getIsCompletedString();
    }

    public static ToDo getTask(String currentInput)
        throws EmptyTaskDescription, InvalidTaskArguments {
        try {
            boolean isCompleted = currentInput.contains(Task.IS_COMPLETED_STRING);
            currentInput = currentInput.replaceAll(Task.IS_COMPLETED_STRING, "");

            // Extract after _todo_, which is 4 characters long
            String taskName = currentInput.substring(4);
            taskName = taskName.trim();
            if (taskName.isEmpty()) {
                throw new EmptyTaskDescription();
            }

            return new ToDo(taskName, isCompleted);

        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskArguments();
        }

    }
}
