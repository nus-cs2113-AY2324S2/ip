public class Task {
    private final String description;
    private final String type;
    private final boolean status;

    Task(String description, String type) {
        this.description = description;
        this.type = type;
        this.status = false;
    }

    Task(String description, String type, boolean status) {
        this.description = description;
        this.type = type;
        this.status = status;
    }

    Task(Task a, boolean status){
        this.description = a.description;
        this.type = a.type;
        this.status = status;
    }

    Task markTask() {
        return new Task(this, true);
    }

    Task unmarkTask() {
        return new Task(this,false);
    }

    String getDescription() {
        return this.description;
    }
    String getType() {
        return this.type;
    }

    boolean getStatus() {
        return this.status;
    }

    String encodeString() {
        return String.format("%s|%s|%s",
                this.type, this.status ? "X" : " ", this.description);
    }

    @Override
    public String toString() {
        return String.format(
                "%s|%s|%s",this.type, this.status ? "X" : " ", this.description);
    }
}
