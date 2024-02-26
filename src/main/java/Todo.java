public class Todo {
    protected String description;
    protected String status;
    protected String type;

    //constructor
    public Todo(String description) {
        this.description = description;
        status = "[ ]";
        type = "[T]";
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }

    public String formatTask() {
        return (type + status + description);
    }

    public String getWriteFormat() {
        return (type + status + description);
    }

    public void markStatus() {
        if (this.status.equals("[ ]")) {
            this.status = "[X]";
        } else {
            this.status = "[ ]";
        }
    }
}
