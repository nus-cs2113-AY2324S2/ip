import java.util.Scanner;
public class N {

    public static void printLine() {
        System.out.println("    ____________________________________________________________ \n");
    }

    public static void printMessage(String message) {
        printLine();
        System.out.println("    " +message);
        printLine();
    }

    /**
     * Prints the current list of tasks and their completion status.
     * If there are no tasks added, "no tasks to complete" will be printed.
     *
     * @param taskList the current list of tasks tracked.
     */
    public static void printAsList(Task[] taskList) {
        if (taskList[0] == null) {
            printMessage("no tasks added, wake up pleasee!");
        } else {
            printLine();
            for(int i = 0; taskList[i] != null; i++) {
                System.out.println(taskList[i].getIndex()+ ".["+taskList[i].getCheckMark()+"] " +taskList[i].getDescription());
            }
            printLine();
        }
    }

    public static void changeTaskStatus(Task[] taskList, int taskIndex, boolean newStatus) {
        if (taskIndex < Task.totalTasks) {
            taskList[taskIndex].setDone(newStatus);
            if (newStatus) {
                printMessage("Task " +(taskIndex + 1)+ " is done, yay! :)");
            } else {
                printMessage("Okay, task " +(taskIndex + 1)+ " still needs to be done");
            }
        } else {
            printMessage("task not found :p");
        }

    }

    public static void addTask(Task[] taskList, int taskIndex, String taskDescription) {
        printMessage("added: " +taskDescription.trim());
        taskList[taskIndex] = new Task(taskDescription, taskIndex);
    }

    public static void handleMessages(Task[] taskList, int nextIndex) {
        Scanner in = new Scanner(System.in);
        String message = in.nextLine();

        if (message.equalsIgnoreCase("bye")) {
            //print bye message
            printMessage("Bye. Hope to see you again soon!");
        } else if (message.equalsIgnoreCase("list")) {
            //print task list and continue to poll for message
            printAsList(taskList);
            handleMessages(taskList, nextIndex);
        } else if (message.contains("unmark")) {
            int indexToUnmark = Integer.parseInt(message.split(" ")[1]);
            changeTaskStatus(taskList, indexToUnmark - 1, true);
            handleMessages(taskList, nextIndex);
        } else if (message.contains("mark")) {
            int indexToMark = Integer.parseInt(message.split(" ")[1]);
            changeTaskStatus(taskList, indexToMark - 1, true);
            handleMessages(taskList, nextIndex);
        } else {
            // add a new task to the current task list
            addTask(taskList, nextIndex, message);
            handleMessages(taskList, nextIndex + 1);
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        printLine();
        System.out.println("    Hello! I'm N :) \n" + "    What can I do for you? \n");
        printLine();

        Task[] taskList = new Task[100];
        handleMessages(taskList, 0);
    }
}
