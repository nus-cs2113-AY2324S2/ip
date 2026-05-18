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
        System.out.println("____________________________________________________________");
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
        System.out.println("____________________________________________________________");
    }

    public void showTaskRemovedMessage(Task removedTask, int numTasks) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + removedTask);
        System.out.println("Now you have " + numTasks + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    public void showTaskAddedMessage(Task addedTask, int numTasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + addedTask);
        System.out.println("Now you have " + numTasks + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    public void showTaskMarkedDoneMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
        System.out.println("____________________________________________________________");
    }

    public void showTaskMarkedUndoneMessage(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
        System.out.println("____________________________________________________________");
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showMatchingTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No matching tasks found.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i));
            }
        }
        System.out.println("____________________________________________________________");
    }
}
