public class Event extends Task {

    public Event(String description) {
        super(description.substring(6,description.indexOf(" /from")) + " (from: " + description.substring(description.indexOf("/from")+6,description.indexOf(" /to"))
                + " to: " + description.substring(description.indexOf("/to")+4) + ")" );
        this.type = "E";
    }

}
