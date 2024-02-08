public class Event extends Task{
    public Event(String description, String from, String to){
        super(description + String.format("(from:%s to:%s)",from,to));
    }

    public String taskType(){
        return "[E]";
    }
}
