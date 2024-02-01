public class List {
    protected static Task[] tasks;

    public static int totalTasks = 0;

    public List() {
        this.tasks = new Task[100];
    }

    public void getAllTasks() {
        System.out.print("     ");
        System.out.println("Here are the tasks in your list:");
        int serialNumber = 1;
        for (int i = 0; i < totalTasks; i++) {
            System.out.print("     ");
            System.out.println(serialNumber + ".[" + tasks[i].getStatusIcon() + "] " + tasks[i].getDescription());
            serialNumber += 1;
        }
        return;
    }

    public void addTask(String taskDescription) {
        tasks[totalTasks] = new Task(taskDescription);
        totalTasks++;
    }

    public void printAddedTask() {
        Omoh.printHorizontalLine();
        System.out.print("     ");
        System.out.println("added: " + tasks[totalTasks - 1].getDescription());
        Omoh.printHorizontalLine();
    }

    public static void markAsDone(int index) {
        tasks[index - 1].isDone = true;
    }

    public static void markAsNotDone(int index) {
        tasks[index - 1].isDone = false;
    }

    //method that prints out the task that has been marked done or unmarked
    public static void printMarkTask(int index, String input) {
        Omoh.printHorizontalLine();
        System.out.print("    ");
        if (input.startsWith("mark")) {
            System.out.println("Nice! I've marked this task as done:");
        } else {
            System.out.println("OK, I've marked his task as not done yet:");
        }
        System.out.print("      ");
        System.out.println("[" + tasks[index - 1].getStatusIcon() + "] " + tasks[index - 1].description);
        Omoh.printHorizontalLine();
    }

}
