import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String input;
        Scanner in = new Scanner(System.in);
        System.out.println("Good evening. I'm Nocturne.");
        System.out.println("What ails you on this fine day?");
        input = in.nextLine();
        while (!input.equals("bye")) {
            System.out.println(input);
            input = in.nextLine();
        }
        System.out.println("Farewell, and may the fortunes be ever in your favour.");
    }
}