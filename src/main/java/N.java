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

    public static void printAsList(Task[] taskList) {
        if (taskList[0] == null) {
            printMessage("no tasks to complete");
        } else {
            printLine();
            for(int i = 0; taskList[i] != null; i++) {
                System.out.println(taskList[i].getIndex()+ ".["+taskList[i].getMark()+"] " +taskList[i].getDescription());
            }
            printLine();
        }
    }

    public static void makeTaskList(Task[] taskList, int nextIndex) {
        Scanner in = new Scanner(System.in);
        String message = in.nextLine();
        if (message.equalsIgnoreCase("bye")) {
            printMessage("Bye. Hope to see you again soon!");
        }
        else if (message.equalsIgnoreCase("list")) {
            printAsList(taskList);
            makeTaskList(taskList, nextIndex);
        }
        else if (message.contains("unmark")) {
            int indexToUnmark = Integer.parseInt(message.split(" ")[1]);
            taskList[indexToUnmark - 1].setDone(false);
            printMessage("Okay, task " +indexToUnmark+ " still needs to be done");
            makeTaskList(taskList, nextIndex);
        }
        else if (message.contains("mark")) {
            int indexToMark = Integer.parseInt(message.split(" ")[1]);
            taskList[indexToMark - 1].setDone(true);
            printMessage("Task " +indexToMark+ " is done, yay! :)");
            makeTaskList(taskList, nextIndex);
        }
        else {
            printMessage("added: " +message.trim());
            taskList[nextIndex] = new Task(message, nextIndex);
            makeTaskList(taskList, nextIndex + 1);
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
        makeTaskList(taskList, 0);
    }
}
