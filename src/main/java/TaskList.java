import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> taskList;
    public TaskList()
    {
        taskList=new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> taskList)
    {
        this.taskList = taskList;
    }

    /**
     * add a task to taskList
     * @param task: task need to be added
     */
    public void add(Task task) {
        taskList.add(task);
        System.out.println("Now you have "+this.size()+" tasks in the list.");
    }

    /**
     *
     * @return the size of the taskList
     */

    public int size() {
        return taskList.size();
    }

    /**
     * get tast from taskList at index i
     * @param i : index i, position in the taskList
     * @return task at position i in the taskList
     */
    public Task get(int i) {
        return taskList.get(i);
    }


}
