public class Task {
    private String description;
    private boolean isDone;

    public Task(String task){
        description = task;
        isDone = false;
    }

    public String getStatus(){
        return (isDone ? "[X]" : "[ ]");
    }

    public String getDescription(){
        return (this.description);
    }

    public void markAsDone(){
        isDone = true;
    }

    public void unmark(){
        isDone = false;
    }

    public String taskType(){
        return "[U]";
    }




}
