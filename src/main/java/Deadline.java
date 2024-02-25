public class Deadline extends Task {

    public Deadline(String description) {
        super(description);
        this.type = "D";

    }

    @Override
    public String toFileString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description;
    }

}
