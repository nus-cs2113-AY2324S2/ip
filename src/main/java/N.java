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

    public static void printAsList(String[] taskList) {
        if (taskList[0] == null) {
            printMessage("no tasks to complete");
        } else {
            printLine();
            for(int i = 0; taskList[i] != null; i++) {
                System.out.println(i+1 + ". " +taskList[i]);
            }
            printLine();
        }
    }

    public static void makeTaskList(String[] taskList, int nextIndex) {
        Scanner in = new Scanner(System.in);
        String message = in.nextLine();
        if (message.equalsIgnoreCase("bye")) {
            printMessage("Bye. Hope to see you again soon!");
        }
        else if (message.equalsIgnoreCase("list")) {
            printAsList(taskList);
            makeTaskList(taskList, nextIndex);
        }
        else {
            printMessage("added: " +message.trim());
            taskList[nextIndex] = message.trim();
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

        String[] taskList = new String[100];
        makeTaskList(taskList, 0);
    }
}
