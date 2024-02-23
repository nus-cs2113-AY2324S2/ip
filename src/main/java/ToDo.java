public class ToDo extends Task{
    protected String deadlineDate;

    @Override
    public String saveTaskRepresentation() {
        return "T|" + super.saveTaskRepresentation(); // Prefix with "T" to indicate Todo
    }

    public ToDo(String description) {
        super(description);
        taskType = "T";
        this.description = description.replace("todo", "");
    }

}
