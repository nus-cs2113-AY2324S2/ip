package MassimoBoi;

public class Event extends Task {
    /**
     * Creates a new event task.
     *
     * @param description the description of the task.
     * @param from the start date (and time) of the task.
     * @param to the end date (and time) of the task.
     */
    public Event(String description, String from, String to){
        super(description + String.format("(from:%s to:%s)",from,to));
    }

    /**
     * Returns the task type as Event.
     */
    @Override
    public String taskType(){
        return "[E]";
    }
}
