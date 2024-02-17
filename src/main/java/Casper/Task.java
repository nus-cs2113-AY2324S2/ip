package Casper;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    private static final String SEPARATOR = "    _______________________________________________________________________";
    public static void wrapEchoMessage(String message){
        System.out.println(SEPARATOR);
        System.out.println(message);
        System.out.println(SEPARATOR);
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
    }
    public void unMarkTask(){
        this.isDone=false;
    }
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
    public abstract String saveFormat();
}
