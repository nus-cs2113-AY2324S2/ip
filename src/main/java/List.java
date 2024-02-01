public class List {
    //array of 100 size
    protected Task[] tasks;

    public static int totalTasks = 0;

    public List() {
        this.tasks = new Task[100];
    }

    public void getAllTasks() {
        int serialNumber = 1;
        for (int i = 0; i < totalTasks; i++) {
            System.out.print("     ");
            System.out.println(serialNumber + ". " + tasks[i].getDescription());
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


}
