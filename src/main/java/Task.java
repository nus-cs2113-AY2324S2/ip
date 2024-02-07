public class Task {

    private final String name;
    private boolean hasDone;

    private char tde;

    public Task(String task, char tde) {
        this.name = task;
        this.hasDone = false;
        this.tde = tde;
    }

    public void printTask() {
        String tde =  getTDE() ;
        String tick = getTick();
        String name = getName();
        System.out.print(tde);
        System.out.print(tick);
        System.out.print(name);
    }
    public String getName() {
        return this.name;
    }
    public String getTick() {
        return this.hasDone ? "[X] " : "[ ] ";
    }
    public String getTDE() {
        return "[" + (char)this.tde + "]";
    }
    public void markDone() {
        this.hasDone = true;
    }

    public void unmarkDone() {
        this.hasDone = false;
    }

}
