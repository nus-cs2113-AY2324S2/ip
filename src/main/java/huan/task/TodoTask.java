package huan.task;

public class TodoTask extends Task{
    public TodoTask(String name, Boolean isDone) {
        super(name, isDone);
        setTaskType(1);
    }
    public void printTask() {
        System.out.println("[T][" + (getIsDone() ? "X" : " ") + "] " + getName());
    }
}
