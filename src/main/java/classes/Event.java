package classes;

public class Event extends Task {

    public Event(String description) {
        super(description);
    }
    @Override
    public void printTask() {
        if (this.isDone) {
            System.out.println("[E][X] " + this.description);
        } else {
            System.out.println("[E][ ] " + this.description);
        }
    }

    @Override
    public String getTaskType() {
        return "Event";
    }
}