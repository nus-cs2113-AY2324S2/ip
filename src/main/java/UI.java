import java.util.Scanner;

public class UI {
    public static final String LINE_SEPARATOR = "--------------------------------------";

    public static void welcome () {
        Task.loadFromDisk();
        String name = "Stella";
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        System.out.println(UI.LINE_SEPARATOR);
    }

    public static void printMessage(String message){
        System.out.println(UI.LINE_SEPARATOR);
        System.out.println(message);
        System.out.println(UI.LINE_SEPARATOR);
    }
    public static void takeResponse () {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        while (!line.equals("bye")) {
            Task.responseToCommand(line);
            line = in.nextLine();
        }
    }

    public static void bye () {
        System.out.println(UI.LINE_SEPARATOR);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(UI.LINE_SEPARATOR);
    }

}
