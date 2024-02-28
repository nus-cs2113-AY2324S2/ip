public class Event extends Deadline{
    String starttime;
    String Icon="E";

    public Event(boolean isDone, String description, String endtime, String starttime) {
        super(isDone, description, endtime);
        this.starttime = starttime;
    }

    @Override
    public String toString(){
        String DoneIcon = this.isDone? "x":" ";
        return "["+Icon+"]"+"["+DoneIcon+"]"+ " "+ this.description + "(from: "+starttime+ ") (by: "+this.endtime+")" ;
    }
}
