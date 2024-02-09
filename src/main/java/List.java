public class List {
    protected static Task[] tasks;

    public static int totalTasks = 0;

    public List() {
        this.tasks = new Task[100];
    }

    public static Task[] getTasksArray() {
        return tasks;
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
        System.out.println("[T][ ] " + description.description);
        System.out.print("     ");
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
        Omoh.printHorizontalLine();
    }

}
