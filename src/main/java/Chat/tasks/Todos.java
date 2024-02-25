package Chat.tasks;

public class Todos extends Task{
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
