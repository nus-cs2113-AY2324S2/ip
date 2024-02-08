public class Todo extends Task{
    protected static String TYPE = "todo";
    public Todo(String description) {
        super(description);
    }

    public String getDescription(){
        return "[T][" + super.getStatusIcon() + "] " + super.getDescription();
    }

    public String getTYPE() {
        return this.TYPE;
    }
}
