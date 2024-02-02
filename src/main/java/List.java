public class List {
    
    private Task[] tasks;
    private int size;

    public List() {
        tasks = new Task[100];
        size = 0;
    }

    public void addTask(String name) {
        tasks[size] = new Task(name);
        size++;
        printHorizontalLine();
        System.out.println("    added: " + name);
        printHorizontalLine();
    }

    public void changeTaskStatus(int index, boolean isDone) {
        tasks[index].changeStatus(isDone);
        printHorizontalLine();
        if (isDone) {
            System.out.println("    Nice! I've marked this task as done:");
        } else {
            System.out.println("    OK, I've marked this task as not done yet:");
        }
        printTask(index);
        printHorizontalLine();
    }

    public void printList() {
        printHorizontalLine();
        System.out.println("    Here are the current tasks in your list:");
        for (int i = 0; i < size; i++) {
            printTask(i);
        }
        if (size == 0) {
            System.out.println("    There are no tasks in your list!");
        }
        printHorizontalLine();
    }

    private void printTask(int index) {
        String taskName = tasks[index].getName();
        String taskStatus = tasks[index].getStatusIcon();
        System.out.println("    " + (index + 1) + ".[" + taskStatus + "] " + taskName);
    }

    private void printHorizontalLine() {
        System.out.println("    ________________________________________________");
    }

    public int getSize() {
        return size;
    }
}
