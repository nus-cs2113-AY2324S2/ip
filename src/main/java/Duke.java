import java.util.Scanner;
public class Duke {
    static String break_line = "____________________________________________________________";

    public static void main(String[] args) {
        System.out.println(break_line + "\nHello! I'm Jeremy.\nWhat can I do for you?\n" + break_line);

        Scanner in = new Scanner(System.in);
        String line;

        do {
            line = in.nextLine();
            if (!line.equals("bye")) {
                System.out.println(line + "\n" + break_line);
            }
        }
        while (!line.equals("bye"));

        System.out.println("Bye. Hope to see you again soon!\n" + break_line);


        }
    }
