
import java.util.Scanner;


public class Eln {
    static String LINE = "____________________________________________________________";
    private static void echoUser() {
        Scanner scan = new Scanner(System.in);
        String input = "";

        while(!input.equals("bye")) {
            input = scan.nextLine();
            System.out.println(LINE);
            System.out.println(input);
            System.out.println(LINE);
        }
    }
    public static void main(String[] args) {

        System.out.println(LINE);
        System.out.println("Hello! I'm Eln");
        System.out.println("What can I do for you?");
        System.out.println(LINE);

        echoUser();

        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);

    }
}
