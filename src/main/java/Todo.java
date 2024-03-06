public class Todo extends Task{

    public Todo(String description) {
        super(description);
    }

    /**
     * Converts the Todo object into a string representation suitable for file storage.
     * The format of the string is "T|isDone|description", where:
     * - "T" indicates the type of task (Todo),
     * - "isDone" is 1 if the todo is marked as done, and 0 otherwise,
     * - "description" is the todo's description.
     *
     * @return A string representation of the Todo object for file storage.
     */
    public String toFileString() {

        return "T|" + (isDone ? 1 : 0) + "|" + description;
    }

    /**
     * Creates a Todo object from its string representation stored in a file.
     * The expected format of the string is "T|isDone|description", where each part is separated by "|".
     * This method parses the string, constructs a new Todo object with the specified description,
     * and sets its completion status according to the parsed value.
     *
     * @param line The string representation of a Todo, read from a file.
     * @return A Todo object with properties set according to the parsed string.
     */
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
