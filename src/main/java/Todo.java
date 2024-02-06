//seems useless can probably just delete
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public  String getType() {
        return "T";
    }

}
