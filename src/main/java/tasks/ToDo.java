package tasks;

/** The most basic type of task with just a simple description. */
public class ToDo extends Task {
    public ToDo(String task) {
        super(task);
        this.haveToDo = false;
        setTaskType("T");
    }
    private boolean haveToDo;

    public void setHaveToDo(boolean haveToDo) {
        this.haveToDo = haveToDo;
    }

    /** Prints information about type and if it's marked, as indicated by X,
     *  when task is presented by UI.
     */
    @Override
    public void print() {
        System.out.print('[' + getTaskType() + ']');
        System.out.print(isMarked() ? "[X] " : "[ ] ");
        System.out.println(getTaskDescription());
    }
}
