package kvothe;

import java.util.ArrayList;
import kvothe.task.Task;

public class TaskList {
    private ArrayList<Task> tasks;


    public TaskList(int size){
        this.tasks = new ArrayList<Task>(size);
    }


    public void add(Task task){
        this.tasks.add(task);
    }

    public void remove(int index){
        this.tasks.remove(index);
    }
}
