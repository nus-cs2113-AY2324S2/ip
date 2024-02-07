public class Task {
    //Attributes

    protected String name;
    protected boolean isDone;
    protected static int tasksCount = 0;
    private static Task[] tasks = new Task[100];

    //Constructors
    Task(String name){
        this.name = name;
        isDone = false;
    }

    //Methods
    public String toString(){
        return (isDone? "[X] " : "[ ] ") + name;
    }

    public static void listTasks(){
        System.out.println(Duke.LINE_SEPARATOR);
        for (int i = 0; i < tasksCount; i++){
            System.out.print((i + 1) + ".");
            System.out.println(tasks[i]);
        }
        System.out.println(Duke.LINE_SEPARATOR);
    }

    public static void add(Task task){
        System.out.println(Duke.LINE_SEPARATOR);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        tasks[tasksCount] = task;
        tasksCount++;
        System.out.println(Duke.LINE_SEPARATOR);
    }

    public static void mark(int index){
        tasks[index - 1].isDone = true;
        System.out.println(Duke.LINE_SEPARATOR);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks[index - 1]);
        System.out.println(Duke.LINE_SEPARATOR);
    }

    public static void unmark(int index){
        tasks[index - 1].isDone = false;
        System.out.println(Duke.LINE_SEPARATOR);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks[index - 1]);
        System.out.println(Duke.LINE_SEPARATOR);
    }

    private static boolean isValidObject(String command){
        return command.equals("deadline") || command.equals("event")
                || command.equals("todo");
    }

    public static void responseToCommand(String command){
        String[] commandWords = command.split(" ");
        if (commandWords[0].equals("list")) {
            Task.listTasks();
        } else if (commandWords[0].equals("mark")) {
            mark(Integer.parseInt(commandWords[1]));
        } else if (commandWords[0].equals("unmark")){
            unmark(Integer.parseInt(commandWords[1]));
        } else {
            Task newTask;
            if (isValidObject(commandWords[0])){
                newTask = Parser.parseCommand(command);
                add(newTask);
            } else {
                System.out.println("Invalid!");
            }
        }
    }
}
