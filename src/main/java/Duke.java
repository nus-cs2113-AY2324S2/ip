import java.util.Scanner;

public class Duke {
    public static void startEcho() {
        Scanner in = new Scanner(System.in);
        final String line = "____________________________________________________________";
        String byeStatement = "bye";
        String text = in.nextLine();
        boolean hasSaidBye = byeStatement.equals(text);

        if (text.equals(byeStatement)) {
            System.out.println(line);
            return;
        }

        System.out.println(line);
        System.out.println(text);
        System.out.println(line);

        while (!hasSaidBye) {
            text = in.nextLine();
            System.out.println(line); //not being printed
            if (text.equals(byeStatement)) {
                hasSaidBye = true;
            } else {
                System.out.println(text);
                System.out.println(line);
            }
        }
    }

    public static void main(String[] args) {
        final String line = "____________________________________________________________";
        System.out.println(line);
        System.out.println("Hello! I'm Gandalf");
        System.out.println("What can I do for you?");
        System.out.println(line);
        startEcho();
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
