package interactions;
import interactions.Task;

public class ToDo extends Task {
    private boolean haveToDo;
    public boolean HaveToDo() {
        return haveToDo;
    }

    public void setHaveToDo(boolean haveToDo) {
        this.haveToDo = haveToDo;
    }

    public ToDo(String task) {
        super(task);
        this.haveToDo = false;
    }
}
