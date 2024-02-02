import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);

        System.out.println("Hello! I'm FredBot.\nWhat can I do for you?\n");

        line = in.nextLine();
        while (!line.equals("bye")) {
            System.out.println(line+"\n");
            line = in.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
