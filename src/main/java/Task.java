public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }


    // Method to mark the tasks as done
    public void markAsDone(){
        this.isDone = true;
    }

    // Method to mark the tasks as undone
    public void markAsUndone(){
        this.isDone = false;
    }

    public static Task parseTask(String line) {
        String[] parts = line.split(" \\| ");
        boolean isDone = parts[1].equals("1");
        Task task;

        switch (parts[0]) {
            case "T":
                task = new Todo(parts[2]);
                break;
            case "D":
                task = new Deadline(parts[2], parts[3]);
                break;
            case "E":
                task = new Event(parts[2], parts[3], parts[4]);
                break;
            default:
                throw new IllegalArgumentException("Unknown task type in the save file");
        }

        if (isDone) {
            task.markAsDone();
        }

        return task;
    }

    public abstract String toSaveFormat();
    @Override
    public String toString() {
        return getStatusIcon() + " " + description; // Return status icon and description
    }
}
