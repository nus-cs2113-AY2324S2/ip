public class Deadlines extends Task{
    private String by;
    public Deadlines(String description, String by) {
        super(description);
        this.by = by;
        this.type = TaskType.DEADLINE;
    }
    @Override
    public String toString(){
        return super.toString() + "(by: "+ by +")";
    }
}
