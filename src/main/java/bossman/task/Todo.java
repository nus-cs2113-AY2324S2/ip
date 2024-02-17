package bossman.task;
public class Todo extends Task {
    public Todo(String task, boolean isMark) {
        super(task, isMark);
        this.typeSymbol = "[T]";
    }

    @Override
    public String formatForSave() {
        return "T" + "," + isMark + "," + DESCRIPTION ;
    }
}