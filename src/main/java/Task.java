import javax.swing.table.TableRowSorter;

public class Task {
    protected String description;
    protected boolean isDone;
    protected boolean isTodo;
    protected boolean isEvent;
    protected boolean isDDL;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.isTodo = false;
        this.isEvent = false;
        this.isDDL = false;
    }

    public void markAsDone() {
        isDone = true;
    };

    public void markAsToDo() {
        isTodo = true;
    };
    public void markAsDDL() {
        isDDL = true;
    };
    public void markAsEvent() {
        isEvent = true;
    };


    public void unmarkAsDone() {
        isDone = false;
    }



    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    public String getTypeIcon() { return isDDL? "D" :( isTodo ? "T" :( isEvent ? "E" : " "));};
    public String toString() {
        return description;
    }
}