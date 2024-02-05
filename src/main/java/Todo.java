public class Todo extends Task{
    public Todo(String name, int taskNo) {
        super(name, taskNo);
    }

    @Override
    public void markedTask() {
        this.hasDone=true;
        System.out.println("    " + "--------------");
        System.out.println("    " + "Nice! I've marked this task as done:");
        System.out.println("      [T]" + "[X] "+this.name);
        System.out.println("    " + "--------------");
    }

    @Override
    public void unmarkedTask() {
        this.hasDone=false;
        System.out.println("    " + "--------------");
        System.out.println("    " + "OK, I've marked this task as not done yet:");
        System.out.println("      [T]" + "[ ] "+this.name);
        System.out.println("    " + "--------------");
    }

    @Override
    public void printTask() {
        System.out.print("[T]");
        if (hasDone){
            System.out.print("[X] ");
        }else{
            System.out.print("[ ] ");
        }
        System.out.println(name);
    }
}
