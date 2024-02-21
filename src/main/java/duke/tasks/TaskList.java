package duke.tasks;


import duke.DukeException;

import java.io.File;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

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
     * @param taskNumber The task number
     */
    public void removeTask(int taskNumber) throws DukeException{
        if(taskNumber > tasks.size()){
            throw new DukeException("Can't delete task as task is not found!");
        }
        System.out.println("     Fine! I've removed this task:");
        int indexOfTask = taskNumber - 1;
        System.out.println("      " + tasks.get(indexOfTask));
        tasks.remove(indexOfTask);
        System.out.println("     Now you have " + tasks.size() + " tasks in the list");

    }

    /**
     * Checks the task in the task list
     * @param taskNumber The task number
     */
    public void checkTask(int taskNumber) throws DukeException{
        if(taskNumber > tasks.size()){
            throw new DukeException("Task not found!");
        }
        System.out.println("     Nice! I've marked this task as done:");
        tasks.get(taskNumber-1).setTaskStatus(true);
        System.out.println("      " + tasks.get(taskNumber-1));
    }

    /**
     * Unchecks the task in the task list
     * @param taskNumber The task number
     */
    public void uncheckTask(int taskNumber) throws DukeException{
        if(taskNumber > tasks.size()){
            throw new DukeException("Task not found!");
        }
        System.out.println("     OK, I've marked this task as not done yet:");
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

    /**
     * Saves the current task list into duke.txt
     * @throws IOException
     */
    public void saveTaskList() throws IOException {
        FileWriter fw = new FileWriter("duke.txt");

        for (Task task : tasks) {
            if (task instanceof ToDos) {
                fw.write("T/ ");
            } else if (task instanceof Events) {
                fw.write("E/ ");
            } else if (task instanceof Deadlines) {
                fw.write("D/ ");
            }
            fw.write((task.getTaskStatus() ? "1 /" : "0 /") + task.getTask() + System.lineSeparator());
        }
        fw.close();
    }


}
