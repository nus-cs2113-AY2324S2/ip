import java.util.ArrayList;

/**
 * Defines the functionality of the user created list.
 */
public class TaskList {
    protected ArrayList<Task> tasks;
    protected Ui userInterface;

    /**
     * Creates an instance of an ArrayList within the instance of the Tasklist class.
     *
     * @param uiComponent Instance of Ui class.
     */
    public void generateList(Ui uiComponent){
        tasks = new ArrayList<>();
        userInterface = uiComponent;
    }


    /**
     * Adds a task to the end of the list.
     * Prints a message indicating the insertion is completed.
     *
     * @param task the task to be added.
     */
    public void insertTask(Task task){
        tasks.add(task);
        userInterface.listInsertionMessage(task.getDescription(), tasks.size());
    }


    public ArrayList<Task> getTasks(){
        return tasks;
    }


    public void setTasks(ArrayList<Task> aList){
        this.tasks = aList;
    }


    /**
     * Prints out all tasks in the list.
     *
     * @param tasks list of tasks to be listed.
     */
    public void listTasks(ArrayList<Task> tasks){
        userInterface.printTasks(tasks);
    }


    /**
     * Marks a task of a specific index with an X.
     *
     * @param taskNumber index, with respect to the listed numbers, of a task.
     */
    public void markIndex(int taskNumber){
        userInterface.taskMarkedMessage();
        tasks.get(taskNumber - 1).markTask();
    }


    /**
     * Removes the X of a task of a specific index.
     *
     * @param taskNumber index, with respect to the listed numbers, of a task.
     */
    public void unmarkIndex(int taskNumber){
        userInterface.taskUnmarkedMessage();
        tasks.get(taskNumber - 1).unmarkTask();
    }

    /**
     * Removes a task from the list.
     *
     * @param taskNumber index, with respect to the listed numbers, of a task.
     */
    public void deleteIndex(int taskNumber){
        userInterface.taskRemovedMessage(tasks.get(taskNumber - 1).getDescription(), tasks.size());
        tasks.remove(taskNumber - 1);
    }


    /**
     * Prints all tasks in the list that contain a specific keyword.
     *
     * @param keyword The keyword.
     */
    public void findKeyword(String keyword){
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task item : tasks){
            String[] words = item.getDescription().split("\\s+"); //splits at either 1 or 2 spaces
            for (String word : words){
                if (word.equals(keyword)){
                    foundTasks.add(item);
                    break;
                }
            }
        }
        userInterface.keywordMessage(foundTasks);
    }
}
