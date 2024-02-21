package huan.task;

public class TodoTask extends Task{
    public TodoTask(String name, Boolean isDone) {
        super(name, isDone);
        setTaskType(1);
    }

    @Override
    public void printTask() {
        System.out.println("[T][" + (getIsDone() ? "X" : " ") + "] " + getName());
    }

    @Override
    public String writeLine() {
        return "T" + (getIsDone() ? "T" : "F") + " " + getName();
    }
}
