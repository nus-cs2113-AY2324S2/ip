public class ToDo extends Task{
    protected String dateOfDeadline;

    public ToDo(String description) {
        super(description);
        typeOfTask = "T";
        this.description = description.replace("todo", "");
    }

}
