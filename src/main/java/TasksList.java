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
        System.out.println("Osu! Your task is as follows:");
        for(int i = 0; i < this.noOfTasks; i++) {
            System.out.print((i + 1) + ".");
            if (this.list.get(i).getStatus()){
                System.out.print("[X] ");
            } else {
                System.out.print("[ ] ");
            }
            System.out.println(this.list.get(i).getDescription());
        }
    }

    public void markAsDone(String s){
        Integer index = this.findIndexWithDesc(s);
        if (index == -1) {
            System.out.println("Gomen! Task is not in your list");
            return;
        } else {
            this.list.get(index).markAsDone();
        }
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("   [X] " + this.list.get(index).getDescription());
    }

    public void markAsDone(Integer index){
        index -= 1;
        if (index < 0 || index > (this.noOfTasks - 1)) {
            System.out.println("Gomen! You went out from the list");
            return;
        } else {
            this.list.get(index).markAsDone();
        }
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("   [X] " + this.list.get(index).getDescription());
    }

    public void markAsNotDone(String s){
        Integer index = this.findIndexWithDesc(s);
        if (index == -1) {
            System.out.println("Gomen! Task is not in your list");
            return;
        } else {
            this.list.get(index).markAsDone();
        }
        System.out.println("As you wished, your task is now undone:");
        System.out.println("   [ ] " + this.list.get(index).getDescription());
    }

    public void markAsNotDone(Integer index){
        index -= 1;
        if (index < 0 || index > (this.noOfTasks - 1)) {
            System.out.println("Gomen! You went out from the list");
            return;
        } else {
            this.list.get(index).markAsNotDone();
        }
        System.out.println("As you wished, your task is now undone:");
        System.out.println("   [ ] " + this.list.get(index).getDescription());
    }

    public Integer findIndexWithDesc(String s){
        for (int i = 0; i < this.noOfTasks; i++){
            if (this.list.get(i).getDescription().equals(s)){
                return i;
            }
        }
        return -1;
    }

    public Task getTaskWithIndex(int i){
        return this.list.get(i);
    }

    public Task getLatestTask(){
        return this.list.get(this.noOfTasks - 1); // -1 to get index
    }

}
