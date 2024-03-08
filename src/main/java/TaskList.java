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

    public void add(Task task) {
        taskList.add(task);
        System.out.println("Now you have "+this.size()+" tasks in the list.");
    }

    public int size() {
        return taskList.size();
    }

    public Task get(int i) {
        return taskList.get(i);
    }


}
