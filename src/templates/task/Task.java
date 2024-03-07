package templates.task;

public abstract class Task{
    private String taskString = "Null";
    private Boolean completed = false;
    private String typeCode;

    public Task(String args, String typeCode, String typeName) throws Exception {
        if (args.isBlank()) {
            throw new Exception(String.format("The description of a %s cannot be empty.\n", typeName));
        }
        args.strip();
        this.taskString = args;
        this.completed = false;
        this.typeCode = typeCode;
    }

    public Boolean getCompleted() {
        return this.completed;
    }

    public String getTaskString() {
        return this.taskString;
    }

    public void setCompleted(Boolean args) {
        this.completed = args;
    }

    @Override
    public String toString() {
        return String.format("\t[%s][%s] %s", typeCode, (completed ? "X" : " "), taskString);
    }
}
