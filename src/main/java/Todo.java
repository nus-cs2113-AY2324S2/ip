public class Todo extends Task{

    public Todo(String description) {
        super(description);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String saveTaskFormat() {
        String marked = isDone ? "1" : "0";
        return "T | " + marked + " | " + this.description + System.lineSeparator();
    }


}
