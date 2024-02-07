public class ToDo extends Task{

    protected String deadlineDate;

    public ToDo(String description) {
        super(description);
        taskType = "T";
        this.description = description.replace("todo", "");
    }

}
