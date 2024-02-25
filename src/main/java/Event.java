public class Event extends Deadline{
    String starttime;
    String Icon="E";
    public Event(boolean isDone, String description, String endtime, String starttime) {
        super(isDone, description, endtime);
        this.starttime = starttime;
    }

    @Override
    public String toString(){
        String DoneIcon = isDone? "x":" ";
        return "["+Icon+"]"+"["+DoneIcon+"]"+ " "+ description + "(from: "+starttime+ "):(by: "+endtime+")" ;
    }
}
