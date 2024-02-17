public class Event extends Task {
    private final String start;
    private final String end;

    public Event(String taskName, String start, String end, boolean isCompleted) {
        super(taskName, isCompleted);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.start + " to: " + this.end + ")";
    }

    @Override
    public String getStringRepresentation() {
        return "event " + taskName + " /from " + this.start + " /to " + this.end + getIsCompletedString();
    }

    public static Event getTask(String currentInput)
        throws EmptyTaskDescription, InvalidTaskArguments {
        try {
            boolean isCompleted = currentInput.contains(Task.IS_COMPLETED_STRING);
            currentInput = currentInput.replaceAll(Task.IS_COMPLETED_STRING, "");

            int idxOfStart = currentInput.indexOf(" /from ");
            int idxOfEnd = currentInput.indexOf(" /to ");

            if (idxOfStart == -1 || idxOfEnd == -1) {
                throw new InvalidTaskArguments();
            }

            // Extract after _event_, which is 5 characters long
            String taskName = currentInput.substring(5, idxOfStart);
            taskName = taskName.trim();
            if (taskName.isEmpty()) {
                throw new EmptyTaskDescription();
            }

            // Extract start after _ /from _, which is 7 characters long, and before idxOfEnd
            String start = currentInput.substring(idxOfStart + 7, idxOfEnd);
            start = start.trim();

            // Extract end after _ /to _, which is 5 characters long
            String end = currentInput.substring(idxOfEnd + 5);
            end = end.trim();

            return new Event(taskName, start, end, isCompleted);

        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskArguments();
        }

    }
}
