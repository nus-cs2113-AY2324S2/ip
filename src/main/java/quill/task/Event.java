package quill.task;

import quill.exception.EmptyDateException;
import quill.exception.QuillException;

public class Event extends Task {
    protected String from;
    protected String to;
    public Event(String description) throws QuillException {
        super(description);
        int fromIndex = description.indexOf("/from");
        this.description = description.substring(0, fromIndex);
        int toIndex = description.indexOf("/to");
        this.from = description.substring(fromIndex + 5, toIndex);
        this.to = description.substring(toIndex + 3);
        if (this.description.isEmpty()) {
            Task.totalTasks--;
            throw new QuillException();
        } else if (this.from.isEmpty()) {
            Task.totalTasks--;
            throw new EmptyDateException("from");
        } else if (this.to.isEmpty()) {
            Task.totalTasks--;
            throw new EmptyDateException("to");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from:" + from + "to:" + to + ")";
    }

    @Override
    public String saveTask() {
        return "E | " + super.saveTask() + "/from" + from + "/to" + to;
    }
}
