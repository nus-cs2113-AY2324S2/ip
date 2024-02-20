package commands;

public class Deadline extends Task {
    protected String date;

    public Deadline(String name) {
        super(name);
        this.date = "None";
    }

    public Deadline(String name, String date) {
        super(name);
        this.date = date;
    }
    public Deadline(String name, boolean status, String date) {
        super(name);
        this.isDone = status;
        this.date = date;
    }

    public static String parseName(String command) {
        return command.substring(command.indexOf(" ") + 1, command.indexOf("/")-1);
    }
    public static String parseDate(String command) {
        return command.substring(command.indexOf("/by") + 3);
    }


    @Override
    public String toString() {
        if (isDone){
            return "[D][X] " + name + " (by:" + date + ")";
        }
        else {
            return "[D][ ] " + name + " (by:" + date + ")";
        }
    }
}
