import java.util.ArrayList;

public class UI {
    protected static final String NAME = "Krot"; // Static variable for the bot's name
    protected boolean hasEnded; // Static variable to end the chatbot

    public UI () {
        this.hasEnded = false;
    }

    public void printUser() {
        System.out.println("You:");
    }

    public void printName() {
        // Prints the name of bot when replying
        System.out.println(NAME + ":");
    }

    public void greeting() {
        // Prints out the standard greeting
        System.out.println("Hello! I'm " + NAME);
        System.out.println("What can I do for you?");
    }

    public void printSeparator() {
        // Prints line separators
        System.out.println("-");
    }

    public void printError(String message) {
        // Prints error message
        System.out.println(message);
    }

    public void endChat() {
        // Prints closing message
        System.out.println("Bye. Hope to see you again soon!");
        hasEnded = true;
    }

    public void listTasks(ArrayList<Task> tasks) {
        // Prints the list of tasks
        int i = 1;
        if (tasks.isEmpty()) {
            System.out.println("There's no tasks to do! :)");
        }
        for (Task task : tasks) {
            System.out.print(i + ".");
            printTask(task);
            i += 1;
        }
    }

    public void printTask(Task task) {
        // Prints specified task
        System.out.println("["
                + task.getTaskType() + "]"
                + "["
                + (task.isDone ? "X" : " ")
                + "] "
                + task.task
                + (task.getTaskType().equalsIgnoreCase("E") ? " (from: " + task.getStart() + " " : "")
                + (task.getTaskType().equalsIgnoreCase("E") ? "to: " + task.getEnd() + ")" :
                task.getTaskType().equalsIgnoreCase("D") ? " (by: " + task.getEnd() + ")" : ""));
    }

    public void printMarkMessage(Task task) {
        // Prints response message after marking
        if (task.isDone) {
            System.out.println("Wow good job at clearing a task! I,ve marked this task as done:");
        } else {
            System.out.println("More to do? I've marked this task as not done yet:");
        }
        printTask(task);
    }

    public void printCreateTaskMessage(Task task, int size) {
        // Prints response message after creating a task
        System.out.println("More tasks to do! I've added:");
        printTask(task);
        System.out.println("Get to working, you now have " + size + " tasks in the list!");
    }

    public void printDeleteMessage(Task task, int index, int size) {
        // Prints response message after deleting a task
        System.out.println("Deleted task " + index + " from the list.");
        printTask(task);
        System.out.println("You now have " + size + " tasks to complete.");
    }
}
