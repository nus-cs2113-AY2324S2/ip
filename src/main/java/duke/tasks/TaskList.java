package duke.tasks;

import duke.DukeException;
import duke.Ui;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class TaskList {
    /** Array of tasks */
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds task to the task list
     * @param task The task
     */
    public void addTask(Task task){
        tasks.add(task);
    }

    /**
     * Removes task from the task list
     *
     * @param taskNumber The task number
     */
    public void removeTask(int taskNumber, Ui ui) throws DukeException{
        if(taskNumber > tasks.size()){
            throw new DukeException("Can't delete task as task is not found!");
        }
        int indexOfTask = taskNumber - 1;
        Task removedTask = tasks.get(indexOfTask);
        tasks.remove(indexOfTask);
        ui.printRemoveTask(tasks, removedTask);
    }

    /**
     * Checks the task in the task list
     * @param taskNumber The task number
     */
    public void checkTask(int taskNumber) throws DukeException{
        if(taskNumber > tasks.size()){
            throw new DukeException("Task not found!");
        }
        tasks.get(taskNumber-1).setTaskStatus(true);

    }

    /**
     * Unchecks the task in the task list
     * @param taskNumber The task number
     */
    public void uncheckTask(int taskNumber) throws DukeException{
        if(taskNumber > tasks.size()){
            throw new DukeException("Task not found!");
        }
        tasks.get(taskNumber-1).setTaskStatus(false);
    }

    /**
     * Prints out all the task
     */
    public void listTasks(){
        int taskCount = 0;
        for (Task task: tasks) {
            if (task == null) {
                break;
            }
            taskCount++;
            System.out.println("     " + taskCount +"." + task);

        }
    }

    /**
     * returns The number of tasks in the list
     * @return The number of tasks
     */
    public int getNoOfTasks(){
        return tasks.size();
    }

    /**
     * Returns the task list
     * @return The entire task list
     */
    public ArrayList<Task> getTaskList() {
        return tasks;
    }

    /**
     * Lists all the tasks with description or keywords that are relevant
     * @param description The task description the user wants to find
     */
    public void listReleventTasks(String description){
        System.out.println("     Here are the relevant tasks in your list:");
        int taskCount = 0;
        for (Task task: tasks) {
            if (task == null) {
                break;
            } else if (task.getTask().contains(description)){
                taskCount++;
                System.out.println("     " + taskCount +"." + task);
            }


        }

    }
}
