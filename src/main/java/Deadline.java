public class Deadline extends Task{
    private final static String ICON_TYPE = "[D]";
    protected String date;

    public Deadline(String description, String by){
        super(description);
        this.date = by;
    }

    public String toString(){
        return ICON_TYPE + super.toString() + "(by:" + date + ")";
    }
}
