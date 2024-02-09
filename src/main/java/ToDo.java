public class ToDo extends Task {
    public ToDo (int order,boolean marked, String toDoName) {
        super(order,marked,toDoName);
    }

    @Override
    public String getType() {
        return "T";
    }

    @Override
    public String displayCurrentTask () {
        String typeCheckbox = "[T]";
        return typeCheckbox + " " + this.doneCheckbox + this.taskName;
    }

}
