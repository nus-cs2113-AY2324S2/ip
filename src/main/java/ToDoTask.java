public class ToDoTask {
    private boolean isMark;
    private String task;

    public ToDoTask(String task) {
        this.isMark = false;
        this.task = task;
    }

    public void setMark() {
        isMark = true;
    }

    public void setUnmark(){
        isMark = false;
    }

    public boolean getMark() {
        return isMark;
    }

    public String getTask() {
        return task;
    }
}

