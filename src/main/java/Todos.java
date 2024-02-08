public class Todos extends Task{
    public Todos(String description) {
        super(description);
        this.type = TaskType.TODO;
    }
    public String toString() {
        return "[T]" + super.toString();
    }

}
