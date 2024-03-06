public class Todo extends Task{

    public Todo(String description) {
        super(description);
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
