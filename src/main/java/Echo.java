import java.util.Scanner;

public class Echo {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line;
        System.out.println("MOBY: Hello! I'm Moby.");
        System.out.println("MOBY: What can I do for you?");
        while (true) {
            System.out.print("YOU: ");
            line = in.nextLine();
            if (line.equals("bye")) {
                break;
            }
            System.out.println("MOBY: " + line);
        }
        System.out.println("MOBY: Bye. Hope to see you again soon!");
    }
}
