public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void markDone() {
        isDone = true;
    }

    public void markNotDone() {
        isDone = false;
    }
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public String getType() {
        return "";
    }

    public String toString() {
        return getType() + " " + getStatusIcon() + " " + description;
    }
    public String toFileString() {
        return getType() + " | " + (isDone() ? "1" : "0") + " | " + getFileDescription();
    }
    public static Task fromFileString(String fileLine) {
        String[] parts = fileLine.split(" \\| ");
        if (parts.length < 3) {
            // Handle invalid file line format
            return null;
        }

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (type) {
            case "T":
                return new Todo(description, isDone);
            case "D":
                if (parts.length < 4) {
                    // Handle invalid file line format for Deadline
                    return null;
                }
                String by = parts[3];
                return new Deadline(description, by, isDone);
            case "E":
                if (parts.length < 5) {
                    // Handle invalid file line format for Event
                    return null;
                }
                String from = parts[3];
                String to = parts[4];
                return new Event(description, from, to, isDone);
            default:
                // Handle unknown task types
                return null;
        }
    }
    public abstract String toSaveFormat();
    protected String getFileDescription() {
        return description;
    }
}