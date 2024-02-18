import java.util.Scanner;

public class Ui {
    public static final String LOGO =
            "  _____           __ \n"
                    + "/  ____|         |  |\n"
                    + "|  |  __   ____  |  |__\n"
                    + "|  | |_  |/  _   |  -   \\ \n"
                    + "|  |__|  |  (_|  | |_)   |\n"
                    + "\\_______ |__ ,_ |_.___ /\n";

    public static void printWelcome() {
        System.out.println(LOGO);
        System.out.println("\tI am Gab the Bot! Nice to meet you!");
        System.out.println("\tAnything I can help you with?");
    }

    public static final String LINE = "_______________________";

    public static String getTask() {
        String taskDescription;
        Scanner in = new Scanner(System.in);
        System.out.println("\tWhat do you want to do?: ");
        taskDescription = in.nextLine();
        return taskDescription;
    }

    public static void listTask(TaskList taskList) { //using the array to list the tasks
        System.out.println("All your tasks are here");
        for (int i = 0; i < taskList.getTaskCount(); i++) {
            System.out.println((i + 1) + "." + taskList.taskList.get(i).toString());
        }
    }

    public static void printTaskCount (int taskCount) {
        System.out.println("Now you have " + taskCount + " task(s)");
        System.out.println(LINE);
    }

    public static void printTodoTask (String toDoTask) {
        System.out.println(LINE);
        System.out.println("Oh no! One new task added...");
        System.out.println(toDoTask);
    }

    public static void printDeadlineTask (String deadlineTask) {
        System.out.println(LINE);
        System.out.println("Ok! Watch the deadline!");
        System.out.println(deadlineTask);
    }

    public static void printEventTask (String eventTask) {
        System.out.println(LINE);
        System.out.println("Weehoo! Enjoy the event!");
        System.out.println(eventTask);
    }

    public static void printMarkTask (int taskIndex, TaskList taskList) {
        System.out.println(LINE);
        System.out.println("One done!");
        System.out.println(taskList.taskList.get(taskIndex).toString());
    }

    public static void printUnmarkTask (int taskIndex, TaskList taskList) {
        System.out.println(LINE);
        System.out.println("Oh no!");
        System.out.println(taskList.taskList.get(taskIndex).toString());
    }

    public static void printDeleteTask (int taskIndex, TaskList taskList) {
        System.out.println("Ok task deleted!");
        System.out.println("\t" + taskList.taskList.get(taskIndex).toString());
    }
}
