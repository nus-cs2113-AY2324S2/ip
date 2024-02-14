package Chat.tasks;

public class Deadlines extends Task{
    private String by;
    public Deadlines(String description, String by) {
        super(description);
        this.by = by;
        this.type = TaskType.DEADLINE;
        shortType = this.type.name().substring(0, 1);
    }
    @Override
    public String toString(){
        return "[" + shortType +"]" + super.toString() + "(by: "+ by +")";
    }
}
