public class List {
    private Task[] tasks;
    private int size;

    public List() {
        this.tasks = new Task[100]; // initial capacity
        this.size = 0;
    }

    // Method to add a task to the list
    public void addTask(String taskDescription) {
        tasks[size] = new Task(taskDescription);
        size++;
    }

    // Method to print the last added task
    public void printLastTask() {
        if (size > 0) {
            System.out.println("Last added task: " + tasks[size - 1]);
        } else {
            System.out.println("The list is empty.");
        }
    }

    //Method to toggle check task as done by index
    public void markDoneByIndex(int index){
        tasks[index].completeTask();
    }

    //Method to toggle check task as undone by index
    public void markUndoneByIndex(int index){
        tasks[index].uncheckTask();
    }

    // Method to print all tasks
    public void printAllTasks() {
        if (size > 0) {
            System.out.println("All tasks:");
            for (int i = 0; i < size; i++) {
                Task currentTask = tasks[i]; // Assuming tasks[i] is a Task object
                String statusMark = currentTask.isCompleted() ? "x" : " "; // Mark with 'x' if completed
                System.out.println((i+1) + ". " + "[" + statusMark + "] " + currentTask.getDescription());
            }
        } else {
            System.out.println("The list is empty.");
        }
    }
}
