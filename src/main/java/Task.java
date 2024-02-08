public class Task {
    private String name;
    private Boolean isDone;

    public void setName(String name) {
        this.name = name;
    }

    public void setIsDone(Boolean isDone) {
        this.isDone = isDone;
    }

    public String getName() {
        return name;
    }

    public Boolean getIsDone() {
        return isDone;
    }

    public void printTask() {
        System.out.println("[" + (isDone ? "X" : " ") + "] " + name);
    }
    public Task() {
        setName("task");
        setIsDone(false);
    }

    public Task(String name, Boolean isDone) {
        setName(name);
        setIsDone(isDone);
    }
}
