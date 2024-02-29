package Casper;

import java.util.ArrayList;

/**
 * Contains the task list itself, along with functionalities relating to the list.
 */
public class TaskList {
    private final ArrayList<Task> taskList;
    private int noOfTasks;

    public TaskList(){
        this.taskList = new ArrayList<Task>();
        this.noOfTasks = 0;
    }

    public TaskList(ArrayList<Task> taskList){
        this.taskList = taskList;
        this.noOfTasks = taskList.size();
    }

    /**
     * Returns the TaskList in <code>ArrayList</code> form.
     *
     * @return <code>ArrayList</code> representing the TaskList instance.
     */
    public ArrayList<Task> getTaskList(){
        return this.taskList;
    }

    /**
     * Change a task's status to marked.
     *
     * @param index Target index of the task.
     */
    public void markTask(int index){
        taskList.get(index-1).markTask();
    }


    /**
     * Change a task's status to unmarked.
     *
     * @param index Target index of the task.
     */
    public void unMarkTask(int index){
        taskList.get(index-1).unMarkTask();
    }

    /**
     * Gets the total number of tasks in the <code>TaskList</code>.
     *
     * @return An integer representing the total number of tasks in the list.
     */
    public int getTaskNumber(){
        return noOfTasks;
    }

    /**
     * Gets a specific task from the <code>TaskList</code>given the index.
     *
     * @param index The index of the wanted task.
     * @return A <code>Task</code> corresponding to the given index.
     */
    public Task getTask(int index){
        return taskList.get(index);
    }

    /**
     * Adds a <code>Task</code> to the <code>TaskList</code> instance.
     *
     * @param newTask A new <code>Task</code> to be inserted into the list.
     */
    public void addTask(Task newTask){
        taskList.add(newTask);
        noOfTasks++;
    }

    /**
     * Removes a task given the task's index.
     *
     * @param index Index of the task to be removed.
     */
    public void removeTask(int index){
        taskList.remove(index);
        noOfTasks--;
    }

    private static boolean checkNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Prints the full task list of the current <code>TaskList</code> instance.
     */
    public void echoTaskList(){
        if (noOfTasks==0) {
            System.out.println("     You have no tasks this time around.");
        } else {
            System.out.println("     Here are the tasks in your list:");
            for (int i=1; i<=noOfTasks; i++) {
                System.out.println("     "+i+". "+taskList.get(i-1));
            }
        }
    }

    /**
     * Prints the full task list of the current <code>TaskList</code> instance.
     * The only difference with the <code>echoTaskList</code> is the printed string into the command line,
     * and the context in which the method is invoked.
     */
    public void echoFoundTasks(){
        if (noOfTasks==0) {
            System.out.println("     That does not sound like a task you have.");
        } else {
            System.out.println("     Here are the matching tasks in your list:");
            for (int i=1; i<=noOfTasks; i++) {
                System.out.println("     "+i+". "+taskList.get(i-1));
            }
        }
    }

    /**
     * Validates whether the user's input that targets a certain task is within bounds of the <code>TaskList</code>.
     *
     * @param userInput The user's raw <code>String</code> input.
     * @throws CasperInvalidInputException If the target index is out of bounds or <code>userInput</code>
     * does not obey the defined syntax.
     */
    public int validateTargetedInput(String userInput) throws CasperInvalidInputException{
        String[] userInputSplit = userInput.split(" ");
        if (userInputSplit.length==2 && checkNumeric(userInputSplit[1])) {
            int targetTaskNumber = Integer.parseInt(userInputSplit[1]);
            boolean withinBounds = targetTaskNumber<=noOfTasks && targetTaskNumber>0;

            if (withinBounds) {
                return targetTaskNumber;
            } else {
                throw new CasperInvalidInputException("That task does not exist!");
            }
        } else {
            throw new CasperInvalidInputException("Invalid input!");
        }
    }


    /**
     * Handles marking and un-marking of a task given the user's input.
     *
     * @param userInput The user's raw <code>String</code> input.
     * @throws CasperInvalidInputException If the target index is out of bounds or <code>userInput</code>
     * does not obey the defined syntax.
     */
    public void handleMarkTask(String userInput) throws CasperInvalidInputException{
        boolean toMark = userInput.split(" ")[0].equals("mark");
        int targetTaskNumber = validateTargetedInput(userInput);
        if(toMark){
            markTask(targetTaskNumber);
        }else{
            unMarkTask(targetTaskNumber);
        }
    }

}
