import java.util.ArrayList;

//Manages temporary list of tasks, i.e. adding, removing, editing
public class TaskList {
    private final ArrayList<Task> tasks;
    public TaskList(){
        tasks = new ArrayList<>();
    }
    public void addTask(Task task){
        tasks.add(task);
    }
    public ArrayList<Task> getTaskList(){
        return tasks;
    }
    public Task getTask(int index) throws IndexOutOfBoundsException { return tasks.get(index); }
    public void deleteTask(int index) throws IndexOutOfBoundsException { tasks.remove(index);}
    public void updateTaskDone(int index, boolean isDone) throws IndexOutOfBoundsException{
        tasks.get(index).setDone(isDone);
    }
    public int getSize(){ return tasks.size(); }
}
