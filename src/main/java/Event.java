import static com.sun.beans.introspect.PropertyInfo.Name.description;

public class Event extends Task{
    private String at;

    public Event (String description, String at){
        super();
        this.at = at;
    }

    public String getAt(){
        return at;
    }

    public String toString() {
        return "[E] " + super.toString() + description + " (at: " + at + ")";
    }
}
