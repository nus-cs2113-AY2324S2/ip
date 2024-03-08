import java.util.List;

public class Ui {
    public void showWelcomeMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm [Sparky]");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public void showGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soooon!");
        System.out.println("____________________________________________________________");
    }

    public void showTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks added yet.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i));
            }
        }
        showLine();
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
        showLine();
    }

    public void showTaskRemovedMessage(Task task, int remainingTasks) {
        System.out.println("Noted. I've removed this task:\n" + task + "\nNow you have " + remainingTasks + " tasks in the list.");
        showLine();
    }

    public void showTaskAddedMessage(Task task, int totalTasks) {
        System.out.println("Got it. I've added this task:\n" + task + "\nNow you have " + totalTasks + " tasks in the list.");
        showLine();
    }

    public void showTaskMarkedDoneMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:\n" + task);
        showLine();
    }

    public void showTaskMarkedUndoneMessage(Task task) {
        System.out.println("OK, I've marked this task as not done yet:\n" + task);
        showLine();
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }
}
