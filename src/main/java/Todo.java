public class Todo {
    public static void markTask(Task[] taskList, int taskIndex) {
        if (!isValidIndex(taskList, taskIndex)) {
            System.out.println("Invalid number! Please try again");
        } else if (taskList[taskIndex].isDone()) {
            System.out.println("ERROR: Task is already marked");
        } else {
            taskList[taskIndex].markTask();
            System.out.println("OK, Dobby has marked this task as done:");
            System.out.println("  " + taskList[taskIndex]);
            System.out.println("~~~~~~~~~~~~~~~~");
        }
    }

    public static void unmarkTask(Task[] taskList, int taskIndex) {
        if (!isValidIndex(taskList, taskIndex)) {
            System.out.println("Invalid number! Please try again");
        } else if (!taskList[taskIndex].isDone()) {
            System.out.println("The task is already unmarked");
        } else {
            taskList[taskIndex].unmarkTask();
            System.out.println("OK, Dobby marked this task as not done:");
            System.out.println("  " + taskList[taskIndex]);
            System.out.println("~~~~~~~~~~~~~~~~");
        }
    }

    private static Boolean isValidIndex(Task[] taskList, int index) {
        return index >= 0 && index < taskList.length && taskList[index] != null;
    }
}
