public class Task {
    protected Boolean isDone;
    protected String name;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public Boolean getDone() {
        return isDone;
    }

    public void setDone(Boolean done) {
        isDone = done;
    }

    @Override
    public String toString() {
        if (isDone){
            return "[X] " + name;
        }
        else{
            return "[ ] " + name;
        }
    }
}
