public class List {
    
    private Task[] tasks;
    private int size;

    public List() {
        tasks = new Task[100];
        size = 0;
    }

    public void addTodo(String name) {
        tasks[size] = new Todo(name);
        size++;
        printAfterAddingTask(tasks[size - 1]);
    }

    public void addDeadline(String name, String by) {
        tasks[size] = new Deadline(name, by);
        size++;
        printAfterAddingTask(tasks[size - 1]);
    }

    public void addEvent(String name, String from, String to) {
        tasks[size] = new Event(name, from, to);
        size++;
        printAfterAddingTask(tasks[size - 1]);
    }

    public void changeTaskStatus(int index, boolean isDone) {
        tasks[index].changeStatus(isDone);
        printHorizontalLine();
        if (isDone) {
            System.out.println("    Nice! I've marked this task as done:");
        } else {
            System.out.println("    OK, I've marked this task as not done yet:");
        }
        System.out.println("      " + tasks[index]);
        printHorizontalLine();
    }

    public void printAllTasks() {
        printHorizontalLine();
        System.out.println("    Here are the current tasks in your list:");
        for (int i = 0; i < size; i++) {
            System.out.println("    " + (i + 1) + "." + tasks[i]);
        }
        if (size == 0) {
            System.out.println("    There are no tasks in your list!");
        }
        printHorizontalLine();
    }

    public void printAfterAddingTask(Task task) {
        printHorizontalLine();
        printTaskAddedMessage(task);
        printListSummary();
        printHorizontalLine();
    }

    public void printTaskAddedMessage(Task task) {
        System.out.println("    Got it. I've added this task: ");
        System.out.println("      " + task);
    }

    public void printListSummary() {
        if (size == 1) {
            System.out.println("    Now you have " + size + " task in the list.");
        } else {
            System.out.println("    Now you have " + size + " tasks in the list.");
        }
    }

    private void printHorizontalLine() {
        System.out.println("    ________________________________________________");
    }

    public int getSize() {
        return size;
    }
}
