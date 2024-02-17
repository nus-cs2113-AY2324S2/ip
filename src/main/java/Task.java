public class Task {
    private String task;
    private boolean isMarked;
    public String getTask() {
        return task;
    }
    public void setTask(String task) {
        this.task = task;
    }
    public boolean isMarked() {
        return isMarked;
    }
    public void setMarked(boolean marked) {
        isMarked = marked;
    }
    public Task(String task) {
        this.task = task;
        this.isMarked = false;
    }
    public void print() {
        System.out.print("[" + (isMarked() ? "X" : " ") + "] ");
        System.out.println(getTask());
    }
}