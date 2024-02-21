package Tasks;


public class ToDo extends Task {
    public ToDo(String description) throws ArrayIndexOutOfBoundsException{
        super(description);

    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
