package Chat.tasks;

public class Todos extends Task{
    /**
     * Construct a Todos class that takes in task description.
     * Inherits from superclass task.
     * @param description The description of the task of subclass todos.
     */
    public Todos(String description) {
        super(description);
        this.type = TaskType.TODO;
        shortType = this.type.name().substring(0, 1);
    }
    @Override
    public String toString() {
        return "[" + shortType +"]" + super.toString();
    }

}
