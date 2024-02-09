public class List {
    protected static Task[] tasks;

    public static int totalTasks = 0;

    public List() {
        this.tasks = new Task[100];
    }

    public static Task[] getTasksArray() {
        return tasks;
    }

    public void getAllTasks() {
        System.out.print("     ");
        System.out.println("Here are the tasks in your list:");
        int serialNumber = 1;
        for (int i = 0; i < totalTasks; i++) {
            System.out.print("     ");
            System.out.print(serialNumber
                    + ".[" + tasks[i].type + "]"
                    + "[" + tasks[i].getStatusIcon() + "] "
                    + tasks[i].getDescription());

            if (tasks[i].type.equals("D")) {
                //type casting required to access by member
                Deadline deadlineTask = (Deadline) tasks[i];
                System.out.print(" (by: " + deadlineTask.by + ")");
            } else if (tasks[i].type.equals("E")) {
                //type casting required to access from and to members
                Event eventTask = (Event) tasks[i];
                System.out.print(" (from: " + eventTask.from + " to: " + eventTask.to + ")");
            }
            System.out.println();
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
