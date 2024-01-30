
public class List {
    private Task[] list;
    private int taskCount = 0;


    public List() {
        this.list = new Task[100];
    }

    /**
     * Obtains and prints out the task list as well as whether it is marked as done or not done.
     */
    public void getList() {
        Binks.createLineSpacing();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++){
            String taskStatus = list[i].isDone() ? "[X]" : "[ ]";
            System.out.println( (i + 1) + ". " + taskStatus + " " + list[i].getTaskDescription());
        }
        Binks.createLineSpacing();
    }

    /**
     * Adds a new item to the list.
     *
     *  @param task Task to be added to the list.
     */
    public void addItem(String task){
        Binks.createLineSpacing();
        Task newTask = new Task(task);
        list[taskCount] = newTask;
        taskCount++;
        System.out.println("added: " + task);
        Binks.createLineSpacing();
    }

    /**
     * Marks the task as done by adding an "X" in the square brackets and prints out the task that is done.
     *
     * @param index Index of the task that is marked as done
     */
    public void markAsDone(int index){
        Binks.createLineSpacing();
        if (index > 0 && index <= taskCount){
            list[index - 1].markTaskAsDone();
            System.out.println("Nice! I have marked this task as done:");
            System.out.println("[X] " + list[index - 1].getTaskDescription());
        }
        else {
            System.out.println("This task does not exist");
        }
        Binks.createLineSpacing();
    }

    /**
     * Marks the task as not done by removing the "X" in the square brackets and prints out the task that is being
     * marked as undone.
     *
     * @param index Index of the task that is being marked as undone
     */
    public void unmarkAsDone(int index){
        Binks.createLineSpacing();
        if (index > 0 && index <= taskCount){
            list[index - 1].unmarkTaskAsDone();
            System.out.println("OK, I have marked this task as not done yet:");
            System.out.println("[ ] " + list[index - 1].getTaskDescription());
        }
        else {
            System.out.println("This task does not exist");
        }
        Binks.createLineSpacing();
    }
}
