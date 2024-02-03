public class Event extends Todo{
    private final String TYPE_SYMBOL = "[E]";
    private final String FROM;
    private final String TO;
    public Event(String task, String from, String to) {
        super(task);
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
}
