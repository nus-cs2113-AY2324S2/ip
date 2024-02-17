public class ToDo extends Task {
    public ToDo(String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public static ToDo getTask(String currentInput)
        throws EmptyTaskDescription, InvalidTaskArguments {
        try {
            // Extract after _todo_, which is 4 characters long
            String taskName = currentInput.substring(4);
            taskName = taskName.trim();
            if (taskName.isEmpty()) {
                throw new EmptyTaskDescription();
            }

            return new ToDo(taskName);

        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskArguments();
        }

    }
}
