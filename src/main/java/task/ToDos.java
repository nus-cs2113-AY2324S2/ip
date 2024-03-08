package task;

public class ToDos extends Tasks{

    /**
     * The ToDo class represents a task without any date and time attached to it.
     */
    public ToDos(String description) {
        super(description);
    }

    @Override
    public String toSave() {
        return "T / " + super.toSave();
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
