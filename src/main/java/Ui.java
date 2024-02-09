import java.util.Scanner;

public class Ui {

    public static String getTask() {
        String taskDescription;
        Scanner in = new Scanner(System.in);
        System.out.println("\tWhat do you want to do?: ");
        taskDescription = in.nextLine();
        return taskDescription;
    }

    public static void listTask(Task[] taskList) { //using the array to list the tasks
        System.out.println("All your tasks are here");
        for (int i = 0; i < Task.getTaskCount(); i++) {
            System.out.println((i + 1) + "." + taskList[i].toString());
        }
    }

    public static void printTaskCount (int taskCount) {
        System.out.println("Now you have " + taskCount + " task(s)");
        System.out.println("_______________________");
    }

    public static void printTodoTask (String toDoTask) {
        System.out.println("_______________________");
        System.out.println("Oh no! One new task added...");
        System.out.println(toDoTask);
    }

    public static void printDeadlineTask (String deadlineTask) {
        System.out.println("_______________________");
        System.out.println("Ok! Watch the deadline!");
        System.out.println(deadlineTask);
    }

    public static void printEventTask (String eventTask) {
        System.out.println("_______________________");
        System.out.println("Weehoo! Enjoy the event!");
        System.out.println(eventTask);
    }

    public static void printMarkTask (int taskIndex, Task[] taskList) {
        System.out.println("_______________________");
        System.out.println("One done!");
        System.out.println(taskList[taskIndex]);
    }

    public static void printUnmarkTask (int taskIndex, Task[] taskList) {
        System.out.println("______________________");
        System.out.println("Oh no!");
        System.out.println(taskList[taskIndex]);
    }
}
