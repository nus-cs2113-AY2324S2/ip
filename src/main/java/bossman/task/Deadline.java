package bossman.task;
public class Deadline extends Todo {
    private final String TYPE_SYMBOL = "[D]";
    private final String BY;
    public Deadline(String task, String by) {
        super(task);
        this.BY = by;
    }

    @Override
    public String getTypeSymbol() {
        return TYPE_SYMBOL;
    }

    @Override
    public void printTask() {
        super.printTask();
        System.out.print("(do by:" + BY + ")");
    }
}
