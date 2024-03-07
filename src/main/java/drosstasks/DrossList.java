package drosstasks;
import drosstasks.Event;
import myexceptions.InvalidTodoException;
import java.util.ArrayList;

public class DrossList {
    private ArrayList<Task> tasks;

    public DrossList() {
        this.tasks = new ArrayList<Task>(); // Use ArrayList instead of array
    }

    //Method that returns the size of the current list
    public int getSize(){
        return tasks.size();
    }

    //Method to return task object based on index
    public Task getTask(int index){
        return tasks.get(index);
    }

    // Overloaded Method to add a deadline to the list
    public void addTask(String taskDescription, String taskDeadline){
        tasks.add(new Deadline(taskDescription, taskDeadline));
    }

    // Overloaded Method to add an event to the list
    public void addTask(String taskDescription, String taskStart, String taskEnd) {
        tasks.add(new Event(taskDescription,taskStart, taskEnd));
    }

    // Method to add a task to the list
    public void addTask(String taskDescription) throws InvalidTodoException {
        if (taskDescription.isEmpty()){
            throw new InvalidTodoException();
        }
        tasks.add(new ToDo(taskDescription));
    }

    // Method to delete a task from the list by index
    public void deleteTask(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > tasks.size()) {
            throw new IndexOutOfBoundsException("Task index out of range.");
        }
        tasks.remove(index-1);
    }

    // Method to print the last added task
    public void printLastTask() {
        if (!tasks.isEmpty()) {
            System.out.println("Last added task: " + tasks.get(tasks.size() - 1));
        } else {
            System.out.println("The list is empty.");
        }
    }

    //Method to toggle check task as done by index
    public void markDoneByIndex(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > tasks.size()) {
            throw new IndexOutOfBoundsException("Task index out of range.");
        }
        tasks.get(index-1).checkTask();
    }

    //Method to toggle check task as undone by index
    public void markUndoneByIndex(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > tasks.size()) {
            throw new IndexOutOfBoundsException("Task index out of range.");
        }
        tasks.get(index-1).uncheckTask();
    }

    // Method to print all tasks
    public void printAllTasks() {
        if (!tasks.isEmpty()) {
            System.out.println("All tasks:");
            for (int i = 0; i < tasks.size(); i++) {
                Task currentTask = tasks.get(i);
                System.out.print((i + 1) + ".");
                System.out.println(currentTask);
            }
        }
        else {
            System.out.println("The list is empty.");
        }
    }
}
