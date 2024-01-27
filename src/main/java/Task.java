public class Task {
    private boolean isMark;
    private String description;

    public Task(String task) {
        this.isMark = false;
        this.description = task;
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
        return description;
    }
}


