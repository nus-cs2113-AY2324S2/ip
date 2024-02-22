public class Event extends Task{
    protected String from;
    protected String to;
    public Event(String label, String from, String to) {
        super(label);
        this.from = from;
        this.to = to;
    }

    public static String[] getInterval(String input) {

        String[] results = new String[Constant.EVENT_PARAMETERS];


        int indexFrom = input.indexOf("/from");
        int indexTo = input.indexOf("/to");

        String label = input.substring(0, indexFrom).trim();


        String fromSubstring = input.substring(indexFrom + Constant.EVENT_FROM_OFFSET, indexTo).trim();
        String toSubstring = input.substring(indexTo + Constant.EVENT_TO_OFFSET).trim();

        results[0] = label;
        results[1] = fromSubstring;
        results[2] = toSubstring;

        return results;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
    @Override
    public String getType() {
        return "EVENT";
    }
    @Override
    public String getLabel() {
        return label;
    }
    @Override
    public String getRange() {
        return from + ":" + to;
    }
}

