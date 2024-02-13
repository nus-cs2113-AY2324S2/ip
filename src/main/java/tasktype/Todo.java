package tasktype;

import tasktype.Task;

public class Todo extends Task {
    private final static String ICON_TYPE = "[T]";

    public Todo(String description){
        super(description);
    }

    public String toString(){
        return ICON_TYPE + super.toString();
    }
}
