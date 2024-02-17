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
            // for UI testing, to prevent "no line found" exception
            if (sc.hasNextLine()) {
                command = sc.nextLine();
            } else {
                running = false;
                System.exit(0);
            }

            printSeparation();

            String[] arguments = command.split(" ");

            switch (arguments[0]) {
            case "bye":
                endSession();
                running = false;
                break;
            case "list":
                tl.listTasks();
                printSeparation();
                break;
            case "mark":
                tl.markDone(Integer.parseInt(arguments[1]) - 1);
                printSeparation();
                break;
            case "unmark":
                tl.unmarkDone(Integer.parseInt(arguments[1]) - 1);
                printSeparation();
                break;
            case "todo":
                String taskName = command.substring(5);
                tl.addToDo(taskName);
                printSeparation();
                break;
            case "deadline": {
                String[] segments = command.split(" /by ");
                tl.addDeadline(segments[0].substring(9), segments[1]);
                break;
            }
            case "event": {
                String[] segments = command.split(" /");
                tl.addEvent(segments[0].substring(6), segments[1].substring(5), segments[2].substring(3));
                break;
            }
            default:
                break;
            }
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
