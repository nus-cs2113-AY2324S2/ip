import java.util.Scanner;

public class Miku {
    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);

        System.out.println("______________________");
        System.out.println("Hello! I'm Miku!\n" + "What can I do for you?");
        System.out.println("______________________");

        line = in.nextLine();
        String echo = line;
        while (!line.equals("bye")) {
            System.out.println("______________________");
            System.out.println(echo);
            System.out.println("______________________");
            line = in.nextLine();
            echo = line;
        }

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("______________________");
    }
}
