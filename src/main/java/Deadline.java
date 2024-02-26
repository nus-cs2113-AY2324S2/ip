public class Deadline extends Todo {
    protected String date;

    //constructor for Deadline
    public Deadline(String description, String date) {
        super(description);
        this.date = date;
        type = "[D]";
    }

    public String getDate() {
        return date;
    }

    public String formatTask() {
        return (type + status + description + "(by:" + date + ")");
    }

    public String getWriteFormat() {
        return (type + status + description + "/by" + date);
    }
}
