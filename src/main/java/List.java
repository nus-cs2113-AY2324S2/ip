public class List {
    //array of 100 size
    private String[] tasks = new String[100];

    public static int totalTasks = 0;

    public List() {
        this.tasks = new String[100];
    }

    public void getAllTasks() {
        int serialNumber = 1;
        for (int i = 0; i < totalTasks; i++) {
            System.out.print("     ");
            System.out.println(serialNumber + ". " + tasks[i]);
            serialNumber += 1;
        }
        return;
    }

    public void setAndPrintTask(String task) {
        this.tasks[totalTasks] = task;
        System.out.println("added: " + task);
        totalTasks++;
    }

    public static int getTotalTasks() {
        return totalTasks;
    }

}
