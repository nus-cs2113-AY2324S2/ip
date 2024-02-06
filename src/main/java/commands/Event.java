package commands;

public class Event extends Task {
    protected String start;
    protected String end;

    public Event() {
    }

    public Event(String name) {
        super(name);
        this.start = "None";
        this.end = "None";
    }

    public Event(String name, String start, String end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    public static String parseName(String command) {
        return command.substring(command.indexOf(" ") + 1, command.indexOf("/")-1);
    }
    public static String parseStart(String command) {
        return command.substring(command.indexOf("/from") + 5, command.indexOf("/to") - 1);
    }
    public static String parseEnd(String command) {
        return command.substring(command.indexOf("/to") + 3);
    }

    @Override
    public String toString() {
        if (isDone){
            return "[E][X] " + name + " (from:" + start + " to:" + end + ")";
        }
        else {
            return "[E][ ] " + name + " (from:" + start + " to:" + end + ")";
        }
    }
}
