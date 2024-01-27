public class Task {
    protected String description;
    protected int listNumber;
    protected boolean isDone;


    public Task(String description, int listPosition) {
        this.description = description;
        this.isDone = false;
        this.listNumber = listPosition;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markTask(){
        this.isDone = true;
        String divider = "____________________________________________________________";
        System.out.println(divider);
        System.out.println("Nice! I've marked this task as done:");
        this.printTask();
        System.out.println(divider);

    }

    public void unmarkTask(){
        this.isDone = false;
        String divider = "____________________________________________________________";
        System.out.println(divider);
        System.out.println("Ok, I've marked this task as not done yet:");
        this.printTask();
        System.out.println(divider);

    }

    public void printTask(){
        System.out.print("[" + this.getStatusIcon() + "] " + this.description + "\n");
    }

}
