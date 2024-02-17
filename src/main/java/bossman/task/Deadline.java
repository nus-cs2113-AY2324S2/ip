package bossman.task;
public class Deadline extends Todo {
    private final String TYPE_SYMBOL = "[D]";
    private final String BY;
    public Deadline(String task, boolean isMark, String by) {
        super(task, isMark);
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

    @Override
    public String formatForSave() {
        return "D" + "," + isMark + "," + DESCRIPTION + "," + BY;
    }
}
