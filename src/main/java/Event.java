public class Event extends Task{
    private String from;
    private String to;
    public Event(String label, String from, String to) {
        super(label);
        this.from = from;
        this.to = to;
    }

    public static String[] getInterval(String input) {

        String[] results = new String[3];


        int indexFrom = input.indexOf("/from");
        int indexTo = input.indexOf("/to");

        String label = input.substring(0, indexFrom).trim();


        String fromSubstring = input.substring(indexFrom + 6, indexTo).trim();
        String toSubstring = input.substring(indexTo + 3).trim();

        results[0] = label;
        results[1] = fromSubstring;
        results[2] = toSubstring;

        return results;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}

