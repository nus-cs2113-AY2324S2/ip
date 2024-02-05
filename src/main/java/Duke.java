import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static void addList(String line) {
        System.out.println("--------------------------------------");
        System.out.println("added: " + line);
        System.out.println("--------------------------------------");
    }

    public static void printBye() {
        System.out.println("--------------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("--------------------------------------");
    }

    public static void printList(Task[] list, int listIndex) {
        int printCounter = 1;
        System.out.println("--------------------------------------");
        System.out.println("Here are the tasks in your lists:");
        for (Task item : Arrays.copyOf(list, listIndex)) {
            System.out.println(printCounter + "." + item.getStatusIcon() + " " + item.description);
            printCounter++;
        }
    }

    public static void introStart() {
        String logo
                = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("--------------------------------------");
        System.out.println("Hello! I'm Steffy");
        System.out.println("What can I do for you?");
        System.out.println("--------------------------------------");
    }

    public static void main(String[] args) {

        introStart(); //Prints starting screen

        boolean ifExit = false; //exits program if true
        Scanner in = new Scanner(System.in);
        Task[] list = new Task[100];
        int listIndex = 0;

        while (!ifExit) {
            String line = in.nextLine();
            if (line.equals("bye")) {
                //checks for bye interaction and sets ifExit to true
                printBye();
                ifExit = true;
            } else if (line.equals("list")) {
                //calls printList and shows list of tasks
                printList(list, listIndex);
            } else if (line.contains("unmark")) {
                //checks for "unmark" to unmark a task
                list[Integer.parseInt(line.substring(7)) - 1].unmarkDone();
                System.out.println("--------------------------------------");
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println
                        (list[Integer.parseInt(line.substring(7)) - 1].getStatusIcon() + " "
                        + list[Integer.parseInt(line.substring(7)) - 1].description);
                System.out.println("--------------------------------------");
            } else if (line.contains("mark")) {
                //else checks for "mark" to mark a task
                list[Integer.parseInt(line.substring(5)) - 1].markAsDone();
                System.out.println("--------------------------------------");
                System.out.println("Nice! I've marked this task as done:");
                System.out.println
                (list[Integer.parseInt(line.substring(5)) - 1].getStatusIcon() + " "
                        + list[Integer.parseInt(line.substring(5)) - 1].description);
                System.out.println("--------------------------------------");
            } else {
                //else adds a new task and echos task once
                addList(line);
                list[listIndex] = new Task(line);
                listIndex++;
            }
        }
    }
}
