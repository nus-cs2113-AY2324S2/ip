package magus.task.variant;

import magus.exception.ArgumentNotFoundException;
import magus.task.Task;
import magus.task.storage.Parser;

import static magus.task.storage.Parser.DELIMITER;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public static Todo parse(String taskInfo) throws ArgumentNotFoundException {
        boolean hasNoDescription = taskInfo.isEmpty();
        if (hasNoDescription) {
            String errorContext = "Missing description";
            throw new ArgumentNotFoundException(errorContext);
        }
        return new Todo(taskInfo);
    }

    public static Todo parseStoredTaskInfo(Parser parser) {
        try {
            String description = parser.getTaskInfo();
            Todo todo = parse(description);

            boolean isDone = parser.isDone();
            if (isDone) {
                todo.markAsDone();
            } else {
                todo.unmarkAsDone();
            }

            return todo;
        } catch (ArgumentNotFoundException e) {
            return null;
        }
    }

    @Override
    public char getBadge() {
        return 'T';
    }

    @Override
    public String toString() {
        return String.format("[%c]%s", getBadge(), super.toString());
    }

    @Override
    public String toStoredString() {
        String formatString = "%c" + DELIMITER + "%s";
        return String.format(formatString, getBadge(), super.toStoredString());
    }
}
