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

        String[] todos = new String[100];
        int todosCount = 0;

        String line;
        line = in.nextLine();
        while (!(line.toUpperCase().contains("BYE"))) {
            if (line.equalsIgnoreCase("LIST")) {
                for(int i = 0; i < todosCount; i += 1) {
                    System.out.println((i + 1) + ". " + todos[i]);
                }
            } else {
                System.out.println("added: " + line);
                todos[todosCount] = line;
                todosCount += 1;
            }

            line = in.nextLine();
        }


        System.out.println("Bye. Hope to see you again!");
    }
}
