package chris.tasktypes;

import chris.customexceptions.illegalToDoInput;

public class ToDo extends Task {
    public ToDo(String description, boolean isDone) throws illegalToDoInput {
        super(description);
        if (description.isEmpty()) {
            throw new illegalToDoInput();
        }
        this.isDone = isDone;
    }
    public String toString() {
        return "[T] " + super.toString();
    }

    public String saveString() {
        return "T|" + super.getStatusIcon() + "|" + super.saveString();
    }
}
