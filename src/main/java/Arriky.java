import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
public class Arriky {
    public static void main(String[] args) {

        // initialize
        Scanner sc = new Scanner(System.in);
        TaskList tl = new TaskList();
        greet();
        boolean running = true;

        while(running) {
            String command = "";
            if (sc.hasNextLine()) {
                command = sc.nextLine();
            } else {
                System.exit(0);
            }
            printSeparation();

            String[] arguments = command.split(" ");

            if (arguments[0].equals("bye")) {
                endSession();
                running = false;
            } else if (arguments[0].equals("list")) {
                tl.listTasks();
                printSeparation();
            } else if (arguments[0].equals("mark")) {
                tl.markDone(Integer.parseInt(arguments[1]) - 1);
                printSeparation();
            } else if (arguments[0].equals("unmark")) {
                tl.unmarkDone(Integer.parseInt(arguments[1]) - 1);
                printSeparation();
            } else if (arguments[0].equals("todo")) {
                String taskName = command.substring(5);
                tl.addToDo(taskName);
                printSeparation();
            } else if (arguments[0].equals("deadline")) {
                String[] segments = command.split(" /by ");
                tl.addDeadline(segments[0].substring(9), segments[1]);
            } else if (arguments[0].equals("event")) {
                String[] segments = command.split(" /");
                tl.addEvent(segments[0].substring(6), segments[1].substring(5), segments[2].substring(3));
            } else {}
        }
    }

    private static void echo(String cmd) {
        printSeparation();
        System.out.println(cmd);
    }

    private static void greet() {
        printSeparation();
        System.out.println(
                " Hello! I'm Arriky\n" +
                " What can I do for you?"
        );
        printSeparation();
    }

    private static void endSession() {
        System.out.println(" Bye. Hope to see you again soon.");
        printSeparation();
    }

    private static void printSeparation() {
        System.out.println("____________________________________________________________");
    }
}
