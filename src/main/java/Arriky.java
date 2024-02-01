import java.util.Scanner;
public class Arriky {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        greet();
        boolean running = true;

        while(running) {
            String command = sc.nextLine();
            printSeparation();
            if(command.equals("bye")) {
                endSession();
                running = false;
            }
            echo(command);

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
    }

    private static void endSession() {
        System.out.println(" Bye. Hope to see you again soon.");
        printSeparation();
    }

    private static void printSeparation() {
        System.out.println("____________________________________________________________");
    }
}
