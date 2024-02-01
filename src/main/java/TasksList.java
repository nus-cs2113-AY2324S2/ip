import java.util.ArrayList;
import java.util.List;

public class TasksList {
    private Integer noOfTasks;
    private List<Task> list = new ArrayList<Task>();

    public TasksList(){
        this.noOfTasks = 0;
    }

    public void addTask(Task t) {
        this.noOfTasks++;
        this.list.add(t);
    }

    public void show(){
        for(int i = 0; i < this.noOfTasks; i++) {
            System.out.println(i + ". " + this.list.get(i).getDescription());
        }
    }

    public Task getTaskIndex(int i){
        return this.list.get(i);
    }

    public Task getLatestTask(){
        return this.list.get(this.noOfTasks - 1); // -1 to get index
    }

}
