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
        String outputMessage = "";
        //check to ensure that task to be marked/unmarked exists in the list
        if (taskIndex < Task.totalTasks) {
            //when no change in status is required
            if (taskList[taskIndex].isDone() == newStatus) {
                //generate output message based on current task status
                outputMessage = (newStatus) ?
                        //output message if task has already been marked done
                        "Task " +taskList[taskIndex].getIndex()+ " is already completed!" :
                        //output message if task has not been marked done
                        "Task " +taskList[taskIndex].getIndex()+ " is already unmarked, complete other tasks!";
            } else { //handle a change in task status
                taskList[taskIndex].setDone(newStatus);
                //generate output message based on new task status
                outputMessage = (newStatus) ?
                        //output message when task is marked done
                        "Task " +(taskIndex + 1)+ " marked done, yay! :)" :
                        //output message when task is unmarked
                        "Okay, task " +(taskIndex + 1)+ " unmarked, let's complete it soon ~.o.~";
            }
        } else {
            //output message when task index given is invalid
            outputMessage = "Task not found :p";
        }
        printMessage(outputMessage);
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
            changeTaskStatus(taskList, indexToUnmark - 1, false);
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
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
         */

        String logo = " ____   ___\n"
                + "|    \\ |   |\n"
                + "|     \\|   |\n"
                + "|          |\n"
                + "|   |\\     |\n"
                + "|___| \\____| .chatbot :)\n";

        System.out.println("Hello from\n" + logo);

        printLine();
        System.out.println("    Hello! I'm N :) \n" + "    What can I do for you? \n");
        printLine();

        Task[] taskList = new Task[100];
        handleMessages(taskList, 0);
    }
}
