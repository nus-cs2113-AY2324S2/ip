package Tasks;


public class ToDo extends Task {
    public ToDo(String description) throws ArrayIndexOutOfBoundsException{
        super(description);
        //System.out.println(description + "yes");
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
