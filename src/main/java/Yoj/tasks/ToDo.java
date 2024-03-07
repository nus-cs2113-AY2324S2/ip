package Yoj.tasks;
public class ToDo extends Task {
    public ToDo (String description){
        super(description);
    }

    public String taskType() {
        return "[T]";
    }

    // Prints task
    @Override
    public String toString(){
        return taskType() + super.toString();
    }
}