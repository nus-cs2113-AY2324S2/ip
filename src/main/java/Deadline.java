public class Deadline extends Task {
    protected String by;

    public Deadline(String name, int taskNo, String by) {
        super(name, taskNo);
        this.by = by;
    }

    @Override
    public void markedTask() {
        this.hasDone=true;
        System.out.println("    " + "--------------");
        System.out.println("    " + "Nice! I've marked this task as done:");
        System.out.println("      [D]" + "[X] "+this.name);
        System.out.println("    " + "--------------");
    }

    @Override
    public void unmarkedTask() {
        this.hasDone=false;
        System.out.println("    " + "--------------");
        System.out.println("    " + "OK, I've marked this task as not done yet:");
        System.out.println("      [D]" + "[ ] "+this.name);
        System.out.println("    " + "--------------");
    }

    @Override
    public void printTask() {
        System.out.print("[D]");
        if (hasDone){
            System.out.print("[X] ");
        }else{
            System.out.print("[ ] ");
        }
        System.out.println(name + " (by: " + by +")");
    }
}
