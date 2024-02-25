public class Deadline extends Todo{
    String endtime;
    String Icon="D";

    public Deadline(boolean isDone, String description, String endtime) {
        super(isDone, description);
        this.endtime = endtime;
    }

    @Override
    public String toString(){
        String DoneIcon = isDone? "x":" ";
        return "["+Icon+"]"+"["+DoneIcon+"]"+ description + "(by:"+ endtime+")";
    }

}
