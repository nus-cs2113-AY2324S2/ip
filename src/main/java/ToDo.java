public class ToDo extends Task {

    public ToDo(String label) {
        super(label);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String getType() {
        return "TODO";
    }
    @Override
    public String getRange() {
        return "";
    }
}
