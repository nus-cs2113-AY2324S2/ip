import java.util.Scanner;

public class Kvothe {
    public static void main(String[] args) {
        String greeting = "Hello! I'm Kvothe.\n"
                + "What can I do for you?";

        String bye = "Bye. Hope to see you again soon!";

        System.out.println(greeting);

        repeatCommands();

        System.out.println(bye);
    }

    public static void repeatCommands() {
        String stopWord = "bye";
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();

        while (!line.equals(stopWord)) {
            System.out.println(line);
            line = in.nextLine();
        }

    }
}