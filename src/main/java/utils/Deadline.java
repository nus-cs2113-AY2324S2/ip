package utils;

public class Deadline extends Task {

    public Deadline(String description) {
        super(description);
    }

    @Override
    public void printTask() {
        if (this.isDone) {
            System.out.println("[D][X] " + this.description);
        } else {
            System.out.println("[D][ ] " + this.description);
        }
    }
}