package classes;

public class Event extends Task {

    public Event(String description, String time) {

        super(description);
        this.time = time;
    }
    @Override
    public void printTask() {
        if (this.isDone) {
            System.out.println("[E][X] " + this.description + " (" + this.time + ")");
        } else {
            System.out.println("[E][ ] " + this.description + " (" + this.time + ")");
        }
    }



    @Override
    public String getTaskType() {
        return "Event";
    }
}