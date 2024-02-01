public class Task {
    private String name;
    private boolean isDone;

    private static int tasksCount = 0;
    private static Task[] tasks = new Task[100];

    Task(String name){
        this.name = name;
        isDone = false;
    }

    public static void listTasks(){
        System.out.println("-----------------------------------");
        for (int i = 0; i < tasksCount; i++){
            System.out.print((i + 1) + ".");
            System.out.print("[");
            if (tasks[i].isDone){
                System.out.print("X");
            } else {
                System.out.print(" ");
            }
            System.out.print("] ");
            System.out.println(tasks[i].name);
        }
        System.out.println("-----------------------------------");
    }
    public static void addTask(String taskName){
        System.out.println("-----------------------------------");
        System.out.println("added: " + taskName);
        tasks[tasksCount] = new Task(taskName);
        tasksCount++;
        System.out.println("-----------------------------------");
    }

    public static void response(String command){
        if (!command.equals("list")) {
            String[] commandWords = command.split(" ");
            if (commandWords[0].equals("mark")) {
                System.out.println("-----------------------------------");
                System.out.println("Nice! I've marked this task as done:");
                int index = Integer.parseInt(commandWords[1]);
                System.out.println("[X] " + tasks[index - 1].name);
                System.out.println("-----------------------------------");
                tasks[index - 1].isDone = true;
            } else if (commandWords[0].equals("unmark")){
                System.out.println("-----------------------------------");
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("-----------------------------------");
                int index = Integer.parseInt(commandWords[1]);
                System.out.println("[ ] " + tasks[index - 1].name);
                tasks[index - 1].isDone = false;
            } else {
                Task.addTask(command);
            }
        } else {
            Task.listTasks();
        }
    }
}
