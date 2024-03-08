import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Scanner in;
    public Ui() {
        in = new Scanner(System.in);
    }
    public String getUserInput() {
        return in.nextLine();
    }
    public void printMessage(String message) {
        System.out.println(message);
    }
    public void showLoadingError() {
        System.err.println("Error loading tasks from file.");
    }
    public void showWelcome() {
        showLine();
        System.out.println("Hello! I'm Brennan!");
        System.out.println("What can I do for you?\n");
    }

    public static void deleteTaskDisplay(int indexTask, Task removedTask) {
        System.out.println("Command received. I've removed this task:");
        System.out.println(indexTask + ". " + "[" + removedTask.getStatusIcon() + "]" + removedTask.description);
        System.out.println("Currently you have " + TaskList.tasks.size() + " tasks in the list below.");
    }

    public static void noTasksFoundDisplay() {
        System.out.println("____________________________________________________________");
        System.out.println("No tasks found.");
        System.out.println("____________________________________________________________");
    }

    public static void showTasksFoundDisplay(ArrayList<Task> matchingTasks) {
        System.out.println("Here are all the tasks that match your search keyword: ");
        System.out.println("____________________________________________________________");
        for (int i = 0; i < matchingTasks.size(); i++) {
            System.out.println((i + 1) + ". " + " " + "[" + matchingTasks.get(i).typeOfTask + "]" + "[" + matchingTasks.get(i).getStatusIcon() + "]" + matchingTasks.get(i).description);
        }
        System.out.println("____________________________________________________________");
    }

    public static void markedTasksAsCompletedDisplay(int indexTask) {
        System.out.println("____________________________________________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(indexTask + ". " + "[" + TaskList.tasks.get(indexTask - 1).getStatusIcon() + "]" + TaskList.tasks.get(indexTask - 1).description);
        System.out.println("____________________________________________________________");
    }

    public static void listTasksDisplay() {
        System.out.println("Here are the tasks in your list: ");
        System.out.println("____________________________________________________________");
        for (int i = 0; i < TaskList.tasks.size(); i++) {
            System.out.println((i + 1) + ". " + " " + "[" + TaskList.tasks.get(i).typeOfTask + "]" + "[" + TaskList.tasks.get(i).getStatusIcon() + "]" + TaskList.tasks.get(i).description);
        }
        System.out.println("____________________________________________________________");
    }

    public static void markedTasksAsNotCompletedDisplay(int indexTask) {
        System.out.println("____________________________________________________________");
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println((indexTask) + ". " + "[" + TaskList.tasks.get(indexTask - 1).getStatusIcon() + "]" + TaskList.tasks.get(indexTask - 1).description);
        System.out.println("____________________________________________________________");
    }

    public static void indicateNewTaskDisplay(Task newTask, int currentNumberOfTasks) {
        System.out.println("____________________________________________________________");
        System.out.println("Well done, you've added a new task: ");
        System.out.println("[" + newTask.typeOfTask + "]" + "[" + newTask.getStatusIcon() + "]" + newTask.description);
        System.out.println("Currently you have " + currentNumberOfTasks + " task(s) in your list!");
        System.out.println("____________________________________________________________");
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }
}
