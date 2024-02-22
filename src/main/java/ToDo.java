public class ToDo extends Task {

    public ToDo(String desc) {
        super(desc);
    }

    @Override
    public String convertToCommand() {
        return "todo " + super.convertToCommand();
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
