import java.util.ArrayList;

public class Ui {
    private static final String DASHED_LINE = "____________________________________________________________";

    public void showWelcomeMessage() {
        System.out.println("Hello! I'm Joey");
        System.out.println("What can I do for you?");
        System.out.println(DASHED_LINE);
    }

    public void showByeMessage() {
        System.out.println(" bye bye, take care:)!");
        System.out.println(DASHED_LINE);
    }

    public void showError(String errorMessage) {
        System.out.println("OOPS!!! " + errorMessage);
        System.out.println(DASHED_LINE);
    }

    public void showTaskAdded(Task task, int taskCount) {
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + taskCount + " tasks in the list.");
        System.out.println(DASHED_LINE);
    }

    public void showTaskList(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("There are no tasks in the list.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(" " + (i + 1) + ". " + tasks.get(i).toString());
            }
        }
        System.out.println("____________________________________________________________");
    }
}
