public class Task {
    private boolean isDone;
    private String description;

    public Task() {
        this(false,"");
    }

    public Task(boolean status, String s){
        this.isDone = status;
        this.description = s;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void markAsDone(){
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    // getters
    public boolean getStatus() {
        return this.isDone;
    }

    public String getDescription() {
        return this.description;
    }
}
