package quill.task;

import quill.exception.EmptyDateException;
import quill.exception.QuillException;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description) throws QuillException {
        super(description);
        int index = description.indexOf("/by");
        this.description = description.substring(0, index);
        this.by = description.substring(index + 3);
        if (this.description.isEmpty()) {
            Task.totalTasks--;
            throw new QuillException();
        } else if (by.isEmpty()) {
            Task.totalTasks--;
            throw new EmptyDateException("by");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + by + ")";
    }
}
