import java.util.ArrayList;

/**
 * Class that manages temporary list of tasks, i.e. adding, removing, editing
 */
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

    /**
     * Updates task to either marked or unmarked
     * @param index Index of task to be updated
     * @param isDone Whether the task should be marked or unmarked
     * @throws IndexOutOfBoundsException Thrown when the index is not found in the list of tasks
     */
    public void updateTaskDone(int index, boolean isDone) throws IndexOutOfBoundsException{
        tasks.get(index).setDone(isDone);
    }
    public int getSize(){ return tasks.size(); }
}
