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

    public void addTaskAndDeadline (String input) {
        Deadline extractedInfo = Deadline.extractTaskAndDueDate(input);
        tasks[totalTasks] = new Deadline(extractedInfo.description, extractedInfo.by);
        totalTasks++;
        printTaskAndDeadline(extractedInfo);
    }

    public void printTaskAndDeadline(Deadline description) {
        Omoh.printHorizontalLine();
        System.out.print("     ");
        System.out.println("Got it. I've added this task:");
        System.out.print("       ");
        System.out.println("[D][ ]" + description.description + " (by: " + description.by + ")" );
        System.out.print("     ");
        System.out.println("Now you have " + totalTasks + " in the list.");
        Omoh.printHorizontalLine();
    }

    public void addTodo (String input) {
        //extracts task portion from input, after the "todo" keyword
        String description = input.substring("todo".length()).trim();
        tasks[totalTasks] = new Task(description);
        totalTasks++;
        printTodoMessage(tasks[totalTasks - 1]);
    }

    public void printTodoMessage (Task description) {
        Omoh.printHorizontalLine();
        System.out.print("     ");
        System.out.println("Got it. I've added this task:");
        System.out.print("       ");
        System.out.println("[T][ ]" + description.description);
        System.out.print("     ");
        System.out.println("Now you have " + totalTasks + " in the list.");
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
