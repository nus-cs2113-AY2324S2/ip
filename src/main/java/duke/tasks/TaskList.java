package duke.tasks;

import java.util.ArrayList;

public class TaskList {
    /** Array of tasks */
    private ArrayList<Task> tasks;

    /** Number of tasks */
    private int noOfTasks = 0;
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
     * @param taskNumber The task number
     */
    public void removeTask(int taskNumber){
        int indexOfTask = taskNumber - 1;
        System.out.println("      " + tasks.get(indexOfTask));
        tasks.remove(indexOfTask);
        System.out.println("     Now you have " + tasks.size() + " tasks in the list");

    }

    /**
     * Checks the task in the task list
     * @param taskNumber The task number
     */
    public void checkTask(int taskNumber){
        tasks.get(taskNumber-1).setTaskStatus(true);
        System.out.println("      " + tasks.get(taskNumber-1));
    }

    /**
     * Unchecks the task in the task list
     * @param taskNumber The task number
     */
    public void uncheckTask(int taskNumber){
        tasks.get(taskNumber-1).setTaskStatus(false);
        System.out.println("      " + tasks.get(taskNumber-1));
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
     * returns the number of tasks in the list
     * @return the number of tasks
     */
    public int getNoOfTasks(){
        return tasks.size();
    }


}
