public class ToDo extends Task{
    protected String dateOfDeadline;

    public ToDo(String description) {
        super(description);
        typeOfTask = "T";
        this.description = description.replace("todo", "");
    }

    @Override
    public String toFileString() {
        return "T|" + super.toFileString(); // Prefix with "T" to indicate Todo
    }

}
