import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String[] list = new String[100];

        int COUNT = 0;

        String input;
        Scanner in = new Scanner(System.in);
        System.out.println("Good evening. I'm Nocturne.");
        System.out.println("What ails you on this fine day?");

        input = in.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                for (int i = 0; i < COUNT; i++){
                    System.out.println((i + 1) + ". " + list[i]);
                }
            } else {
                System.out.println("added: " + input);
                list[COUNT] = input;
                COUNT++;
            }
            input = in.nextLine();
        }
        System.out.println("Farewell, and may the fortunes be ever in your favour.");
    }
}