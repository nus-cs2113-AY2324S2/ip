package classes;

public class Task {
    private String name;
    private boolean isDone;


    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getType(){
        return "T";
    }

    public void markAsDone() {
        setDone(true);
    }

    public void markAsUndone() {
        setDone(false);
    }

    @Override
    public String toString(){
        return "[" + this.getStatusIcon() + "] " + this.getName();
    }
}
