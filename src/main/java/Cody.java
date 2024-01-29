import java.util.Scanner;

public class Cody {

    private static void echo() {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        while (!input.equals("bye")) {
            System.out.println("____________________________________________________________\n" +
                    " " + input + "\n" +
                    "_____________________________________________________________");
            input = in.nextLine();
        }
    }

    private static void greet() {
        String greet = " ____________________________________________________________\n" +
                " Hello! I'm Cody\n" +
                " What can I do for you?\n" +
                "_____________________________________________________________\n";
        System.out.println(greet);
    }

    private static void exit() {
        String exit = "____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "_____________________________________________________________";
        System.out.println(exit);
    }


    public static void main(String[] args) {
        greet();
        echo();
        exit();
    }
}

