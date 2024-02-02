public class Task {
    protected String description;
    protected boolean isDone;
    private static final String separator = "    _____________________________________________";
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
        wrapEchoMessage("     Nice Job! I'll mark this as done: \n       [X] "+description);
    }
    public void unMarkTask(){
        this.isDone=false;
        wrapEchoMessage("     Noted. Get it done soon.. \n       [ ] "+description);
    }
}
