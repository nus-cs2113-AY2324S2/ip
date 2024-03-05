package MassimoBoi;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates task list to store user's tasks.
 */
public class TaskList {
    private List<Task> taskList;

    /**
     * Creates a new TaskList from an existing arrayList of tasks.
     *
     * @param taskList the arrayList containing tasks inputted by user.
     */
    TaskList(List<Task> taskList){
        this.taskList = taskList;
    }

    /**
     * Creates a new TaskList with no tasks inside.
     */
    TaskList(){
        this.taskList = new ArrayList<>();
    }

    /**
     * Returns the task at the specified index.
     */
    public Task get(int index){
        return taskList.get(index);
    }

    /**
     * Returns size of the taskList.
     */
    public int size(){
        return taskList.size();
    }

    /**
     * Adds task to the task list.
     * Prints details of the added task including task type, status, and description for user verification.
     *
     * @param task the task to be added.
     */
    public void addTask(Task task){
        taskList.add(task);
        printNewTaskMessage(task);
    }

    /**
     * Deletes the task from the task list.
     * Prints details of the deleted task including task type, status, and description for user verification.
     *
     * @param task the task to be deleted.
     */
    public void deleteTask(int task){
        printDeleteTaskMessage(task);
        taskList.remove(task);
    }

    /**
     * Prints out the task details in this specific order: type, status, description.
     *
     * @param task the task to be printed.
     */
    public void printTask(Task task) {
        System.out.printf("""
                %s%s %s
                """, task.taskType(), task.getStatus(), task.getDescription());
    }

    /**
     * Prints the newly added task's details and number of tasks in the updated list.
     *
     * @param newTask the task that has been newly added
     */
    public void printNewTaskMessage(Task newTask){
        System.out.println("Got it! Ya boi has added: ");
        printTask(newTask);
        System.out.printf("You now have %d %s in the list\n", taskList.size(), taskList.size() == 1 ? "task" : "tasks");
    }

    /**
     * Prints the deleted task's details and number of tasks in the updated list.
     *
     * @param taskIndex the index of the task to be deleted in the taskList
     */
    public void printDeleteTaskMessage(int taskIndex) {
        System.out.println("Got it! I have deleted: ");
        printTask(taskList.get(taskIndex));
        System.out.printf("You now have %d %s in the list\n", taskList.size()-1, taskList.size() == 1 ? "task" : "tasks");
    }

    /**
     * Prints the details of all tasks in the task list.
     * Prints details in this order: task index, task type, status, description.
     */
    public void printList() {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.print(i + 1 + ". ");
            printTask(taskList.get(i));
        }
    }
}
