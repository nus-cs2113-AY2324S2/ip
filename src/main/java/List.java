public class List {
    private String[] tasks;
    private int size;

    public List() {
        this.tasks = new String[10]; // initial capacity
        this.size = 0;
    }

    // Dynamically resize the array if needed
    private void ensureCapacity() {
        if (size == tasks.length) {
            String[] newTasks = new String[size * 2];
            for (int i = 0; i < size; i++) {
                newTasks[i] = tasks[i];
            }
            tasks = newTasks;
        }
    }

    // Method to add a task to the list
    public void addTask(String task) {
        ensureCapacity();
        tasks[size++] = task;
    }

    // Method to print the last added task
    public void printLastTask() {
        if (size > 0) {
            System.out.println("Last added task: " + tasks[size - 1]);
        } else {
            System.out.println("The list is empty.");
        }
    }

    // Method to print all tasks
    public void printAllTasks() {
        if (size > 0) {
            System.out.println("All tasks:");
            for (int i = 0; i < size; i++) {
                System.out.println(tasks[i]);
            }
        } else {
            System.out.println("The list is empty.");
        }
    }
}
