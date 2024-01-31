import java.util.Scanner;

public class Humi {
    public static final String LINE = "____________________________________________________________";
        //    String logo = " ____        _        \n"
        //                + "|  _ \\ _   _| | _____ \n"
        //                + "| | | | | | | |/ / _ \\\n"
        //                + "| |_| | |_| |   <  __/\n"
        //                + "|____/ \\__,_|_|\\_\\___|\n";
    public static void main(String[] args) {
        System.out.println(LINE);
        System.out.println("Hello! I'm Humi");
        System.out.println("What can I do for you?");
        System.out.println(LINE);

        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        while (!input.equals("bye")) {
            System.out.println(LINE);
            System.out.println(input);
            System.out.println(LINE);
            input = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
