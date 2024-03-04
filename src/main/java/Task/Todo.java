package Task;

import Task.Task;

/**
 * Represents the Todo Task. A <code>Todo</code> object corresponds to
 * a Todo Command
 */
public class Todo extends Task {
    public Todo(String description){
        super(description);
    }
    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}
