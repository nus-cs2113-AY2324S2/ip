public class Task {
    protected String description;
    protected boolean isDone;
    private static final String separator = "\t_____________________________________________";
    private static void wrapEchoMessage(String message){
        System.out.println(separator);
        System.out.println(message);
        System.out.println(separator);
    }
    public Task(String description){
        this.description = description;
        this.isDone = false;
    }
    public String getStatusIcon(){
        return (isDone ? "X" : " ");
    }
    public void markTask(){
        this.isDone=true;
        wrapEchoMessage("\t Nice Job! I'll mark this as done: \n\t   [X] "+description);
    }
    public void unMarkTask(){
        this.isDone=false;
        wrapEchoMessage("\t Noted. Get it done soon.. \n\t   [ ] "+description);
    }
}
