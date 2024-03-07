package chris.tasktypes;

public class ToDo extends Task {
    public ToDo(String[] description, boolean isDone){
        super(description[0], isDone);
    }
    public String toString() {
        return "[T] " + super.toString();
    }

    public String saveString() {
        return "T|" + super.getStatusIcon() + "|" + super.saveString();
    }
}
