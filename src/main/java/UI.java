

public class UI {
    private static final String LINE = "____________________________________________________________";

    public static void greeting() {
        System.out.println(LINE);
        System.out.println("Hello! I'm Eln");
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    public static void farewell() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }
    public static void showList() {
        System.out.println(LINE);

        int numberOfTasks = TaskList.getNumberOfTasks();
        System.out.println("Your list currently has " + numberOfTasks + " items.");
        int displayedTaskNum;

        for (int taskIndex = 0; taskIndex < numberOfTasks; taskIndex += 1) {
            displayedTaskNum = taskIndex + 1;
            Task taskToDisplay = TaskList.getAllTasks().get(taskIndex);
            System.out.println(displayedTaskNum + " " + taskToDisplay.toString());
        }

        System.out.println(LINE);
    }
}
