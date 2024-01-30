public class Task {
    private String name;
    private boolean hasDone;
    private int taskNo;

    public Task(String name, int taskNo) {
        this.name = name;
        this.hasDone=false;
        this.taskNo = taskNo;
    }

    public void markedTask(){
        this.hasDone=true;
        System.out.println("    " + "--------------");
        System.out.println("    " + "Nice! I've marked this task as done:");
        System.out.println("    " + "[X] "+this.name);
        System.out.println("    " + "--------------");
    }
    public void unmarkedTask(){
        this.hasDone=false;
        System.out.println("    " + "--------------");
        System.out.println("    " + "OK, I've marked this task as not done yet:");
        System.out.println("    " + "[ ] "+this.name);
        System.out.println("    " + "--------------");
    }

    public void printTask(){
        System.out.print("    " +taskNo);
        if (hasDone){
            System.out.print(".[X] ");
        }else{
            System.out.print(".[ ] ");
        }
        System.out.println(name);
    }


}
