public class Task {
    private final String input;
    private final String type;
    private final boolean status;

    Task(String input, String type) {
        this.input = input;
        this.type = type;
        this.status = false;
    }

    Task(String input, String type, boolean status) {
        this.input = input;
        this.type = type;
        this.status = status;
    }

    Task(Task a, boolean status){
        this.input = a.input;
        this.type = a.type;
        this.status = status;
    }

    Task markTask() {
        return new Task(this, true);
    }

    Task unmarkTask() {
        return new Task(this,false);
    }

    String getInput() {
        return this.input;
    }
    String getType() {
        return this.type;
    }

    boolean getStatus() {
        return this.status;
    }

    String encodeString() {
        return String.format("%s|%s|%s",
                this.type, this.status ? "X" : " ", this.input);
    }

    @Override
    public String toString() {
        return String.format(
                "%s|%s|%s",this.type, this.status ? "X" : " ", this.input);
    }
}
