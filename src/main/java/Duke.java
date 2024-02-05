import java.util.Locale;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        Scanner in = new Scanner(System.in);
        System.out.println("Hello! I'm Gary");
        System.out.println("What can I do for you?");

        String line;
        line = in.nextLine();
        while (!(line.toUpperCase().contains("BYE"))) {
            System.out.println(line);
            line = in.nextLine();
        }

        System.out.println("Bye. Hope to see you again!");
    }
}
