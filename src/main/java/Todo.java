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

    public String toFileString() {
        return "T|" + (isDone ? 1 : 0) + "|" + description;
    }

    public static Task fromFileString(String line) {
        String[] items = line.split("\\|");
        Todo todo = new Todo(items[2]);
        if(items[1].equals("1")) {
            todo.setIsDone();
        }
        return todo;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
