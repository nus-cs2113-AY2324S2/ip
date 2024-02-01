import java.util.Scanner;
import java.util.ArrayList;
public class Arriky {
    public static void main(String[] args) {

        // initialize
        Scanner sc = new Scanner(System.in);
        TaskList tl = new TaskList();
        greet();
        boolean running = true;

        while(running) {
            String command = sc.nextLine();
            printSeparation();
            if(command.equals("bye")) {
                endSession();
                running = false;
            } else if (command.equals("list")) {
                tl.listTasks();
                printSeparation();
            } else {
                tl.addTask(command);
                printSeparation();
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
