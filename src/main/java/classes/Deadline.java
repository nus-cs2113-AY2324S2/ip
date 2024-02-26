package classes;

public class Deadline extends Task {

    public Deadline(String description, String time) {

        super(description);
        this.time = time;
    }

    @Override
    public void printTask() {
        if (this.isDone) {
            System.out.println("[D][X] " + this.description + " (" + this.time + ")");
        } else {
            System.out.println("[D][ ] " + this.description + " (" + this.time + ")");
        }
    }

    @Override
    public String getTaskType() {
        return "Deadline";
    }
}