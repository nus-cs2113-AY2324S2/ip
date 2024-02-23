package MassimoBoi;

<<<<<<< HEAD

=======
>>>>>>> branch-Level-6
public class Task {
    private final String description;
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
