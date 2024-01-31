import java.util.Scanner;

public class Hubert {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        //greetings
        String horizontal = "____________________________________________________________";
        String botName = "Hubert";
        System.out.println("Hello! I'm " + botName);
        System.out.println("What can I do for you?");
        System.out.println(horizontal);

        //echo
        Scanner in = new Scanner(System.in);
        String line;
        String exitWord = "bye";
        line = in.nextLine();
        while (!line.equalsIgnoreCase(exitWord)) {
            System.out.println(line);
            System.out.println(horizontal);
            line = in.nextLine();
        }

        //exit
        if (line.equalsIgnoreCase(exitWord)) {
            System.out.println("Bye. Hope to see you again soon!");
            System.out.println(horizontal);
        }
    }
}