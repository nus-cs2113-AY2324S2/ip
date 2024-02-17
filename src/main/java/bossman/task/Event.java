package bossman.task;
public class Event extends Todo {
    private final String TYPE_SYMBOL = "[E]";
    private final String FROM;
    private final String TO;
    public Event(String task, boolean isMark, String from, String to) {
        super(task, isMark);
        this.FROM = from;
        this.TO = to;
    }
    @Override
    public String getTypeSymbol() {
        return TYPE_SYMBOL;
    }

    @Override
    public void printTask() {
        super.printTask();
        System.out.print(" (from:" + FROM + " to:" + TO + ")");
    }

    @Override
    public String formatForSave() {
        return "E" + "," + isMark + "," + DESCRIPTION + "," + FROM + "," + TO;
    }
}
