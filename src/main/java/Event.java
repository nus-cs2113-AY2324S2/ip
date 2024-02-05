public class Event extends Task{
    protected String from;
    protected String to;

    public Event(String name, int taskNo, String from, String to) {
        super(name, taskNo);
        this.from = from;
        this.to = to;
    }

    @Override
    public void markedTask() {
        this.hasDone=true;
        System.out.println("    " + "--------------");
        System.out.println("    " + "Nice! I've marked this task as done:");
        System.out.println("      [E]" + "[X] "+this.name);
        System.out.println("    " + "--------------");
    }

    @Override
    public void unmarkedTask() {
        this.hasDone=false;
        System.out.println("    " + "--------------");
        System.out.println("    " + "OK, I've marked this task as not done yet:");
        System.out.println("      [E]" + "[ ] "+this.name);
        System.out.println("    " + "--------------");
    }

    @Override
    public void printTask() {
        System.out.print("[E]");
        if (hasDone){
            System.out.print("[X] ");
        }else{
            System.out.print("[ ] ");
        }
        System.out.println(name + " (from: " + from+" to: "+ to+ ")");
    }
}
