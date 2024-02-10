public abstract class Task {
    private boolean isMark;
    private final String DESCRIPTION;

    public Task(String task) {
        this.isMark = false;
        this.DESCRIPTION = task;
    }

    public void printTask() {
        String markSymbol = isMark ? "[x] " : "[ ] ";
        String taskSymbol = getTypeSymbol();
        System.out.print(taskSymbol + markSymbol + getTask());
    }

    public void setMark() {
        isMark = true;
    }

    public void setUnmark(){
        isMark = false;
    }

    public String getTask() {
        return DESCRIPTION;
    }

    public String getTypeSymbol() {
        return "[ ]";
    }
}