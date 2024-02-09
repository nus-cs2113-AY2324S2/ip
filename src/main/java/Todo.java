public class Todo extends Task {
    public Todo(String description) {
        super(description);
        this.type = "T";
    }

    public static void addTodo (String input) {
        Task[] tasksArray = List.getTasksArray();
        //extracts task portion from input, after the "todo" keyword
        String description = input.substring("todo".length()).trim();
        tasksArray[List.totalTasks] = new Task(description);
        List.totalTasks++;
        printTodoMessage(tasksArray[List.totalTasks - 1]);
    }

    public static void printTodoMessage (Task description) {
        Omoh.printHorizontalLine();
        System.out.print("     ");
        System.out.println("Got it. I've added this task:");
        System.out.print("       ");
        System.out.println("[T][ ] " + description.description);
        System.out.print("     ");
        System.out.println("Now you have " + List.totalTasks + " tasks in the list.");
        Omoh.printHorizontalLine();
    }
}
