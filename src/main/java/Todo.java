public class Todo extends Task{

    public Todo(String description) {
        super(description);
    }

    public static Task fromString(String line) throws MissingParameterException {
        String todo = line.replace("todo", "");
        String todoName = todo.trim();
        if(todoName.isEmpty()) {
            throw new MissingParameterException("todo");
        }
        return new Todo(todoName);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
