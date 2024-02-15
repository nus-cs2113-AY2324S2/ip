package n.task;

import n.exceptions.EmptyTaskDescriptionException;

public class ToDo extends Task{
    public ToDo(String description, int arrayIndex) throws EmptyTaskDescriptionException {
        super(description, arrayIndex);
        if (this.description.isEmpty()){
            throw new EmptyTaskDescriptionException();
        }
        this.taskType = Type.ToDo;
    }

    @Override
    public String toString() {
        return this.getIndex()+ ". [T] ["+ this.getCheckMark()+"] " +this.getDescription();
    }
}
