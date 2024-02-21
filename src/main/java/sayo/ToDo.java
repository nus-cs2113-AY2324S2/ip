package sayo;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
    
    public String toFileFormat() {
        return "T | " + super.toFileFormat();
    }

    public static Task fromFileFormat(String fileFormat) {
        // Assuming fileFormat is in the correct format for ToDo
        String[] parts = fileFormat.split(" \\| ");
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();

        ToDo todo = new ToDo(description);
        if (isDone) {
            todo.markAsDone();
        }
        return todo;
    }

}