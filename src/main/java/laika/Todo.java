package laika;
public class Todo extends Task {


    public Todo(String description) {
        super(description);
        this.type = "[T]";
    }

    @Override
    public String toString() {
        return type + super.toString();
    }

}