package kvothe;

import java.util.ArrayList;

import kvothe.exception.WrongArgumentsException;
import kvothe.task.Task;

/**
 * Represents a list of tasks
 */
public class TaskList {
    private ArrayList<Task> tasks;


    public TaskList(int size){
        this.tasks = new ArrayList<Task>(size);
    }

    public TaskList(ArrayList<Task> tasks){
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list
     * @param task the task to add
     */
    public void add(Task task){
        this.tasks.add(task);
    }

    /**
     * Removes a task from the list
     * @param index the index of the task to remove. Index starts from 1
     * @return the task that was removed
     * @throws WrongArgumentsException if the index is invalid
     */
    public Task remove (String index) throws WrongArgumentsException{
        Task task = tasks.get(checkIndex(index) - 1);
        this.tasks.remove(checkIndex(index) - 1);
        return task;
    }

    /**
     * Removes a task from the list
     * @param index the index of the task to remove. Index starts from 0
     * @return the task that was removed
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    public Task remove(int index) throws IndexOutOfBoundsException{
        Task task = tasks.get(index);
        this.tasks.remove(index);
        return task;
    }

    /**
     * Returns the task at the specified index
     * @param index the index of the task to return. Index starts from 0
     * @return the task at the specified index
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    public Task get(int index) throws IndexOutOfBoundsException{
        return this.tasks.get(index);
    }

    /**
     * Returns the task at the specified index
     * @param index the index of the task to return. Index starts from 1
     * @return the task at the specified index
     * @throws WrongArgumentsException if the index is invalid
     */
    public Task get(String index) throws WrongArgumentsException{
       return this.tasks.get(checkIndex(index) - 1);
    }

    /**
     * Tries to parse the index to an integer and checks if it is within the range of the list.
     * Index starts from 1.
     * @param index the index to check
     * @return the index as an integer
     */
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

    /**
     * Sets the status of the task at the specified index
     * @param index the index of the task to set the status. Index starts from 0
     * @param status true if the task is done, false otherwise
     * @throws WrongArgumentsException if the index is invalid
     */
    public void setStatus(int index, boolean status) throws WrongArgumentsException{
        this.tasks.get(index).setIsDone(status);
    }

    /**
     * Sets the status of the task at the specified index
     * @param index the index of the task to set the status. Index starts from 1
     * @param status true if the task is done, false otherwise
     * @throws WrongArgumentsException if the index is invalid
     */
    public void setStatus(String index, boolean status) throws WrongArgumentsException{
        this.tasks.get(checkIndex(index) - 1).setIsDone(status);
    }

    /**
     * Returns the tasks in the list
     * @return the tasks in the list
     */
    public ArrayList<Task> getTasks(){
        return this.tasks;
    }

    /**
     * Returns the number of tasks in the list
     * @return the number of tasks in the list
     */
    public int size(){
        return this.tasks.size();
    }
}
