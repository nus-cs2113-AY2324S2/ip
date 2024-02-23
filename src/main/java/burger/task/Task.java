package burger.task;

public class Task {

    protected String name;
    protected boolean hasDone;

    protected char tde;

    public Task(String task, char tde) {
        this.name = task;
        this.hasDone = false;
        this.tde = tde;
    }
    /**
     * Prints task in the required format [][] {Task Name}.
     */
    public void printTask() {
        char tde =  getTDE() ;
        char tick = getTick();
        String name = getName();
        System.out.print("[" + tde + "]");
        System.out.print("[" + tick + "] ");
        System.out.print(name);
    }

    public String getName() {
        return this.name;
    }

    public char getTick() {
        return this.hasDone ? 'X' : ' ';
    }
    public char getTDE() {
        return this.tde;
    }
    public void markDone() {
        this.hasDone = true;
    }

    public void unmarkDone() {
        this.hasDone = false;
    }

}
