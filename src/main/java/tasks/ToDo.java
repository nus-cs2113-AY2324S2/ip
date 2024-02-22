package tasks;

public class ToDo extends Task {
    public ToDo() {
        super();
    }

    public ToDo(String name) {
        super(name);
    }

    public ToDo(String name, boolean isDone) {
        super(name, isDone);
    }
    
    @Override
    public String getType() {
        return "T";
    }

    @Override
    public String toString() {
        return ("[T]" + super.toString());
    }
}
