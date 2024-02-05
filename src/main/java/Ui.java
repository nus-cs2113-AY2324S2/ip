public class Ui {

    public static void listTask(Task[] taskList) { //using the array to list the tasks
        System.out.println("All your tasks are here");
        for (int i = 0; i < Task.getTaskCount(); i++) {
            System.out.println((i+1) + "." + taskList[i].toString());
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

}
