package Tasks;

public class Task {
    private String content;
    private boolean isDone;

    public Task(String content, boolean isDone) {
        this.content = content;
        this.isDone = isDone;
    }


    public String getContent() {
        return content;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public String toString() {
        String checkmark = isDone ? "X": " ";
        return  " [" + checkmark + "] " + content;
    }
}
