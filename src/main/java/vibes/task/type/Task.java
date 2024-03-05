package vibes.task.type;

public abstract class Task {
    public static final char MARKED = 'X';
    public static final char UNMARKED = ' ';
    public static final String PRINT_FORMAT = "[%s] %s";
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }

    public char getStatusIcon() {
        if(isDone){
            return MARKED;
        }
        return UNMARKED;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public abstract String getTaskType();

    @Override
    public String toString(){
        return String.format(PRINT_FORMAT, this.getStatusIcon(), this.getDescription());
    }
}
