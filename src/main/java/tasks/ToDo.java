package tasks;

public class ToDo extends Task {
    public ToDo(String task) {
        super(task);
        this.haveToDo = false;
        setTaskType("T");
    }
    private boolean haveToDo;

    public boolean haveToDo() {
        return haveToDo;
    }
    public void setHaveToDo(boolean haveToDo) {
        this.haveToDo = haveToDo;
    }

    @Override
    public void print() {
        System.out.print('[' + getTaskType() + ']');
        System.out.print(isMarked() ? "[X] " : "[ ] ");
        System.out.println(getTaskDescription());
    }
}
