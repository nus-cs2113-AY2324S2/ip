package kvothe;

import java.util.ArrayList;

import kvothe.exception.WrongArgumentsException;
import kvothe.task.Task;

public class TaskList {
    private ArrayList<Task> tasks;


    public TaskList(int size){
        this.tasks = new ArrayList<Task>(size);
    }

    public TaskList(ArrayList<Task> tasks){
        this.tasks = tasks;
    }


    public void add(Task task){
        this.tasks.add(task);
    }

    public Task remove (String index) throws WrongArgumentsException{
        Task task = tasks.get(checkIndex(index) - 1);
        this.tasks.remove(checkIndex(index) - 1);
        return task;
    }

    public void remove(int index) throws IndexOutOfBoundsException{

        this.tasks.remove(index);
    }

    public Task get(int index) throws IndexOutOfBoundsException{
        return this.tasks.get(index);
    }

    public Task get(String index) throws WrongArgumentsException{
       return this.tasks.get(checkIndex(index) - 1);
    }

    private int checkIndex(String index) throws WrongArgumentsException{
        int i;
        try{
            i = Integer.parseInt(index);
        } catch (NumberFormatException e){
            throw new WrongArgumentsException("Please enter a valid number");
        }

        if (i > this.tasks.size() || i < 1){
            throw new WrongArgumentsException("Sorry. There are only " + tasks.size() + " tasks in the list");
        }

        return i;
    }

    public void setStatus(int index, boolean status) throws WrongArgumentsException{
        this.tasks.get(index).setIsDone(status);
    }

    public void setStatus(String index, boolean status) throws WrongArgumentsException{
        this.tasks.get(checkIndex(index) - 1).setIsDone(status);
    }

    public ArrayList<Task> getTasks(){
        return this.tasks;
    }

    public int size(){
        return this.tasks.size();
    }
}
