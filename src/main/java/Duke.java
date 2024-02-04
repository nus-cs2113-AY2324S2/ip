import java.util.Scanner;
public class Duke {
    static String LINE = "____________________________________________________________";
    public static void main(String[] args) {
        greetUser();
        echoUser();
        byeUser();
    }
    private static void greetUser() {
        System.out.println(LINE);
        System.out.println("Hello! I'm Bartholomew, but you can call me Bart for short :)");
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }
    private static void echoUser() {
        Scanner in = new Scanner(System.in);
        String command = "";
        while (!command.equals("bye")) {
            command = in.nextLine();
            System.out.println(LINE);
            System.out.println(command);
            System.out.println(LINE);
        }
    }
    private static void byeUser() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }
}
